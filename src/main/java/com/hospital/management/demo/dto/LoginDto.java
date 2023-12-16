package com.hospital.management.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginDto {
  @NotBlank(message = "Email cannot be empty")
  @Email(message = "Email Format incorect")
  private String email;
  @NotBlank
  private String password;

  public String getEmail() {
    return email;
  }

  public LoginDto setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public LoginDto setPassword(String password) {
    this.password = password;
    return this;
  }
}
