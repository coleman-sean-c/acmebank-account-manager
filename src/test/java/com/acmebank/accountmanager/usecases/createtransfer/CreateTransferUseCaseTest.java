package com.acmebank.accountmanager.usecases.createtransfer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
}
