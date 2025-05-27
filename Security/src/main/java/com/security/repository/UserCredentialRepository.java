package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entity.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByEmail(String email);
}
