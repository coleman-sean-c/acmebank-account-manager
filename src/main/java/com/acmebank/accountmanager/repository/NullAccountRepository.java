package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.util.List;
import java.util.Optional;

public class NullAccountRepository implements AccountRepository {
  @Override
  public Optional<Account> findById(String id) {
    return Optional.empty();
  }

  @Override
  public void saveAll(List<Account> accounts) {}
}
