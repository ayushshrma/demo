package com.hospital.management.demo.repositories;

import com.hospital.management.demo.entity.HospitalStaff;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalStaffRepository extends JpaRepository<HospitalStaff,Long> {
  Optional<HospitalStaff> findByEmail(String username);

  boolean existsByEmail(String email);

  HospitalStaff getDoctorById(Long doctorId);
}