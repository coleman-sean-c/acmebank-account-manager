package com.acmebank.accountmanager.usecase.getbalance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetBalanceUseCaseTest {

  @Mock private AccountRepository accountRepository;

  @InjectMocks private GetBalanceUseCase getBalanceUseCase;

  @Test
  public void testNotFound() {
    given(accountRepository.findById("12345678")).willReturn(Optional.empty());

    GetBalanceRequest request = GetBalanceRequest.builder().id("12345678").build();
    GetBalanceResponse response = getBalanceUseCase.getBalance(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isFalse();
    assertThat(response.getMessage()).isEqualTo("Account not found.");
  }

  @Test
  public void testValid() {
    Account account =
        Account.builder()
            .id("12345678")
            .currency("HKD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(account));

    GetBalanceRequest request = GetBalanceRequest.builder().id("12345678").build();
    GetBalanceResponse response = getBalanceUseCase.getBalance(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isTrue();
    assertThat(response.getCurrency()).isEqualTo("HKD");
    assertThat(response.getAmount()).isEqualTo(BigDecimal.valueOf(1_000_000));
  }
}
