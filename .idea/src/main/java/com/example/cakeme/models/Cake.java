package com.example.cakeme.models;



import jakarta.persistence.*;


@Entity
@Table (name = "cake")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Cake() {}

    public Cake(String name, String description, Double basePrice, String imageUrl, PastryShop pastryShop) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.pastryShop = pastryShop;
    }
}
