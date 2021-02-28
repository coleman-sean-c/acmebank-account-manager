package com.acmebank.accountmanager.usecase.getbalance;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class GetBalanceResponse {
  private boolean success;
  private BigDecimal amount;
  private String currency;
  private String message;
}
