package com.amazonaws.serverless.sample.springboot3.controller;

import org.flywaydb.core.Flyway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlywaMigrationController {
  private final Flyway flyway;

  public FlywaMigrationController(Flyway flyway) {
    this.flyway = flyway;
  }

  @PostMapping("/migrate")
  public void migrate() {
    flyway.migrate();
  }
}