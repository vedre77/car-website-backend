package com.udacity.pricing.controller;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.math.BigDecimal;

@Controller
@RequestMapping("/prices")
public class PriceServiceController {
    @Autowired
    private PricingService pricingService;

    @GetMapping("/{carId}")
    public ResponseEntity<Price> retrievePrice(@PathVariable Long carId) {
        BigDecimal randomPrice = MockPriceRepository.getRandomPrice();
        Price retrievedPrice = pricingService.assignPrice(carId, randomPrice);
        return ResponseEntity.ok(retrievedPrice);
    }

    @DeleteMapping("/{carId}")
    ResponseEntity<?> delete(@PathVariable Long carId) {
        pricingService.delete(carId);
        return ResponseEntity.noContent().build();
    }
}
