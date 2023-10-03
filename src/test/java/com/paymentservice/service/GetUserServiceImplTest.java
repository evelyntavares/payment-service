package com.paymentservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserServiceImplTest {
  @Mock private UserRepository repository;

  @InjectMocks private GetUserServiceImpl getUserService;

  @Test
  void shouldFindUserById() {
    final Long id = 1L;
    final User expectedUser = createUser();

    when(repository.findUserById(id)).thenReturn(Optional.of(expectedUser));

    final User userResult = getUserService.execute(id);

    assertEquals(1L, userResult.getId());
    assertEquals("Elton", userResult.getName());
    assertEquals("elton123", userResult.getUsername());
    assertEquals(BigDecimal.ONE, userResult.getBalance());
    assertEquals(UserType.SELLER, userResult.getUserType());
    assertEquals("elton@email.com", userResult.getEmail());

    verify(repository, times(1)).findUserById(id);
  }

  @Test
  void shouldFindAllUsers() {
    final User expectedUser1 = createUser();
    final User expectedUser2 =
        new User(2L, "David", "david007", BigDecimal.ZERO, UserType.COMMON, "david@email.com");

    when(repository.findAll()).thenReturn(List.of(expectedUser1, expectedUser2));

    final List<User> userResults = getUserService.execute();

    assertEquals(2, userResults.size());
    assertEquals(1L, userResults.get(0).getId());
    assertEquals("Elton", userResults.get(0).getName());
    assertEquals("elton123", userResults.get(0).getUsername());
    assertEquals(BigDecimal.ONE, userResults.get(0).getBalance());
    assertEquals(UserType.SELLER, userResults.get(0).getUserType());
    assertEquals("elton@email.com", userResults.get(0).getEmail());

    assertEquals(2L, userResults.get(1).getId());
    assertEquals("David", userResults.get(1).getName());
    assertEquals("david007", userResults.get(1).getUsername());
    assertEquals(BigDecimal.ZERO, userResults.get(1).getBalance());
    assertEquals(UserType.COMMON, userResults.get(1).getUserType());
    assertEquals("david@email.com", userResults.get(1).getEmail());

    verify(repository, times(1)).findAll();
  }

  private User createUser() {
    return new User(1L, "Elton", "elton123", BigDecimal.ONE, UserType.SELLER, "elton@email.com");
  }
}
