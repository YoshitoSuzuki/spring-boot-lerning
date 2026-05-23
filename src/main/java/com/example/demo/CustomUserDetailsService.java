package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity entity = userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + username));

    return User.builder()
        .username(entity.getId())
        .password(entity.getPassword())
        .roles(entity.getRole())
        .build();
  }
}
