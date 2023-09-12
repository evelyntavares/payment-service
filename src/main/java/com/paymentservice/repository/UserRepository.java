package com.paymentservice.repository;

import com.paymentservice.domain.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findUserById(Long id);

  Optional<User> findUserByUsername(String nickname);

  List<User> findAll();
}
