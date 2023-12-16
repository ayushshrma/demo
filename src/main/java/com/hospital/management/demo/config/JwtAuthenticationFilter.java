package com.hospital.management.demo.config;

import com.hospital.management.demo.service.impl.JwtTokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  JwtTokenServiceImpl jwtTokenService;

  @Autowired
  CustomUserDetailService customUserDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = getJwtFromRequest(request);
    if (StringUtils.hasText(token) && jwtTokenService.validateToken(token)
        && jwtTokenService.isAccessToken(token)) {
      String email = jwtTokenService.getEmailFomJwt(token);

      UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
      if (userDetails.isEnabled() && userDetails.isAccountNonLocked()
          && userDetails.isCredentialsNonExpired()) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      } else if (!userDetails.isEnabled()) {
        throw new BadCredentialsException("Account is not yet activated");
      } else if (!userDetails.isCredentialsNonExpired()) {
        throw new BadCredentialsException("Your Password has expired");
      } else if (!userDetails.isAccountNonLocked()) {
        throw new BadCredentialsException("Your Account is Locked for 24hrs");
      }
    }
    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}