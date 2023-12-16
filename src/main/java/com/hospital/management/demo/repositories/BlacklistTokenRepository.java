package com.hospital.management.demo.repositories;

import com.hospital.management.demo.entity.token.BlacklistedJwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistTokenRepository extends JpaRepository<BlacklistedJwtToken,String> {

}