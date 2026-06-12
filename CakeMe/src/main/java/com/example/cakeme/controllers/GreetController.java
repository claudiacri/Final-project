package com.example.cakeme.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GreetController {

    @GetMapping("/greet")
    public ResponseEntity<Map<String, String>> greetPublic() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "WELCOME TO YOUR CAKE ME APP! 🍰🚀");
        response.put("logo_url", "https://unsplash.com");
        response.put("description", "CakeMe is an advanced bakery catalog system powered by Spring Boot 3 and MySQL.");

        // quiz canva
        response.put("interactive_quiz_title", "Scopri la tua personalità dolce!");
        response.put("interactive_quiz_text", "Non sai quale torta scegliere? Fai il nostro test interattivo per scoprire quale dolce si adatta meglio al tuo umore di oggi!");
        response.put("interactive_quiz_url", "https://cakemequiz.my.canva.site/");

        response.put("status", "Public Endpoint - Access Granted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/greet/personal")
    public ResponseEntity<Map<String, String>> greetPersonal(Authentication authentication) {
        String username = authentication.getName();

        Map<String, String> response = new HashMap<>();
        response.put("message", "WELCOME BACK TO CAKE ME, " + username.toUpperCase() + "! 👑");
        response.put("details", "You have successfully authenticated via JWT Bearer Token.");

        // 🟢 SEZIONE SPECIALE: Rimando artigianale alla tua amica per le torte personalizzate
        response.put("custom_orders_partner", "Vuoi una torta unica e su misura? Ordina direttamente dalla nostra Cake Designer partner!");
        response.put("partner_name", "la_vale_cake"); // Sostituisci con il nome reale
        response.put("partner_contact", "WhatsApp: +39 345 678910 / Instagram: @la_vale_cake"); // Sostituisci con i contatti reali
        response.put("partner_note", "Menziona il codice 'CAKEME10' per avere il 10% di sconto sul primo preventivo!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
