package com.acmebank.accountmanager.configuration;

import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.repository.NullAccountRepository;
import com.acmebank.accountmanager.usecases.getbalance.GetBalanceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountManagerConfiguration {

  @Bean
  public AccountRepository accountRepository() {
    return new NullAccountRepository();
  }

  @Bean
  public GetBalanceUseCase getBalanceUseCase(AccountRepository accountRepository) {
    return new GetBalanceUseCase(accountRepository);
  }
}
