package com.example.cakeme.models;


import jakarta.persistence.*;

@Entity
@Table(name = "standard_cake")
@PrimaryKeyJoinColumn(name = "cake_id")
public class StandardCake extends Cake {

    @Column(name = "vegana")
    private Boolean isDietary;

    private String allergens;

    @Column(name = "disponibile")
    private Integer availableInStock;

    public StandardCake() {}

    public StandardCake(String name, String description, Double basePrice, String imageUrl,
                        PastryShop pastryShop, Boolean isDietary, String allergens, Integer availableInStock) {
        super(name, description, basePrice, imageUrl, pastryShop);
        this.isDietary = isDietary;
        this.allergens = allergens;
        this.availableInStock = availableInStock;
    }

    // Getter e Setter
    public Boolean getIsDietary() { return isDietary; }
    public void setIsDietary(Boolean dietary) { isDietary = dietary; }

    public String getAllergens() { return allergens; }
    public void setAllergens(String allergens) { this.allergens = allergens; }

    public Integer getAvailableInStock() { return availableInStock; }
    public void setAvailableInStock(Integer availableInStock) { this.availableInStock = availableInStock; }
}
