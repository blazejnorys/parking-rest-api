package com.demo.service;

import com.demo.model.ClientType;
import com.demo.model.Driver;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ParkingRateCalculatorPlnService implements ParkingRatesCalculator {

    @Override
    public Pricing getPricingByClientType(ClientType clientType) {
        if (clientType.equals(ClientType.VIP)){
            return new PricingPLNForVipUser();
        }
        return new PricingPLNForCommonUser();
    }

    @Override
    public BigDecimal calculateParkingRate(int time, Driver driver) {
        Pricing pricing = getPricingByClientType(driver.getClientType());
        BigDecimal amountToBePaid;
        if (time <= FIRST_HOUR) {
            return pricing.getFirstHourPrice();
        }
        if (time <= SECOND_HOUR) {
            return pricing.getFirstHourPrice().add(pricing.getSecondHourPrice());
        }
        amountToBePaid = pricing.getFirstHourPrice().add(pricing.getSecondHourPrice());
        for (int i = 1; i <= time - SECOND_HOUR; i++) {
            amountToBePaid = amountToBePaid.add((pricing.getMultiplier().pow(i)).multiply(pricing.getSecondHourPrice()));
        }
        return amountToBePaid;
    }
}
