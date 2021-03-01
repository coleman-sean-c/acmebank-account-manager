package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapAccountRepository implements AccountRepository {

  private final Map<String, Account> accountMap;

  public MapAccountRepository(Map<String, Account> accountMap) {
    this.accountMap = accountMap;
  }

  @Override
  public Optional<Account> findById(String id) {
    return Optional.ofNullable(accountMap.get(id));
  }

  @Override
  public void saveAll(List<Account> accounts) {
    accounts.forEach(account -> accountMap.put(account.getId(), account));
  }
}
