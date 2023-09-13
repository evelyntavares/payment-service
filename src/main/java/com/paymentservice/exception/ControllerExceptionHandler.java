package com.paymentservice.exception;

import com.paymentservice.dto.ExceptionDTO;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ExceptionDTO duplicatedUsernameHandler(final DataIntegrityViolationException exception) {
    return new ExceptionDTO("Duplicate name for user. Please provide another name.");
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ExceptionDTO userNotFoundHandler(final NoSuchElementException exception) {
    return new ExceptionDTO("User not found.");
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ExceptionDTO exceptionHandler(final Exception exception) {
    return new ExceptionDTO("Contact the system admin for more info.");
  }
}
