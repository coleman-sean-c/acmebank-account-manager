package com.acmebank.accountmanager.usecase.getbalance;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.usecase.exception.AccountNotFoundException;

public class GetBalanceUseCase {
  private AccountRepository accountRepository;

  public GetBalanceUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public GetBalanceResponse getBalance(GetBalanceRequest request) {
    Account account =
        accountRepository.findById(request.getId()).orElseThrow(AccountNotFoundException::new);

    return responseFromAccount(account);
  }

  private GetBalanceResponse responseFromAccount(Account account) {
    return GetBalanceResponse.builder()
        .success(true)
        .amount(account.getAmount())
        .currency(account.getCurrency())
        .build();
  }
}
