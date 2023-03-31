package com.udacity.pricing.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements a mock repository for generating a random price.
 */
public class MockPriceRepository {

    private String randomPrice;

    static BigDecimal getRandomPrice() {

        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal(5000d)).setScale(2, RoundingMode.HALF_UP);
    }
}
