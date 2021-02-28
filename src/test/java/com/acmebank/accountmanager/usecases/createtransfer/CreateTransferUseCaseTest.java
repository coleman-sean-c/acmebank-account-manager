package com.acmebank.accountmanager.usecases.createtransfer;

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
class CreateTransferUseCaseTest {

  @Mock private AccountRepository accountRepository;

  @InjectMocks private CreateTransferUseCase createTransferUseCase;

  @Test
  public void testFromNotFound() {
    given(accountRepository.findById("12345678")).willReturn(Optional.empty());

    CreateTransferRequest request =
        CreateTransferRequest.builder()
            .from("12345678")
            .to("88888888")
            .currency("HKD")
            .amount(BigDecimal.TEN)
            .build();
    CreateTransferResponse response = createTransferUseCase.createTransfer(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isFalse();
    assertThat(response.getMessage()).isEqualTo("Account '12345678' not found.");
  }

  @Test
  public void testToNotFound() {
    Account from =
        Account.builder()
            .id("12345678")
            .currency("HKD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.empty());

    CreateTransferRequest request =
        CreateTransferRequest.builder()
            .from("12345678")
            .to("88888888")
            .currency("HKD")
            .amount(BigDecimal.TEN)
            .build();
    CreateTransferResponse response = createTransferUseCase.createTransfer(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isFalse();
    assertThat(response.getMessage()).isEqualTo("Account '88888888' not found.");
  }

  @Test
  public void testFromWrongCurrency() {
    Account from =
        Account.builder()
            .id("12345678")
            .currency("USD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    Account to =
        Account.builder()
            .id("88888888")
            .currency("HKD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request =
        CreateTransferRequest.builder()
            .from("12345678")
            .to("88888888")
            .currency("HKD")
            .amount(BigDecimal.TEN)
            .build();
    CreateTransferResponse response = createTransferUseCase.createTransfer(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isFalse();
  }

  @Test
  public void testToWrongCurrency() {
    Account from =
        Account.builder()
            .id("12345678")
            .currency("HKD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    Account to =
        Account.builder()
            .id("88888888")
            .currency("CNY")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request =
        CreateTransferRequest.builder()
            .from("12345678")
            .to("88888888")
            .currency("HKD")
            .amount(BigDecimal.TEN)
            .build();
    CreateTransferResponse response = createTransferUseCase.createTransfer(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isFalse();
  }

  @Test
  public void testFromInsufficientBalance() {
    Account from = Account.builder().id("12345678").currency("HKD").amount(BigDecimal.ONE).build();

    Account to =
        Account.builder()
            .id("88888888")
            .currency("HKD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request =
        CreateTransferRequest.builder()
            .from("12345678")
            .to("88888888")
            .currency("HKD")
            .amount(BigDecimal.TEN)
            .build();
    CreateTransferResponse response = createTransferUseCase.createTransfer(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isFalse();
    assertThat(response.getMessage()).isEqualTo("Insufficient Balance");
  }
}
