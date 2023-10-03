package com.paymentservice.service;

import com.paymentservice.domain.user.User;
import java.util.List;

public interface GetUserService {

  User execute(Long id);

  List<User> execute();
}
