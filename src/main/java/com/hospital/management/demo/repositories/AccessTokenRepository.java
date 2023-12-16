package com.hospital.management.demo.repositories;

import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.token.AccessToken;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken,String> {
    void deleteAllByUser(HospitalStaff user);

    List<AccessToken> findAllByUser(HospitalStaff user);
}