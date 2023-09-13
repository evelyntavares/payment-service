package com.paymentservice.controller;

import com.paymentservice.domain.user.User;
import com.paymentservice.service.GetUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class GetUserController {

  private final GetUserService getUserService;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    final User user = getUserService.execute(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> getUsers() {
    final List<User> users = getUserService.execute();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
