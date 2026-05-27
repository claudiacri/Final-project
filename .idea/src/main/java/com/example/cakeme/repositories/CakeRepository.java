package com.example.cakeme.repositories;

import com.example.cakeme.models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    List<Cake> findByPastryShopId(Long pastryShopId);

    // Questa servirà per la ricerca a parole libere dell'utente
    List<Cake> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}