package com.example.cakeme.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Entity
@Table(name = "cake")
@Inheritance(strategy = InheritanceType.JOINED)
// 🟢 Spiega a Jackson che nel JSON ci sarà un campo "type" per riconoscere il figlio
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
// 🟢 Mappa i valori del campo "type" alle classi reali concrete
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardCake.class, name = "standard"),
        @JsonSubTypes.Type(value = CustomCake.class, name = "custom")
})
public abstract class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "pastry_shop_id", nullable = false)
    private PastryShop pastryShop;

    public Cake() {}

    public Cake(String name, String description, Double basePrice, String imageUrl, PastryShop pastryShop) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.pastryShop = pastryShop;
    }

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public PastryShop getPastryShop() { return pastryShop; }
    public void setPastryShop(PastryShop pastryShop) { this.pastryShop = pastryShop; }
}
