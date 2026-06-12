package com.example.cakeme.repositories;

import com.example.cakeme.models.StandardCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardCakeRepository extends JpaRepository<StandardCake, Long> {
    List<StandardCake> findByIsDietaryTrue();
}

