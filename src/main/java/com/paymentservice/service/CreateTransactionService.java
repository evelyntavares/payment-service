package com.paymentservice.service;

import com.paymentservice.domain.transaction.Transaction;
import com.paymentservice.dto.TransactionDTO;

public interface CreateTransactionService {

    Transaction execute(TransactionDTO transactionDto) throws Exception;
}
