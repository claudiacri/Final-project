package com.example.cakeme.config;

import com.example.cakeme.models.CustomCake;
import com.example.cakeme.models.PastryShop;
import com.example.cakeme.models.StandardCake;
import com.example.cakeme.repositories.CakeRepository;
import com.example.cakeme.repositories.PastryShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PastryShopRepository pastryShopRepository;

    @Autowired
    private CakeRepository cakeRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Creiamo e salviamo due Pasticcerie di prova
        PastryShop shop1 = new PastryShop("Pasticceria Delizie Verdi", "Via Torino 15", "Milano");
        PastryShop shop2 = new PastryShop("Dolci Momenti", "Corso Vittorio Emanuele 4", "Roma");

        pastryShopRepository.save(shop1);
        pastryShopRepository.save(shop2);

        // 2. Inseriamo Torte Standard (Pronte a catalogo)
        // Torta Vegana al Cioccolato (Richiesta utente)
        StandardCake cake1 = new StandardCake(
                "Torta Sacher Vegana", 
                "Una golosa torta vegana al cioccolato fondente e marmellata di albicocche, senza latte e senza uova.", 
                28.0, 
                "https://unsplash.com", 
                shop1, 
                true, 
                "Glutine, Frutta a guscio", 
                5
        );

        // Torta con la Panna (Richiesta utente)
        StandardCake cake2 = new StandardCake(
                "Millefoglie alla Panna", 
                "Strati di sfoglia croccante farciti con abbondante panna fresca montata e gocce di cioccolato.", 
                32.5, 
                "https://unsplash.com", 
                shop2, 
                false, 
                "Glutine, Latte, Uova", 
                3
        );

        // 3. Inseriamo una Torta Personalizzata (CustomCake) per testare l'ereditarietà JOINED
        CustomCake cake3 = new CustomCake(
                "Torta Monumentale da Cerimonia", 
                "Torta personalizzabile per compleanni e matrimoni. Scegli il gusto della crema e la dedica.", 
                75.0, 
                "https://unsplash.com", 
                shop2, 
                4,      // maxTiers (piani)
                true,   // dedica consentita
                3       // giorni di preavviso necessari
        );

        // Salviamo le torte nel database
        cakeRepository.save(cake1);
        cakeRepository.save(cakeRepository.save(cake2));
        cakeRepository.save(cake3);

        System.out.println("====== DATI DI PROVA CARICATI CON SUCCESSO NEL DATABASE ======");
    }
}
