package com.hospital.management.demo.repositories;

import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.token.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken,String> {
    void deleteAllByUser(HospitalStaff user);
}