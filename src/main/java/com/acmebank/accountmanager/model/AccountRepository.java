package com.acmebank.accountmanager.model;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
  Optional<Account> findById(String id);

  void saveAll(List<Account> accounts);
}
