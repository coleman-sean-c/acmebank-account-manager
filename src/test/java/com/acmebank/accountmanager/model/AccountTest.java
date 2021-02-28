package com.acmebank.accountmanager.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  public void testBuilder() {
    Account account =
        Account.builder()
            .id("12345678")
            .currency("HKD")
            .amount(BigDecimal.valueOf(1_000_000))
            .build();

    assertThat(account.getId()).isEqualTo("12345678");
    assertThat(account.getCurrency()).isEqualTo("HKD");
    assertThat(account.getAmount()).isEqualTo(BigDecimal.valueOf(1_000_000));
  }

  @Test
  public void testBuilderWithNulls() {
    final Throwable throwable = catchThrowable(() -> Account.builder().build());
    assertThat(throwable).isInstanceOf(NullPointerException.class);
  }
}
