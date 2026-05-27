package com.example.cakeme.services;

import com.example.cakeme.models.Cake;
import com.example.cakeme.repositories.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    // READ - Ottieni tutte le torte o filtra per parola chiave (es. "vegana", "panna")
    public List<Cake> getAllCakes(Optional<String> search) {
        if (search.isPresent() && !search.get().trim().isEmpty()) {
            return cakeRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search.get(), search.get());
        }
        return cakeRepository.findAll();
    }

    // READ - Ottieni una singola torta tramite ID
    public Cake getCakeById(Long id) {
        return cakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Torta non trovata con ID: " + id));
    }

    // CREATE - Salva una nuova torta
    public Cake createCake(Cake cake) {
        return cakeRepository.save(cake);
    }

    // UPDATE - Modifica i dati di una torta esistente
    public Cake updateCake(Long id, Cake cakeDetails) {
        Cake existingCake = getCakeById(id);

        existingCake.setName(cakeDetails.getName());
        existingCake.setDescription(cakeDetails.getDescription());
        existingCake.setBasePrice(cakeDetails.getBasePrice());
        existingCake.setImageUrl(cakeDetails.getImageUrl());

        return cakeRepository.save(existingCake);
    }

    // DELETE - Rimuovi una torta tramite ID
    public void deleteCake(Long id) {
        Cake existingCake = getCakeById(id);
        cakeRepository.delete(existingCake);
    }
}