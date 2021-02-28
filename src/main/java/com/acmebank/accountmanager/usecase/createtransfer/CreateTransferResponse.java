package com.acmebank.accountmanager.usecase.createtransfer;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
public class CreateTransferResponse {
  private boolean success;
  private String message;
  private String currency;
  private BigDecimal amount;
}
