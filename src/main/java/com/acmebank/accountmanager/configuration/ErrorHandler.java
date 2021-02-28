package com.acmebank.accountmanager.configuration;

import com.acmebank.accountmanager.usecase.exception.AccountNotFoundException;
import com.acmebank.accountmanager.usecase.exception.IncorrectCurrencyException;
import com.acmebank.accountmanager.usecase.exception.InsufficientBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ApiError handleAccountNotFoundException(AccountNotFoundException e) {
    return new ApiError("Account not found.");
  }

  @ExceptionHandler(IncorrectCurrencyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ApiError handleIncorrectCurrencyException(IncorrectCurrencyException e) {
    return new ApiError("Cannot complete transaction: Currency does not match.");
  }

  @ExceptionHandler(InsufficientBalanceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ApiError handleInsufficientBalanceException(InsufficientBalanceException e) {
    return new ApiError("Cannot complete transaction: Insufficient Balance.");
  }
}
