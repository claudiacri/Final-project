package com.example.cakeme.repositories;

import com.example.cakeme.models.CustomCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCakeRepository extends JpaRepository<CustomCake, Long> {
}