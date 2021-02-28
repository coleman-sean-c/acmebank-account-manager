package com.acmebank.accountmanager.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ApiError {
  private final String message;
  private final boolean success = false;

  public ApiError(String message) {
    this.message = message;
  }
}
