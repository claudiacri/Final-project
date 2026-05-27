package com.example.cakeme.repositories;

import com.example.cakeme.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Metodo fondamentale per cercare un ruolo tramite il suo nome
    Optional<Role> findByName(String name);
}