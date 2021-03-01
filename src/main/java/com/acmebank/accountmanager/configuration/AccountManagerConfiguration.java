package com.acmebank.accountmanager.configuration;

import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.repository.AccountEntityRepository;
import com.acmebank.accountmanager.repository.JpaAccountRepository;
import com.acmebank.accountmanager.usecase.createtransfer.CreateTransferUseCase;
import com.acmebank.accountmanager.usecase.getbalance.GetBalanceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.acmebank.accountmanager.repository")
public class AccountManagerConfiguration {

  @Bean
  public AccountRepository accountRepository(AccountEntityRepository accountEntityRepository) {
    return new JpaAccountRepository(accountEntityRepository);
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
