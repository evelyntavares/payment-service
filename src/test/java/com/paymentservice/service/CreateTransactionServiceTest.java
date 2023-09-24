package com.paymentservice.service;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.paymentservice.domain.transaction.Transaction;
import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.dto.TransactionDTO;
import com.paymentservice.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTransactionServiceTest {
  @InjectMocks private CreateTransactionService createTransactionService;
  @Mock private TransactionRepository transactionRepository;
  @Mock private GetUserService getUserService;
  @Mock private ValidateUserTransactionService validateUserTransactionService;
  @Mock private UpdateUserService updateUserService;

  @Test
  void shouldCreateTransactionGivenATransactionDtoObject() throws Exception {
    final Long senderId = 1L;
    final Long receiverId = 2L;
    final TransactionDTO transactionDTO = new TransactionDTO(BigDecimal.TEN, senderId, receiverId);

    final User sender =
        new User(1L, "Elton", "elton123", BigDecimal.TEN, UserType.SELLER, "elton@email.com");
    final User receiver =
        new User(2L, "David", "david007", BigDecimal.TEN, UserType.COMMON, "david@email.com");

    when(getUserService.execute(senderId)).thenReturn(sender);
    when(getUserService.execute(receiverId)).thenReturn(receiver);

    when(validateUserTransactionService.execute(sender, BigDecimal.TEN)).thenReturn(true);

    final Transaction transactionExpected = createTransaction(transactionDTO, sender, receiver);

    final ArgumentCaptor<Transaction> transactionCaptor =
        ArgumentCaptor.forClass(Transaction.class);

    when(transactionRepository.save(any())).thenReturn(transactionExpected);

    doNothing().when(updateUserService).execute(List.of(sender, receiver));

    final Transaction transactionResult = createTransactionService.execute(transactionDTO);

    verify(transactionRepository).save(transactionCaptor.capture());
    final Transaction valueCaptor = transactionCaptor.getValue();
    assertEquals(valueCaptor.getSender(), sender);
    assertEquals(valueCaptor.getReceiver(), receiver);
    assertEquals(valueCaptor.getAmount(), BigDecimal.TEN);
    assertThat(valueCaptor.getTimestamp()).isNotNull();

    assertEquals(transactionResult.getSender(), sender);
    assertEquals(transactionResult.getReceiver(), receiver);
    assertEquals(transactionResult.getAmount(), BigDecimal.TEN);
    assertThat(valueCaptor.getTimestamp()).isNotNull();

    verify(updateUserService, times(1)).execute(List.of(sender, receiver));
  }

  @Test
  void shouldNotCreateTransactionWhenTransactionIsNotAuthorized() throws Exception {
    final Long senderId = 1L;
    final Long receiverId = 2L;
    final TransactionDTO transactionDTO = new TransactionDTO(BigDecimal.TEN, senderId, receiverId);

    final User sender =
        new User(1L, "Elton", "elton123", BigDecimal.TEN, UserType.SELLER, "elton@email.com");
    final User receiver =
        new User(2L, "David", "david007", BigDecimal.TEN, UserType.COMMON, "david@email.com");

    when(getUserService.execute(senderId)).thenReturn(sender);
    when(getUserService.execute(receiverId)).thenReturn(receiver);
    when(validateUserTransactionService.execute(sender, BigDecimal.TEN)).thenReturn(false);

    final Transaction transactionResult = createTransactionService.execute(transactionDTO);

    verifyNoInteractions(transactionRepository);
    verifyNoInteractions(updateUserService);

    assertTrue(isNull(transactionResult.getId()));
  }

  private Transaction createTransaction(
      final TransactionDTO transactionDto, final User sender, final User receiver) {
    return Transaction.builder()
        .sender(sender)
        .receiver(receiver)
        .amount(transactionDto.value())
        .timestamp(LocalDateTime.now())
        .build();
  }
}
