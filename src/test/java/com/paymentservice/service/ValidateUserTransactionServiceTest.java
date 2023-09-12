package com.paymentservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidateUserTransactionServiceTest {
  @InjectMocks private ValidateUserTransactionService validateUserTransactionService;

  @Test
  void shouldThrowExceptionWhenUserTypeIsNotAuthorizedToPerformOperation() {
    final User sender = createUser(UserType.SELLER, BigDecimal.ONE);

    Exception exception =
        assertThrows(
            Exception.class, () -> validateUserTransactionService.execute(sender, BigDecimal.TEN));

    assertEquals("UserType is not authorized to perform this operation.", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenBalanceIsNotEnoughToPerformOperation() {
    final BigDecimal negativeBalance = BigDecimal.TEN.negate();
    final User sender = createUser(UserType.COMMON, negativeBalance);

    Exception exception =
        assertThrows(
            Exception.class, () -> validateUserTransactionService.execute(sender, BigDecimal.TEN));

    assertEquals(
        "User does not have enough balance to perform this operation.", exception.getMessage());
  }

  @Test
  void shouldNotThrowExceptionWhenUserTypeIsCommonAndBalanceIsEnoughToPerformOperation() {
    final User sender = createUser(UserType.COMMON, BigDecimal.TEN);

    assertDoesNotThrow(() -> validateUserTransactionService.execute(sender, BigDecimal.TEN));
  }

  private User createUser(final UserType usertype, BigDecimal balance) {
    return new User(1L, "Elton", "elton123", balance, usertype, "elton@email.com");
  }
}
