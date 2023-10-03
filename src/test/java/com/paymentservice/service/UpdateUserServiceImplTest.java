package com.paymentservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.paymentservice.domain.user.User;
import com.paymentservice.domain.user.UserType;
import com.paymentservice.repository.UserRepository;
import com.paymentservice.service.impl.UpdateUserServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceImplTest {

  @Mock private UserRepository repository;
  @InjectMocks private UpdateUserServiceImpl updateUserService;

  @Test
  void shouldUpdateASingleUser() {
    final User user =
        new User(1L, "John", "john", BigDecimal.TEN, UserType.SELLER, "john@email.com");
    when(repository.save(any())).thenReturn(user);

    updateUserService.execute(user);

    verify(repository, times(1)).save(user);
  }

  @Test
  void shouldUpdateAListOfUsers() {
    final User user1 =
        new User(1L, "John", "john", BigDecimal.TEN, UserType.SELLER, "john@email.com");

    final User user2 =
        new User(2L, "Aaron", "aaron", BigDecimal.ONE, UserType.COMMON, "aaron@email.com");

    final List<User> users = List.of(user1, user2);
    when(repository.saveAll(users)).thenReturn(users);

    updateUserService.execute(users);

    verify(repository, times(1)).saveAll(users);
  }
}
