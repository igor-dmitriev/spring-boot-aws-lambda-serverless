package com.amazonaws.serverless.sample.springboot3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello 2";
  }

}