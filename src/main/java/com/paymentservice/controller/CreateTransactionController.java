package com.paymentservice.controller;

import com.paymentservice.domain.transaction.Transaction;
import com.paymentservice.dto.TransactionDTO;
import com.paymentservice.service.CreateTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class CreateTransactionController {

  private final CreateTransactionService createTransactionService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO)
      throws Exception {
    final Transaction transaction = createTransactionService.execute(transactionDTO);
    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }
}
