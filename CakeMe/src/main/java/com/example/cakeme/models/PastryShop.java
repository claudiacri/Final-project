package com.example.cakeme.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pastry_shop")
public class PastryShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(name = "order_url")
    private String orderUrl;

    @OneToMany(mappedBy = "pastryShop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cake> cakes = new ArrayList<>();

    public PastryShop() {}

    public PastryShop(String name, String address, String city, String orderUrl) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.orderUrl = orderUrl;
    }

    public PastryShop(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;

    }

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getOrderUrl() { return orderUrl; }
    public void setOrderUrl(String orderUrl) { this.orderUrl = orderUrl; }

    public List<Cake> getCakes() { return cakes; }
    public void setCakes(List<Cake> cakes) { this.cakes = cakes; }
}

