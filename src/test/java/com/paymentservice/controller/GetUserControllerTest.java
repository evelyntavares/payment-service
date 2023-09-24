package com.paymentservice.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.service.GetUserService;
import java.math.BigDecimal;
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
class GetUserControllerTest extends IntegrationTestConfig {
  @MockBean private GetUserService getUserService;

  @Autowired private MockMvc mockMvc;

  @Test
  void getUserByIdTest() throws Exception {
    final Long userId = 1L;

    final User user =
        new User(1L, "Aaron", "aaron", BigDecimal.ONE, UserType.COMMON, "aaron@email.com");

    when(getUserService.execute(userId)).thenReturn(user);

    mockMvc
        .perform(get("/users/{id}", userId).contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("id", is(1)))
        .andExpect(jsonPath("name", is("Aaron")))
        .andExpect(jsonPath("username", is("aaron")))
        .andExpect(jsonPath("balance", is(1)))
        .andExpect(jsonPath("userType", is(UserType.COMMON.name())))
        .andExpect(jsonPath("email", is("aaron@email.com")));
  }
}
