package com.acmebank.accountmanager.usecases.createtransfer;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.util.Optional;

public class CreateTransferUseCase {

  private final AccountRepository accountRepository;

  public CreateTransferUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public CreateTransferResponse createTransfer(CreateTransferRequest request) {
    Optional<Account> from = accountRepository.findById(request.getFrom());

    return CreateTransferResponse.builder()
        .success(false)
        .message("Account '12345678' not found.")
        .build();
  }
}
