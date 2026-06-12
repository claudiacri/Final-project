package com.example.cakeme.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class OpenAIService {

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    public String extractKeyword(String userPrompt) {
        RestTemplate restTemplate = new RestTemplate();

        // Prepariamo gli Header HTTP con l'API Key Bearer
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Costruiamo il prompt per istruire l'IA a rispondere con una sola parola
        String systemInstruction = "Sei un assistente per una pasticceria. Analizza la richiesta dell'utente e rispondi SOLO con una singola parola chiave rilevante in italiano (es. 'vegana', 'panna', 'cioccolato', 'fragole') da usare come filtro di ricerca. Non aggiungere nient'altro.";

        // Struttura della richiesta per le API di ChatCompletion (GPT-3.5 o GPT-4)
        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemInstruction));
        messages.add(Map.of("role", "user", "content", userPrompt));
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);
            List<?> choices = (List<?>) response.getBody().get("choices");
            Map<?, ?> firstChoice = (Map<?, ?>) choices.get(0);
            Map<?, ?> message = (Map<?, ?>) firstChoice.get("message");

            // Restituisce la singola parola estratta dall'IA (es. "vegana")
            return message.get("content").toString().trim();
        } catch (Exception e) {
            // Se l'API Key non è attiva o c'è un errore, restituiamo una parola vuota per sicurezza
            return "";
        }
    }
}

