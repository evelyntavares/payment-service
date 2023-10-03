package com.paymentservice.service.impl;

import com.paymentservice.domain.user.User;
import com.paymentservice.repository.UserRepository;
import com.paymentservice.service.UpdateUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserServiceImpl implements UpdateUserService {
  private final UserRepository repository;

  public void execute(User user) {
    repository.save(user);
  }

  public void execute(List<User> users) {
    repository.saveAll(users);
  }
}
