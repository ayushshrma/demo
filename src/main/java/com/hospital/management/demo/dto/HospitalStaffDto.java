package com.hospital.management.demo.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class HospitalStaffDto {
  @Email(message = "Enter a valid email.")
  @NotEmpty
  private String email;
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}",
      message = "Enter a valid password. Password must contain 8-15 characters " +
          "with at least 1 lower case, 1 upper case, 1 special character, and 1 Number.")
  private String password;
  @NotEmpty(message = "confirmPassword cannot be empty")
  @NotNull(message = "confirmPassword cannot be null")
  private String confirmPassword;

  @NotEmpty(message = "First Name is mandatory.")
  @Size(min = 2, max = 30, message = "Must contain 2-15 characters.")
  @Pattern(regexp="(^[A-Za-z]*$)",message = "Invalid Input. " +
      "First Name can only contain alphabets.")
  private String firstName;

  @NotEmpty(message = "Last Name is mandatory.")
  @Size(min = 2, max = 30, message = "Must contain 2-15 characters.")
  @Pattern(regexp="(^[A-Za-z]*$)",message = "Invalid Input. " +
      "Last Name can only contain alphabets.")
  private String lastName;
  @NotEmpty(message = "designation cannot be empty")
  @NotNull(message = "designation cannot be null")
  private String designation;

  public String getEmail() {
    return email;
  }

  public HospitalStaffDto setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public HospitalStaffDto setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public HospitalStaffDto setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public HospitalStaffDto setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public HospitalStaffDto setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getDesignation() {
    return designation;
  }

  public HospitalStaffDto setDesignation(String designation) {
    this.designation = designation;
    return this;
  }
}
