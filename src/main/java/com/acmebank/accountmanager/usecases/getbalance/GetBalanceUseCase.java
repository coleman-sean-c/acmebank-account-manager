package com.acmebank.accountmanager.usecases.getbalance;

import com.acmebank.accountmanager.model.AccountRepository;

public class GetBalanceUseCase {
  private AccountRepository accountRepository;

  public GetBalanceUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public GetBalanceResponse getBalance(GetBalanceRequest request) {
    accountRepository.findById(request.getId());

    return new GetBalanceResponse();
  }
}
