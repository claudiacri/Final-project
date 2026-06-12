package com.example.cakeme.controllers;

import com.example.cakeme.models.PastryShop;
import com.example.cakeme.services.PastryShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class PastryShopController {

    @Autowired
    private PastryShopService pastryShopService;

    // 1. GET /api/shops (Pubblico)
    @GetMapping
    public ResponseEntity<List<PastryShop>> getAllShops() {
        return new ResponseEntity<>(pastryShopService.getAllShops(), HttpStatus.OK);
    }

    // 2. GET /api/shops/{id} (Pubblico)
    @GetMapping("/{id}")
    public ResponseEntity<PastryShop> getShopById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(pastryShopService.getShopById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. POST /api/shops (Protetto da Token JWT)
    @PostMapping
    public ResponseEntity<PastryShop> createShop(@RequestBody PastryShop pastryShop) {
        PastryShop newShop = pastryShopService.createShop(pastryShop);
        return new ResponseEntity<>(newShop, HttpStatus.CREATED);
    }

    // 4. PUT /api/shops/{id} (Protetto da Token JWT)
    @PutMapping("/{id}")
    public ResponseEntity<PastryShop> updateShop(@PathVariable Long id, @RequestBody PastryShop shopDetails) {
        try {
            PastryShop updatedShop = pastryShopService.updateShop(id, shopDetails);
            return new ResponseEntity<>(updatedShop, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. DELETE /api/shops/{id} (Protetto da Token JWT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        try {
            pastryShopService.deleteShop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

