package com.paymentservice.service;

import com.paymentservice.domain.transaction.Transaction;
import com.paymentservice.domain.user.User;
import com.paymentservice.dto.TransactionDTO;
import com.paymentservice.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionService {

  private final TransactionRepository transactionRepository;
  private final GetUserService getUserService;
  private final ValidateUserTransactionService validateUserTransactionService;
  private final UpdateUserService updateUserService;

  public Transaction execute(TransactionDTO transactionDto) throws Exception {
    final User sender = getUserService.execute(transactionDto.senderId());
    final User receiver = getUserService.execute(transactionDto.receiverId());

    final boolean isAuthorized =
        validateUserTransactionService.execute(sender, transactionDto.value());

    Transaction transactionSaved = new Transaction();

    if (isAuthorized) {
      final Transaction transaction = createTransaction(transactionDto, sender, receiver);
      transactionSaved = transactionRepository.save(transaction);
      updateUsersBalance(transactionDto, sender, receiver);
    }

    return transactionSaved;
  }

  private void updateUsersBalance(
      final TransactionDTO transactionDto, final User sender, final User receiver) {
    sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
    receiver.setBalance(receiver.getBalance().add(transactionDto.value()));
    updateUserService.execute(List.of(sender, receiver));
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
