package com.udacity.pricing.repository;

import com.udacity.pricing.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    boolean existsByVehicleId(Long vehicleId);
    Price findByVehicleId(Long vehicleId);
}
