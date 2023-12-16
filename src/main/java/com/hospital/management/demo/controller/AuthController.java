package com.hospital.management.demo.controller;


import static org.springframework.util.StringUtils.hasText;

import com.hospital.management.demo.dto.AuthResponseDto;
import com.hospital.management.demo.dto.HospitalStaffDto;
import com.hospital.management.demo.dto.LoginDto;
import com.hospital.management.demo.exception.InvalidPropertyException;
import com.hospital.management.demo.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital-management")
public class AuthController {

  @Autowired
  private UserService userService;


  @PostMapping(value = "/register")
  public ResponseEntity registerStaff(@Valid @RequestBody HospitalStaffDto hospitalStaffDto){
    if(hospitalStaffDto.getConfirmPassword().compareTo(hospitalStaffDto.getPassword())!=0){
      throw new InvalidPropertyException("Password does not match with Confirm Password");
    }
    if(userService.registerHospitalStaff(hospitalStaffDto)) {
      return ResponseEntity.ok(hospitalStaffDto.getFirstName() + "Register Successfully");
    }
    return ResponseEntity.ok(hospitalStaffDto.getFirstName() + "Registeration failed");
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto loginDto){
    AuthResponseDto responseDto=userService.userLogin(loginDto.getEmail(),loginDto.getPassword());
    return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
  }
  @GetMapping("/logout")
  public ResponseEntity<String> logout(@RequestHeader String Authorization){
    if(hasText(Authorization) && Authorization.startsWith("Bearer ")){
      if(userService.userLogout(Authorization.substring(7,Authorization.length()))){
        return new ResponseEntity<>("Logged Out", HttpStatus.ACCEPTED);
      }
      else{
        return new ResponseEntity<>("Not Logged Out", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
