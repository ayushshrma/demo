package com.hospital.management.demo.service.impl;

import static java.util.stream.Collectors.toList;

import com.hospital.management.demo.config.SecurityConstants;
import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.token.AccessToken;
import com.hospital.management.demo.entity.token.BlacklistedJwtToken;
import com.hospital.management.demo.entity.token.RefreshToken;
import com.hospital.management.demo.exception.InvalidPropertyException;
import com.hospital.management.demo.repositories.AccessTokenRepository;
import com.hospital.management.demo.repositories.BlacklistTokenRepository;
import com.hospital.management.demo.repositories.RefreshTokenRepository;
import com.hospital.management.demo.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JwtTokenServiceImpl implements JwtTokenService {
  @Autowired
  private AccessTokenRepository accessTokenRepository;
  @Autowired
  private BlacklistTokenRepository blacklistTokenRepository;
  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  public String generateToken(HospitalStaff user, Date expireDate) {
    String email = user.getEmail();
    Date currentDate = new Date();
    String token = Jwts.builder().setSubject(email).setIssuedAt(new Date())
        .setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
        .compact();
    return token;
  }

  public void saveAccessToken(String token, HospitalStaff user, Date expiresAt) {
    AccessToken tokenObj = new AccessToken();
    tokenObj.setToken(token);
    tokenObj.setExpiresAt(expiresAt);
    tokenObj.setUser(user);
    accessTokenRepository.save(tokenObj);
  }

  public void saveRefreshToken(String token, HospitalStaff user, Date expiresAt) {
    RefreshToken tokenObj = new RefreshToken();
    tokenObj.setToken(token);
    tokenObj.setExpiresAt(expiresAt);
    tokenObj.setUser(user);
    refreshTokenRepository.save(tokenObj);
  }

  public Boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
      if (blacklistTokenRepository.existsById(token)) {
        throw new BadCredentialsException("User has logged out");
      }
      return true;
    } catch (ExpiredJwtException e) {
      if (isRefreshToken(token)) {
        refreshTokenRepository.deleteById(token);
      } else if (isAccessToken(token)) {
        accessTokenRepository.deleteById(token);
      }
      throw new BadCredentialsException("JWT has expired");

    } catch (JwtException e) {
      throw new BadCredentialsException("JWT is incorrect");

    }
  }

  public Boolean isRefreshToken(String token) {
    return refreshTokenRepository.existsById(token);
  }

  public Boolean isAccessToken(String token) {
    return accessTokenRepository.existsById(token);
  }

  @Transactional
  public Boolean disableTokens(String token) {
    if (accessTokenRepository.existsById(token)) {
      AccessToken accessToken = accessTokenRepository.findById(token).get();
      HospitalStaff user = accessToken.getUser();
      disableAccessTokens(user);
      refreshTokenRepository.deleteAllByUser(accessToken.getUser());
      return true;
    }
    return false;
  }

  @Transactional
  public void disableAccessTokens(HospitalStaff user) {
    accessTokenRepository.findAllByUser(user).stream().filter(e -> e != null)
        .forEach(e -> blacklistTokenRepository.save(new BlacklistedJwtToken(e)));
    accessTokenRepository.deleteAllByUser(user);
  }


  public String refreshTheAccessToken(String refreshToken) {
    RefreshToken refreshTokenObj = refreshTokenRepository.findById(refreshToken).orElseThrow(
        () -> new InvalidPropertyException("The given Refresh Token is invalid or expired"));
    if (validateToken(refreshTokenObj.getToken())) {
      disableAccessTokens(refreshTokenObj.getUser());
      String accessToken = generateToken(refreshTokenObj.getUser(),
          new Date(new Date().getTime() + SecurityConstants.ACCESS_EXPIRATION));
      saveAccessToken(accessToken, refreshTokenObj.getUser(),
          new Date(new Date().getTime() + SecurityConstants.ACCESS_EXPIRATION));
      return accessToken;
    }
    return null;
  }

  public HospitalStaff getUserFomJwt(String token) {
    return accessTokenRepository.findById(token).get().getUser();
  }

  public String getEmailFomJwt(String token){
    Claims claims=Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET)
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }
}