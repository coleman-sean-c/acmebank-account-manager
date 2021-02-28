package com.acmebank.accountmanager.usecase.getbalance;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class GetBalanceResponse {
  private final boolean success;
  private final BigDecimal amount;
  private final String currency;
  private final String message;
}
