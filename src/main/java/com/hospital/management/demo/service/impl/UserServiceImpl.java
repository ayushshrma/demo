package com.hospital.management.demo.service.impl;

import com.hospital.management.demo.config.SecurityConstants;
import com.hospital.management.demo.dto.AuthResponseDto;
import com.hospital.management.demo.dto.HospitalStaffDto;
import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.Role;
import com.hospital.management.demo.exception.InvalidPropertyException;
import com.hospital.management.demo.exception.PropertyAccessDeniedException;
import com.hospital.management.demo.exception.PropertyAlreadyExistsException;
import com.hospital.management.demo.exception.PropertyNotFoundException;
import com.hospital.management.demo.repositories.HospitalStaffRepository;
import com.hospital.management.demo.repositories.RoleRepository;
import com.hospital.management.demo.service.JwtTokenService;
import com.hospital.management.demo.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  HospitalStaffRepository hospitalStaffRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenService jwtTokenService;


  public boolean registerHospitalStaff(HospitalStaffDto hospitalStaffDto) {
    if (hospitalStaffRepository.existsByEmail(hospitalStaffDto.getEmail())) {
      throw new PropertyAlreadyExistsException("Email is taken");
    }
    if(!checkDesignation(hospitalStaffDto)){
      throw new InvalidPropertyException("Invalid Designation");
    }
    HospitalStaff user = new HospitalStaff();
    user.setEmail(hospitalStaffDto.getEmail().toLowerCase());
    user.setPassword(passwordEncoder.encode(hospitalStaffDto.getPassword()));
    user.setFirstName(hospitalStaffDto.getFirstName());
    user.setLastName(hospitalStaffDto.getLastName());
    Role role = roleRepository.findByAuthority(hospitalStaffDto.getDesignation());
    user.setRole(role);
    hospitalStaffRepository.save(user);
    return true;
  }

  private boolean checkDesignation(HospitalStaffDto hospitalStaffDto) {
    return hospitalStaffDto.getDesignation().equalsIgnoreCase("nurse")
        || hospitalStaffDto.getDesignation().equalsIgnoreCase("doctor")
        || hospitalStaffDto.getDesignation().equalsIgnoreCase("Receptionist");
  }
  public AuthResponseDto userLogin(String email, String password) {
    HospitalStaff user = hospitalStaffRepository.findByEmail(email)
        .orElseThrow(() -> new PropertyNotFoundException("Username Not Found"));
    if(!passwordEncoder.matches(password,user.getPassword())){
      throw new PropertyAccessDeniedException("Email or Password is invalid");
    }
    Authentication authentication =authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email,
            password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    Date now=new Date();
    String accessToken= jwtTokenService.generateToken(user, new Date(now.getTime()+
        SecurityConstants.ACCESS_EXPIRATION));
    jwtTokenService.saveAccessToken(accessToken,user,new Date(new Date().getTime()+
        SecurityConstants.ACCESS_EXPIRATION));
    return new AuthResponseDto(accessToken);
  }
  public boolean userLogout(String token){
    jwtTokenService.disableTokens(token);
    return true;
  }
}
