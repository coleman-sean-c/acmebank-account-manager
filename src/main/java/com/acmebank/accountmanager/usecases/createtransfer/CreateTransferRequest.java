package com.acmebank.accountmanager.usecases.createtransfer;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
@Getter
@Builder
public class CreateTransferRequest {
  @NonNull private String from;
  @NonNull private String to;
  @NonNull private String currency;
  @NonNull private BigDecimal amount;
}
