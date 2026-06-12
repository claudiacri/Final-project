package com.example.cakeme.models;

import jakarta.persistence.*;

@Entity
@Table(name = "custom_cake")
@PrimaryKeyJoinColumn(name = "cake_id")
public class CustomCake extends Cake {

    @Column(name = "max_tiers")
    private Integer maxTiers;

    @Column(name = "custom_message_allowed")
    private Boolean customMessageAllowed;

    @Column(name = "production_days_needed")
    private Integer productionDaysNeeded;

    public CustomCake() {}

    public CustomCake(String name, String description, Double basePrice, String imageUrl,
                      PastryShop pastryShop, Integer maxTiers, Boolean customMessageAllowed, Integer productionDaysNeeded) {
        super(name, description, basePrice, imageUrl, pastryShop);
        this.maxTiers = maxTiers;
        this.customMessageAllowed = customMessageAllowed;
        this.productionDaysNeeded = productionDaysNeeded;
    }

    // Getter e Setter
    public Integer getMaxTiers() { return maxTiers; }
    public void setMaxTiers(Integer maxTiers) { this.maxTiers = maxTiers; }

    public Boolean getCustomMessageAllowed() { return customMessageAllowed; }
    public void setCustomMessageAllowed(Boolean customMessageAllowed) { this.customMessageAllowed = customMessageAllowed; }

    public Integer getProductionDaysNeeded() { return productionDaysNeeded; }
    public void setProductionDaysNeeded(Integer productionDaysNeeded) { this.productionDaysNeeded = productionDaysNeeded; }
}
