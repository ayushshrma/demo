package com.hospital.management.demo.config;

import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.Role;
import com.hospital.management.demo.repositories.HospitalStaffRepository;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
  @Autowired
  private HospitalStaffRepository hospitalStaffRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    HospitalStaff user = hospitalStaffRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Email Not found: " + email));
    return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
  }

  private Collection<GrantedAuthority> mapRolesToAuthorities(Role role) {
    return Arrays.asList(new SimpleGrantedAuthority(role.getAuthority()));
  }


}