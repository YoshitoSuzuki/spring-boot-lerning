package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner initData(UserRepository repository, PasswordEncoder passwordEncoder) {
    return args -> {
      repository.save(new UserEntity("yoshito", "Yoshito", passwordEncoder.encode("pass123"), " ADMIN"));
      repository.save(new UserEntity("tester", "Tester", passwordEncoder.encode("tester456"), "USER"));
    };
  }

}
