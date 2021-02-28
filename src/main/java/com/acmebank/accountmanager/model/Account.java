package com.acmebank.accountmanager.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Builder
public class Account {
    @NonNull
    private long id;

    @NonNull
    private String currency;

    @NonNull
    private BigDecimal amount;
}
