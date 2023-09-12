package com.paymentservice.repository;

import com.paymentservice.domain.transaction.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {}
