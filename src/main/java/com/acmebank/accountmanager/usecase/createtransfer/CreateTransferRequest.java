package com.acmebank.accountmanager.usecase.createtransfer;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
@Getter
@Builder
public class CreateTransferRequest {
  @NonNull private final String from;
  @NonNull private final String to;
  @NonNull private final String currency;
  @NonNull private final BigDecimal amount;
}
