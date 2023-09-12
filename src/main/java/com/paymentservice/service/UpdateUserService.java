package com.paymentservice.service;

import com.paymentservice.domain.user.User;
import com.paymentservice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {
  private final UserRepository repository;

  public void execute(User user) {
    repository.save(user);
  }

  public void execute(List<User> users) {
    repository.saveAll(users);
  }
}
