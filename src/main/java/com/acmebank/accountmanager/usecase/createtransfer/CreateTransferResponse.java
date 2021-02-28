package com.acmebank.accountmanager.usecase.createtransfer;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
public class CreateTransferResponse {
  private final boolean success;
  private final String message;
  private final String currency;
  private final BigDecimal amount;
}
