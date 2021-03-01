package com.acmebank.accountmanager.configuration;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.repository.MapAccountRepository;
import com.acmebank.accountmanager.usecase.createtransfer.CreateTransferUseCase;
import com.acmebank.accountmanager.usecase.getbalance.GetBalanceUseCase;
import java.math.BigDecimal;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountManagerConfiguration {

  @Bean
  public AccountRepository accountRepository() {
    HashMap<String, Account> accountMap = new HashMap<>();

    Account account1 =
        Account.builder().id("12345678").currency("HKD").amount(BigDecimal.TEN).build();
    accountMap.put("12345678", account1);

    Account account2 =
        Account.builder().id("88888888").currency("HKD").amount(BigDecimal.ONE).build();
    accountMap.put("88888888", account2);

    return new MapAccountRepository(accountMap);
  }

  @Bean
  public GetBalanceUseCase getBalanceUseCase(AccountRepository accountRepository) {
    return new GetBalanceUseCase(accountRepository);
  }

  @Bean
  public CreateTransferUseCase createTransferUseCase(AccountRepository accountRepository) {
    return new CreateTransferUseCase(accountRepository);
  }
}
