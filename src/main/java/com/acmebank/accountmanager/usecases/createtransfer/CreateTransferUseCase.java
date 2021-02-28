package com.acmebank.accountmanager.usecases.createtransfer;

import com.acmebank.accountmanager.model.AccountRepository;

public class CreateTransferUseCase {

  private AccountRepository accountRepository;

  public CreateTransferUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public CreateTransferResponse createTransfer(CreateTransferRequest request) {
    return null;
  }
}
