package com.udacity.pricing.service;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PricingService {

    final private PriceRepository priceRepository;

    public PricingService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price assignPrice(Long vehicleId, BigDecimal randomPrice) {
        Optional<Price> price = Optional.ofNullable(priceRepository.findByVehicleId(vehicleId));
        if (price.isPresent()) {
            return price.get();
        } else {
            Price newPrice = new Price("USD", randomPrice, vehicleId);
            priceRepository.save(newPrice);
            return priceRepository.findByVehicleId(vehicleId);
        }
    }

    public void delete(Long vehicleId) {
        Optional<Price> optionalCar = Optional.ofNullable(priceRepository.findByVehicleId(vehicleId));
        Price price = optionalCar.orElseThrow(PriceNotFoundException::new);
        priceRepository.delete(price);
    }
}
