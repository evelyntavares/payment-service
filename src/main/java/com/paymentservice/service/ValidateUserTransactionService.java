package com.paymentservice.service;

import com.paymentservice.domain.user.User;
import java.math.BigDecimal;

public interface ValidateUserTransactionService {

  boolean execute(final User sender, final BigDecimal amount) throws Exception;
}
