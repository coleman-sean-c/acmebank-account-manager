package com.acmebank.accountmanager.usecases.createtransfer;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.util.Objects;
import java.util.Optional;

public class CreateTransferUseCase {

  private final AccountRepository accountRepository;

  public CreateTransferUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public CreateTransferResponse createTransfer(CreateTransferRequest request) {
    Optional<Account> from = accountRepository.findById(request.getFrom());
    if (from.isEmpty()) {
      return getNotFoundResponse(request.getFrom());
    }

    Optional<Account> to = accountRepository.findById(request.getTo());
    if (to.isEmpty()) {
      return getNotFoundResponse(request.getTo());
    }

    Account fromAccount = from.get();

    if (!Objects.equals(request.getCurrency(), fromAccount.getCurrency())) {
      return CreateTransferResponse.builder()
          .success(false)
          .message("Cannot complete transfer")
          .build();
    }

    Account toAccount = to.get();
    if (!Objects.equals(request.getCurrency(), toAccount.getCurrency())) {
      return CreateTransferResponse.builder()
              .success(false)
              .message("Cannot complete transfer")
              .build();
    }

    return null;
  }

  private CreateTransferResponse getNotFoundResponse(String id) {
    return CreateTransferResponse.builder()
        .success(false)
        .message(String.format("Account '%s' not found.", id))
        .build();
  }
}
