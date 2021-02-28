package com.acmebank.accountmanager.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    public void testBuilder() {
        Account account = Account.builder()
                .id(12345678)
                .currency("HKD")
                .amount(BigDecimal.valueOf(1_000_000))
                .build();

        assertThat(account.getId()).isEqualTo(12345678);
        assertThat(account.getCurrency()).isEqualTo("HKD");
        assertThat(account.getAmount()).isEqualTo(BigDecimal.valueOf(1_000_000));
    }

}