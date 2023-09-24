package com.paymentservice.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.dto.UserDTO;
import com.paymentservice.service.CreateUserService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CreateUserControllerTest extends IntegrationTestConfig {
  @MockBean private CreateUserService createUserService;

  @Autowired private MockMvc mockMvc;

  @Test
  void createUserTest() throws Exception {
    UserDTO userDTO = new UserDTO("Brad", BigDecimal.TEN, UserType.COMMON, "brad@email.com");
    final String userDtoAsString = new ObjectMapper().writeValueAsString(userDTO);

    User userResponse =
        new User(1L, "Brad", "Brad", BigDecimal.TEN, UserType.COMMON, "brad@email.com");

    when(createUserService.execute(userDTO)).thenReturn(userResponse);

    mockMvc
        .perform(post("/users").contentType(APPLICATION_JSON).content(userDtoAsString))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("id", is(1)))
        .andExpect(jsonPath("name", is("Brad")))
        .andExpect(jsonPath("username", is("Brad")))
        .andExpect(jsonPath("balance", is(10)))
        .andExpect(jsonPath("userType", is(UserType.COMMON.name())))
        .andExpect(jsonPath("email", is("brad@email.com")));
  }
}
