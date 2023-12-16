package com.hospital.management.demo.service;

import com.hospital.management.demo.entity.HospitalStaff;
import java.util.Date;

public interface JwtTokenService {
    String generateToken(HospitalStaff user, Date expireDate);
    void saveAccessToken(String token, HospitalStaff user,Date expiresAt);
    void saveRefreshToken(String token,HospitalStaff user,Date expiresAt);
    Boolean validateToken(String token);
    Boolean disableTokens(String token);
    void disableAccessTokens(HospitalStaff user);
    String refreshTheAccessToken(String refreshToken);
}