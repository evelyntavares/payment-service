package com.paymentservice.service.impl;

import com.paymentservice.domain.user.User;
import com.paymentservice.repository.UserRepository;
import com.paymentservice.service.GetUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService {

  private final UserRepository repository;

  public User execute(Long id) {
    return repository.findUserById(id).orElseThrow();
  }

  public List<User> execute() {
    return repository.findAll();
  }
}
