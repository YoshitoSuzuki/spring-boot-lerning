package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HelloController {

  private final UserService userService;

  public HelloController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello Spring Boot! My first web server!";
  }

  @GetMapping("/api/user/{id}")
  public UserProfile getUser(@PathVariable String id) {
    return userService.findUserById(id);
  }

}
