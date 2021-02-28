package com.acmebank.accountmanager.usecases.getbalance;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.util.Optional;

public class GetBalanceUseCase {
  private AccountRepository accountRepository;

  public GetBalanceUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public GetBalanceResponse getBalance(GetBalanceRequest request) {
    Optional<Account> optional = accountRepository.findById(request.getId());

    return optional.map(this::responseFromAccount).orElseGet(this::failureResponse);
  }

  private GetBalanceResponse responseFromAccount(Account account) {
    return GetBalanceResponse.builder()
        .success(true)
        .amount(account.getAmount())
        .currency(account.getCurrency())
        .build();
  }

  private GetBalanceResponse failureResponse() {
    return GetBalanceResponse.builder().success(false).message("Account not found.").build();
  }
}
