package com.amazonaws.serverless.sample.springboot3.controller;

import com.amazonaws.serverless.sample.springboot3.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
  private final UserRepository userRepository;

  public HealthCheckController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/health")
  public void health() {
    userRepository.count();
  }
}
