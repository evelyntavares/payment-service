package com.paymentservice.service.impl;

import static com.paymentservice.util.NormalizeStringUtils.normalize;

import com.paymentservice.domain.user.User;
import com.paymentservice.dto.UserDTO;
import com.paymentservice.repository.UserRepository;
import com.paymentservice.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {
  private final UserRepository repository;

  public User execute(UserDTO userDTO) {
    final String username = normalize(userDTO.name());

    final User user = new User();
    user.setUsername(username);
    user.setName(userDTO.name());
    user.setBalance(userDTO.balance());
    user.setEmail(userDTO.email());
    user.setUserType(userDTO.userType());
    return repository.save(user);
  }
}
