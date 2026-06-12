package com.example.cakeme.repositories;

import com.example.cakeme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Metodo indispensabile a Spring Security per caricare l'utente durante il login
    Optional<User> findByUsername(String username);
}