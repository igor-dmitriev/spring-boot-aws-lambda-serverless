package com.amazonaws.serverless.sample.springboot3.controller;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FlywaMigrationController {
  private final Flyway flyway;

  @PostMapping("/migrate")
  public void migrate() {
    flyway.migrate();
  }
}