package com.paymentservice.service;

import com.paymentservice.domain.user.User;
import com.paymentservice.dto.UserDTO;

public interface CreateUserService {
  User execute(UserDTO userDTO);
}
