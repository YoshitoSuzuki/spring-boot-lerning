package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  private String id;
  private String name;
  private String password;
  private String role;

  public UserEntity() {
  }

  public UserEntity(String id, String name, String password, String role) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }
}
