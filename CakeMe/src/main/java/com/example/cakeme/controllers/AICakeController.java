package com.example.cakeme.controllers;

import com.example.cakeme.models.Cake;
import com.example.cakeme.repositories.CakeRepository;
import com.example.cakeme.services.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AICakeController {

    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private CakeRepository cakeRepository;

    // GET /api/ai/search?prompt=voglio qualcosa al cioccolato senza latte
    @GetMapping("/search")
    public ResponseEntity<List<Cake>> searchWithAI(@RequestParam String prompt) {
        // 1. L'IA analizza la frase ed estrae ad esempio "vegana"
        String keyword = openAIService.extractKeyword(prompt);

        // 2. Interroghiamo il database usando la parola chiave suggerita dall'IA
        List<Cake> filteredCakes = cakeRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);

        return new ResponseEntity<>(filteredCakes, HttpStatus.OK);
    }
}
