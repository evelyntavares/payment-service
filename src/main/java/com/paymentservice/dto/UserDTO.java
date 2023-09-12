package com.paymentservice.dto;

import com.paymentservice.domain.user.UserType;
import java.math.BigDecimal;

public record UserDTO(String name, BigDecimal balance, String email, UserType userType) {}
