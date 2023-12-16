package com.hospital.management.demo.repositories;

import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {
    List<RefreshToken> findByUser(HospitalStaff user);

    void deleteAllByUser(HospitalStaff user);

    Boolean existsByUser(HospitalStaff user);
}