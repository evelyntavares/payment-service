package com.paymentservice.client;

import com.paymentservice.dto.client.authorization.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorizationClient", url = "${services.authorization-service}")
public interface AuthorizationClient {

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  AuthorizationResponse isAuthorized();
}
