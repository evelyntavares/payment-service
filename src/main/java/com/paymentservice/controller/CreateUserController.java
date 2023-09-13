package com.paymentservice.controller;

import com.paymentservice.domain.user.User;
import com.paymentservice.dto.UserDTO;
import com.paymentservice.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class CreateUserController {

  private final CreateUserService createUserService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> createUser(@Validated @RequestBody UserDTO userDTO) {
    final User user = createUserService.execute(userDTO);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
}
