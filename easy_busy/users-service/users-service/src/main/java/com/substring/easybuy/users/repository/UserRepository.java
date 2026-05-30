package com.substring.easybuy.users.repository;

import com.substring.easybuy.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    //find the user by email
    Optional<User> findByEmail(String email);
    //check the user exist by email or not
    boolean existsByEmail(String email);
}
