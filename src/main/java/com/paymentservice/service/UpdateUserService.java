package com.paymentservice.service;

import com.paymentservice.domain.user.User;
import java.util.List;

public interface UpdateUserService {

  void execute(User user);

  void execute(List<User> users);
}
