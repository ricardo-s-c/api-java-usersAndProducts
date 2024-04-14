package com.apijavalogin.apijavalogin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apijavalogin.apijavalogin.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
