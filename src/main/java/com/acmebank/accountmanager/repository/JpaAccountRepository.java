package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaAccountRepository implements AccountRepository {
  private final AccountEntityRepository accountEntityRepository;

  public JpaAccountRepository(AccountEntityRepository accountEntityRepository) {
    this.accountEntityRepository = accountEntityRepository;
  }

  @Override
  public Optional<Account> findById(String id) {
    Optional<AccountEntity> accountEntity = accountEntityRepository.findById(id);

    return accountEntity.map(this::accountFromEntity);
  }

  @Override
  public void saveAll(List<Account> accounts) {
    List<AccountEntity> entities =
        accounts.stream().map(this::entityFromAccount).collect(Collectors.toList());

    accountEntityRepository.saveAll(entities);
  }

  private Account accountFromEntity(AccountEntity entity) {
    return Account.builder()
        .id(entity.getId())
        .currency(entity.getCurrency())
        .amount(entity.getAmount())
        .build();
  }

  private AccountEntity entityFromAccount(Account account) {
    return new AccountEntity(account.getId(), account.getCurrency(), account.getAmount());
  }
}
