package com.example.cakeme.services;

import com.example.cakeme.models.PastryShop;
import com.example.cakeme.repositories.PastryShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastryShopService {

    @Autowired
    private PastryShopRepository pastryShopRepository;

    // READ - Ottieni tutte le pasticcerie
    public List<PastryShop> getAllShops() {
        return pastryShopRepository.findAll();
    }

    // READ - Ottieni una singola pasticceria per ID
    public PastryShop getShopById(Long id) {
        return pastryShopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pasticceria non trovata con ID: " + id));
    }

    // CREATE - Salva una nuova pasticceria
    public PastryShop createShop(PastryShop pastryShop) {
        return pastryShopRepository.save(pastryShop);
    }

    // UPDATE - Modifica una pasticceria esistente
    public PastryShop updateShop(Long id, PastryShop shopDetails) {
        PastryShop existingShop = getShopById(id);

        existingShop.setName(shopDetails.getName());
        existingShop.setAddress(shopDetails.getAddress());
        existingShop.setCity(shopDetails.getCity());

        return pastryShopRepository.save(existingShop);
    }

    // DELETE - Rimuovi una pasticceria
    public void deleteShop(Long id) {
        PastryShop existingShop = getShopById(id);
        pastryShopRepository.delete(existingShop);
    }
}
