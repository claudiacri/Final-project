package com.example.cakeme.config;

import com.example.cakeme.models.CustomCake;
import com.example.cakeme.models.PastryShop;
import com.example.cakeme.models.StandardCake;
import com.example.cakeme.models.Role;
import com.example.cakeme.models.User;
import com.example.cakeme.repositories.CakeRepository;
import com.example.cakeme.repositories.PastryShopRepository;
import com.example.cakeme.repositories.RoleRepository;
import com.example.cakeme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PastryShopRepository pastryShopRepository;

    @Autowired
    private CakeRepository cakeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // ====== 1. CARICAMENTO RUOLI E UTENTI CRIPTATI ======
        Role userRole = roleRepository.save(new Role(null, "ROLE_USER"));
        Role adminRole = roleRepository.save(new Role(null, "ROLE_ADMIN"));

        User normalUser = new User(
                "mario_rossi",
                passwordEncoder.encode("password123"),
                new ArrayList<>(List.of(userRole))
        );

        User adminUser = new User(
                "la_cloddi",
                passwordEncoder.encode("admin2026"),
                new ArrayList<>(List.of(adminRole, userRole))
        );

        userRepository.save(normalUser);
        userRepository.save(adminUser);

        // ====== 2. CARICAMENTO PASTICCERIE ======
        PastryShop shop1 = new PastryShop(
                "Pasticceria Mauri",
                "Rambla de Catalunya 102",
                "BCN",
                "https://pasteleriasmauri.com/catering-online/" // 🔗 Link e-commerce reale di Mauri!
        );

        PastryShop shop2 = new PastryShop(
                "Demasie",
                "Carrer de la Diputació, 207",
                "BCN",
                "https://www.instagram.com/cookies_demasie/" // 🔗 Link WhatsApp automatico!
        );





        pastryShopRepository.save(shop1);
        pastryShopRepository.save(shop2);

        // ====== 3. CARICAMENTO 5 TORTE STANDARD (Pronte a catalogo) ======

        // 1. Sacher Vegana
        StandardCake sc1 = new StandardCake(
                "Torta Sacher Vegana",
                "Golosa torta vegana al cioccolato fondente e marmellata di albicocche, senza latte e uova.",
                28.0, "https://unsplash.com",
                shop1, true, "Glutine, Frutta a guscio", 5
        );

        // 2. Millefoglie alla Panna
        StandardCake sc2 = new StandardCake(
                "Millefoglie alla Panna",
                "Strati di sfoglia croccante farciti con abbondante panna fresca montata e gocce di cioccolato.",
                32.5, "https://unsplash.com",
                shop2, false, "Glutine, Latte, Uova", 3
        );

        // 3. Crostata di Frutta Senza Glutine
        StandardCake sc3 = new StandardCake(
                "Crostata di Frutta Celiaca",
                "Pasta frolla senza glutine, crema pasticcera leggera e tanta frutta fresca di stagione.",
                26.0, "https://unsplash.com",
                shop1, true, "Latte, Uova", 4
        );

        // 4. Red Velvet Classica
        StandardCake sc4 = new StandardCake(
                "Red Velvet",
                "Torta dal colore rosso acceso con strati di soffice pan di spagna e crema vellutata al frosting di formaggio.",
                35.0, "https://unsplash.com",
                shop2, false, "Glutine, Latte, Uova", 2
        );

        // 5. Tiramisù Cake
        StandardCake sc5 = new StandardCake(
                "Tiramisù",
                "Rivisitazione a torta del classico dolce italiano, con savoiardi artigianali bagnati nel caffè e crema al mascarpone.",
                30.0, "https://unsplash.com",
                shop1, false, "Glutine, Latte, Uova", 6
        );

        // ====== 4. CARICAMENTO 5 TORTE PERSONALIZZATE (Su ordinazione) ======

        // 6. Torta Wedding Monumentale
        CustomCake cc1 = new CustomCake(
                "Torta Monumentale da Cerimonia",
                "Torta scenografica per matrimoni. Scegli i gusti dei tre strati interni e i fiori edibili decorativi.",
                120.0, "https://www.istockphoto.com/it/foto/gigante-torta-nuziale-gm156214154-10284898",
                shop2, 4, true, 5
        );

        // 7. Torta di Compleanno Bambini
        CustomCake cc2 = new CustomCake(
                "Torta Cartoon in Pasta di Zucchero",
                "Personalizzabile con il personaggio dei cartoni animati preferito di tuo figlio e dedica dipinta a mano.",
                65.0, "https://unsplash.com",
                shop1, 2, true, 3
        );

        // 8. Torta Laurea Elegante
        CustomCake cc3 = new CustomCake(
                "Torta Corona di Alloro",
                "Festeggia la laurea con una torta personalizzata rossa e oro, gusto cioccolato e lamponi.",
                55.0, "https://unsplash.com",
                shop2, 1, true, 2
        );

        // 9. Torta Vegana Personalizzata per Eventi
        CustomCake cc4 = new CustomCake(
                "Custom Flower Cake Vegana",
                "Torta totalmente personalizzabile per compleanni, a base vegetale, guarnita con petali biologici cristallizzati.",
                48.0, "https://unsplash.com",
                shop1, 1, true, 3
        );

        // 10. Torta Anniversario Cioccolato e Oro
        CustomCake cc5 = new CustomCake(
                "Luxury Chocolate Gold",
                "Torta elegante con glassa a specchio scura, dettagli in foglia d'oro edibile e dedica interna segreta.",
                80.0, "https://unsplash.com",
                shop2, 2, false, 4
        );

        // Salviamo tutte le 10 torte nel database MySQL
        cakeRepository.save(sc1);
        cakeRepository.save(sc2);
        cakeRepository.save(sc3);
        cakeRepository.save(sc4);
        cakeRepository.save(sc5);
        cakeRepository.save(cc1);
        cakeRepository.save(cc2);
        cakeRepository.save(cc3);
        cakeRepository.save(cc4);
        cakeRepository.save(cc5);

        System.out.println("====== VITTORIA: 10 TORTE E UTENTI CARICATI CON SUCCESSO IN MYSQL ======");
    }
}
