package com.paymentservice.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentservice.domain.transaction.Transaction;
import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.dto.TransactionDTO;
import com.paymentservice.service.CreateTransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CreateTransactionControllerTest {
  @MockBean private CreateTransactionService createTransactionService;

  @Autowired private MockMvc mockMvc;

  @Test
  void createTransactionTest() throws Exception {
    final TransactionDTO transactionDTO = new TransactionDTO(BigDecimal.TEN, 1L, 2L);
    final String transactionDtoAsString = new ObjectMapper().writeValueAsString(transactionDTO);

    final User sender =
        new User(1L, "Elton", "elton123", BigDecimal.ONE, UserType.COMMON, "elton@email.com");
    final User receiver =
        new User(2L, "John", "john", BigDecimal.TEN, UserType.SELLER, "john@email.com");

    final Transaction transaction =
        Transaction.builder()
            .id(3L)
            .amount(BigDecimal.ONE)
            .sender(sender)
            .receiver(receiver)
            .timestamp(LocalDateTime.now())
            .build();

    when(createTransactionService.execute(transactionDTO)).thenReturn(transaction);

    mockMvc
        .perform(
            post("/transactions").contentType(APPLICATION_JSON).content(transactionDtoAsString))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("id", is(3)))
        .andExpect(jsonPath("amount", is(1)))
        .andExpect(jsonPath("timestamp").isNotEmpty())
        .andExpect(jsonPath("sender.id", is(1)))
        .andExpect(jsonPath("sender.name", is("Elton")))
        .andExpect(jsonPath("sender.username", is("elton123")))
        .andExpect(jsonPath("sender.balance", is(1)))
        .andExpect(jsonPath("sender.userType", is("COMMON")))
        .andExpect(jsonPath("sender.email", is("elton@email.com")))
        .andExpect(jsonPath("receiver.id", is(2)))
        .andExpect(jsonPath("receiver.name", is("John")))
        .andExpect(jsonPath("receiver.username", is("john")))
        .andExpect(jsonPath("receiver.balance", is(10)))
        .andExpect(jsonPath("receiver.userType", is("SELLER")))
        .andExpect(jsonPath("receiver.email", is("john@email.com")));
  }
}
