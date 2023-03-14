package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.security.auth.ForgotPasswordToken;
import com.theteapottroopers.farmwatch.security.auth.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Wat doet deze interface?
 */
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, Long> {
    Optional<ForgotPasswordToken> findByToken(String token);
}
