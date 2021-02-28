package com.acmebank.accountmanager.usecases.getbalance;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
@Getter
@Builder
public class GetBalanceRequest {
  private @NonNull String id;
}
