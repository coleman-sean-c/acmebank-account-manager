package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.usecases.getbalance.GetBalanceRequest;
import com.acmebank.accountmanager.usecases.getbalance.GetBalanceResponse;
import com.acmebank.accountmanager.usecases.getbalance.GetBalanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountController {

  @Autowired private GetBalanceUseCase getBalanceUseCase;

  @GetMapping(path = "/{id}", produces = "application/json")
  public GetBalanceResponse getBalance(@PathVariable String id) {
    GetBalanceRequest request = GetBalanceRequest.builder().id(id).build();
    return getBalanceUseCase.getBalance(request);
  }
}
