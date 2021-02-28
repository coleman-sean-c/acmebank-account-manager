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
  @NonNull private String id;
  @NonNull private String currency;
  @NonNull private BigDecimal amount;
}
