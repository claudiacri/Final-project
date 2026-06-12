package com.example.cakeme.controllers;

import com.example.cakeme.models.Cake;
import com.example.cakeme.services.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cakes")
public class CakeController {

    @Autowired
    private CakeService cakeService;

    // 1. GET /api/cakes (oppure /api/cakes?search=panna)
    @GetMapping
    public ResponseEntity<List<Cake>> getAllCakes(@RequestParam Optional<String> search) {
        List<Cake> cakes = cakeService.getAllCakes(search);

        if (cakes.isEmpty()) {
            // Se il database è vuoto, restituisce un array vuoto esplicito con status 200
            return new ResponseEntity<>(new java.util.ArrayList<>(), HttpStatus.OK);
        }

        return new ResponseEntity<>(cakes, HttpStatus.OK);
    }

    // 2. GET /api/cakes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cake> getCakeById(@PathVariable Long id) {
        try {
            Cake cake = cakeService.getCakeById(id);
            return new ResponseEntity<>(cake, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Cake>> getCakesByShop(@PathVariable Long shopId) {
        // Sfruttiamo il metodo findByPastryShopId che abbiamo già creato nella CakeRepository!
        List<Cake> cakes = cakeService.getCakesByShopId(shopId);
        return new ResponseEntity<>(cakes, HttpStatus.OK);
    }
    @GetMapping("/shop-name")
    public ResponseEntity<List<Cake>> getCakesByShopName(@RequestParam String name) {
        List<Cake> cakes = cakeService.getCakesByShopName(name);
        return new ResponseEntity<>(cakes, HttpStatus.OK);
    }


    // 3. POST /api/cakes
    @PostMapping
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        Cake newCake = cakeService.createCake(cake);
        return new ResponseEntity<>(newCake, HttpStatus.CREATED);
    }

    // 4. PUT /api/cakes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cake> updateCake(@PathVariable Long id, @RequestBody Cake cakeDetails) {
        try {
            Cake updatedCake = cakeService.updateCake(id, cakeDetails);
            return new ResponseEntity<>(updatedCake, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. DELETE /api/cakes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCake(@PathVariable Long id) {
        try {
            cakeService.deleteCake(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
