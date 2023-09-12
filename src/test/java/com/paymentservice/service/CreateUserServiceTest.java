package com.paymentservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.dto.UserDTO;
import com.paymentservice.repository.UserRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

  @Mock private UserRepository repository;
  @InjectMocks private CreateUserService createUserService;

  @Test
  void shouldReturnUserGivenAnUserDTO() {
    final UserDTO userDTO = new UserDTO("John", BigDecimal.TEN, UserType.SELLER, "john@email.com");

    final User expectedUser =
        new User(1L, "John", "john", BigDecimal.TEN, UserType.SELLER, "john@email.com");
    when(repository.save(any())).thenReturn(expectedUser);

    final User userResult = createUserService.execute(userDTO);

    assertEquals("John", userResult.getName());
    assertEquals("john", userResult.getUsername());
    assertEquals(BigDecimal.TEN, userResult.getBalance());
    assertEquals("john@email.com", userResult.getEmail());
    assertEquals(UserType.SELLER, userResult.getUserType());

    verify(repository, times(1)).save(any());

    assertEquals(expectedUser, userResult);
  }
}
