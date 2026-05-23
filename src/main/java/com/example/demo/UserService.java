package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserProfile findUserById(String id) {
    return userRepository.findById(id)
        .map(entity -> new UserProfile(entity.getId(), entity.getName(), entity.getRole()))
        .orElse(new UserProfile(id, "Guest User", "Viewer"));
  }
}
