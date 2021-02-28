package com.acmebank.accountmanager.model;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
  public Optional<Account> findById(long id);

  public void saveAll(List<Account> accounts);
}
