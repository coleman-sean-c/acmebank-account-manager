package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.usecase.getbalance.GetBalanceRequest;
import com.acmebank.accountmanager.usecase.getbalance.GetBalanceResponse;
import com.acmebank.accountmanager.usecase.getbalance.GetBalanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
