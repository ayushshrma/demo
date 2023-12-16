package com.hospital.management.demo.dto;

public class AuthResponseDto {
  private String accessToken;

  public AuthResponseDto(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public AuthResponseDto setAccessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }
}
