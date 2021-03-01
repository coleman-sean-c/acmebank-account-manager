package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.usecase.createtransfer.CreateTransferRequest;
import com.acmebank.accountmanager.usecase.createtransfer.CreateTransferResponse;
import com.acmebank.accountmanager.usecase.createtransfer.CreateTransferUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transfers")
public class TransferController {
  @Autowired private CreateTransferUseCase createTransferUseCase;

  @PostMapping(produces = "application/json")
  public CreateTransferResponse createTransfer(@RequestBody CreateTransferRequest request) {
    return createTransferUseCase.createTransfer(request);
  }
}
