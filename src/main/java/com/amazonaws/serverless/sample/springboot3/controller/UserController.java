package com.amazonaws.serverless.sample.springboot3.controller;

import com.amazonaws.serverless.sample.springboot3.entity.UserEntity;
import com.amazonaws.serverless.sample.springboot3.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @GetMapping("/users")
  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }
}
