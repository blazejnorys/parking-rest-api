package com.demo.service;

import com.demo.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class ParkingRateCalculatorPlnService implements ParkingRatesCalculator {

    @Override
    public int calculateParkingRate(int time, Driver driver) {
        Pricing pricing = new PricingPLN(driver.getClientType());
        int amountToBePaid;

        if(time<=1){
            return pricing.getFirstHourFee();
        }else if(time<=2){
            return pricing.getFirstHourFee()+pricing.getSecondHourFee();
        }else {
            amountToBePaid = pricing.getFirstHourFee()+pricing.getSecondHourFee();
            for (int i =1; i<=time-2;i++){
                amountToBePaid += (int) (Math.pow(pricing.getMultiplier(),i)*200);
            }
        }
        return amountToBePaid;
    }
}
