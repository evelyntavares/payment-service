package com.paymentservice.service;

import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateUserTransactionService {

  public void execute(final User sender, final BigDecimal amount) throws Exception {
    validateUserType(sender);
    validateBalance(sender.getBalance(), amount);
  }

  private void validateUserType(final User sender) throws Exception {
    if (UserType.SELLER.equals(sender.getUserType())) {
      throw new Exception("UserType is not authorized to perform this operation.");
    }
  }

  private void validateBalance(final BigDecimal userBalance, final BigDecimal amount)
      throws Exception {
    if (userBalance.compareTo(amount) < 0) {
      throw new Exception("User does not have enough balance to perform this operation.");
    }
  }
}
