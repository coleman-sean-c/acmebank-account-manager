package com.acmebank.accountmanager.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Builder
public class Account {
    private long id;
    private String currency;
    private BigDecimal amount;
}
