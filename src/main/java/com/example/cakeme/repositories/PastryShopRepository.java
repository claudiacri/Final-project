package com.example.cakeme.repositories;

import com.example.cakeme.models.PastryShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastryShopRepository extends JpaRepository<PastryShop, Long> {
    List<PastryShop> findByCityIgnoreCase(String city);
}