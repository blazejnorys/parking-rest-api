package com.demo.service;

import com.demo.model.Driver;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ParkingRateCalculatorPlnService implements ParkingRatesCalculator {

    @Override
    public BigDecimal calculateParkingRate(int time, Driver driver) {
        Pricing pricing = new PricingPLN(driver.getClientType());
        BigDecimal amountToBePaid;
        if (time <= FIRST_HOUR) {
            return pricing.getFirstHourFee();
        }
        if (time <= SECOND_HOUR) {
            return pricing.getFirstHourFee().add(pricing.getSecondHourFee());
        }
        amountToBePaid = pricing.getFirstHourFee().add(pricing.getSecondHourFee());
        for (int i = 1; i <= time - SECOND_HOUR; i++) {
            amountToBePaid = amountToBePaid.add((pricing.getMultiplier().pow(i)).multiply(pricing.getSecondHourFee()));
        }
        return amountToBePaid;
    }
}
