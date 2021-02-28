package com.acmebank.accountmanager.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
@Getter
@Builder
public class Account {
  @NonNull private final String id;
  @NonNull private final String currency;
  @NonNull private final BigDecimal amount;
}
