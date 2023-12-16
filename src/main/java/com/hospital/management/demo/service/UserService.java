package com.hospital.management.demo.service;

import com.hospital.management.demo.dto.AuthResponseDto;
import com.hospital.management.demo.dto.HospitalStaffDto;

public interface UserService {

  boolean registerHospitalStaff(HospitalStaffDto hospitalStaffDto);
  AuthResponseDto userLogin(String email, String password);

  boolean userLogout(String substring);
}
