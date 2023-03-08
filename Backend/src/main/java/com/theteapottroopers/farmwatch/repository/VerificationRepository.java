package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.security.auth.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findVerificationTokenByToken(String token);
    String findUserIdByVerificationToken(String token);
}
