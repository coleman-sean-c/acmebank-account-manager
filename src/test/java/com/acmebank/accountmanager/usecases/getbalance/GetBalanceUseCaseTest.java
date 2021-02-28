package com.acmebank.accountmanager.usecases.getbalance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.acmebank.accountmanager.model.AccountRepository;
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
    GetBalanceRequest request = GetBalanceRequest.builder().id("12345678").build();

    given(accountRepository.findById("12345678")).willReturn(Optional.empty());

    GetBalanceResponse response = getBalanceUseCase.getBalance(request);

    assertThat(response).isNotNull();
  }
}
