package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
