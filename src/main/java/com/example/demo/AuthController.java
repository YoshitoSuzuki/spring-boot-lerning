package com.example.demo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService,
      JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  record LoginRequest(String username, String password) {
  }

  record LoginResponse(String token) {
  }

  @PostMapping("/api/login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));

    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
    final String jwt = jwtUtil.generateToken(userDetails.getUsername());

    return new LoginResponse(jwt);
  }
}
