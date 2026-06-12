package com.example.cakeme.repositories;

import com.example.cakeme.models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    List<Cake> findByPastryShopId(Long pastryShopId);

    // Cerca tutte le torte filtrando per il nome della pasticceria (ignorando maiuscole/minuscole)
    List<Cake> findByPastryShopNameContainingIgnoreCase(String shopName);

    // Questa servirà per la ricerca a parole libere dell'utente
    List<Cake> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}