package com.acmebank.accountmanager.usecase.createtransfer;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.usecase.exception.AccountNotFoundException;
import com.acmebank.accountmanager.usecase.exception.IncorrectCurrencyException;
import com.acmebank.accountmanager.usecase.exception.InsufficientBalanceException;
import java.util.Arrays;
import java.util.Objects;

public class CreateTransferUseCase {

  private final AccountRepository accountRepository;

  public CreateTransferUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public CreateTransferResponse createTransfer(CreateTransferRequest request) {
    Account from =
        accountRepository.findById(request.getFrom()).orElseThrow(AccountNotFoundException::new);
    Account to =
        accountRepository.findById(request.getTo()).orElseThrow(AccountNotFoundException::new);

    if (!Objects.equals(request.getCurrency(), from.getCurrency())) {
      throw new IncorrectCurrencyException();
    }
    if (!Objects.equals(request.getCurrency(), to.getCurrency())) {
      throw new IncorrectCurrencyException();
    }

    if (request.getAmount().compareTo(from.getAmount()) > 0) {
      throw new InsufficientBalanceException();
    }

    Account newFrom =
        Account.builder()
            .id(from.getId())
            .currency(from.getCurrency())
            .amount(from.getAmount().subtract(request.getAmount()))
            .build();

    Account newTo =
        Account.builder()
            .id(to.getId())
            .currency(to.getCurrency())
            .amount(to.getAmount().add(request.getAmount()))
            .build();

    accountRepository.saveAll(Arrays.asList(newFrom, newTo));

    return CreateTransferResponse.builder()
        .amount(newFrom.getAmount())
        .currency(newFrom.getCurrency())
        .success(true)
        .build();
  }
}
