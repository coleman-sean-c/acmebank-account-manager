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
    if (from.isEmpty()) {
      return getNotFoundResponse(request.getFrom());
    }

    Optional<Account> to = accountRepository.findById(request.getTo());
    if (to.isEmpty()) {
      return getNotFoundResponse(request.getTo());
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
