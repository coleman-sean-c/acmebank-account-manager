package com.acmebank.accountmanager.usecase.createtransfer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.usecase.exception.AccountNotFoundException;
import com.acmebank.accountmanager.usecase.exception.IncorrectCurrencyException;
import com.acmebank.accountmanager.usecase.exception.InsufficientBalanceException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTransferUseCaseTest {

  @Mock private AccountRepository accountRepository;

  @Captor private ArgumentCaptor<List<Account>> captor;

  @InjectMocks private CreateTransferUseCase createTransferUseCase;

  @Test
  public void testFromNotFound() {
    given(accountRepository.findById("12345678")).willReturn(Optional.empty());

    CreateTransferRequest request = buildCreateTransferRequest();
    Throwable throwable = catchThrowable(() -> createTransferUseCase.createTransfer(request));

    assertThat(throwable).isNotNull().isInstanceOf(AccountNotFoundException.class);
  }

  @Test
  public void testToNotFound() {
    Account from = buildFromAccount();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.empty());

    CreateTransferRequest request = buildCreateTransferRequest();
    Throwable throwable = catchThrowable(() -> createTransferUseCase.createTransfer(request));

    assertThat(throwable).isNotNull().isInstanceOf(AccountNotFoundException.class);
  }

  @Test
  public void testFromWrongCurrency() {
    Account from = Account.builder().id("12345678").currency("USD").amount(BigDecimal.TEN).build();
    Account to = buildToAccount();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request = buildCreateTransferRequest();
    Throwable throwable = catchThrowable(() -> createTransferUseCase.createTransfer(request));

    assertThat(throwable).isNotNull().isInstanceOf(IncorrectCurrencyException.class);
  }

  @Test
  public void testToWrongCurrency() {
    Account from = buildFromAccount();
    Account to = Account.builder().id("88888888").currency("CNY").amount(BigDecimal.TEN).build();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request = buildCreateTransferRequest();
    Throwable throwable = catchThrowable(() -> createTransferUseCase.createTransfer(request));

    assertThat(throwable).isNotNull().isInstanceOf(IncorrectCurrencyException.class);
  }

  @Test
  public void testFromInsufficientBalance() {
    Account from = Account.builder().id("12345678").currency("HKD").amount(BigDecimal.ONE).build();
    Account to = buildToAccount();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request = buildCreateTransferRequest();
    Throwable throwable = catchThrowable(() -> createTransferUseCase.createTransfer(request));

    assertThat(throwable).isNotNull().isInstanceOf(InsufficientBalanceException.class);
  }

  @Test
  public void testCreateTransfer() {
    Account from = buildFromAccount();
    Account to = buildToAccount();

    given(accountRepository.findById("12345678")).willReturn(Optional.of(from));
    given(accountRepository.findById("88888888")).willReturn(Optional.of(to));

    CreateTransferRequest request = buildCreateTransferRequest();
    CreateTransferResponse response = createTransferUseCase.createTransfer(request);

    assertThat(response).isNotNull();
    assertThat(response.isSuccess()).isTrue();

    verify(accountRepository).saveAll(captor.capture());
    List<Account> accounts = captor.getValue();
    assertThat(accounts.size()).isEqualTo(2);

    Account updatedFrom = accounts.get(0);
    Account updatedTo = accounts.get(1);

    assertThat(updatedFrom.getId()).isEqualTo("12345678");
    assertThat(updatedFrom.getAmount()).isEqualTo(BigDecimal.ZERO);
    assertThat(updatedFrom.getCurrency()).isEqualTo("HKD");

    assertThat(updatedTo.getId()).isEqualTo("88888888");
    assertThat(updatedTo.getAmount()).isEqualTo(BigDecimal.valueOf(20));
    assertThat(updatedTo.getCurrency()).isEqualTo("HKD");
  }

  private CreateTransferRequest buildCreateTransferRequest() {
    return CreateTransferRequest.builder()
        .from("12345678")
        .to("88888888")
        .currency("HKD")
        .amount(BigDecimal.TEN)
        .build();
  }

  private Account buildFromAccount() {
    return Account.builder().id("12345678").currency("HKD").amount(BigDecimal.TEN).build();
  }

  private Account buildToAccount() {
    return Account.builder().id("88888888").currency("HKD").amount(BigDecimal.TEN).build();
  }
}
