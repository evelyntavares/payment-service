package com.paymentservice.dto;

import com.paymentservice.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record UserDTO(
    @NotBlank(message = "Name cannot be blank.") String name,
    BigDecimal balance,
    UserType userType,
    String email) {}
