package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class ParkingRateCalculatorPlnService implements ParkingRatesCalculator {

    private double amountToBePaid = 0;

    @Override
    public double calculateParkingRateRegular(double time) {
        if (time <= 1) {
            return amountToBePaid = 1;
        }else if(time<=2){
            return amountToBePaid = 2;
        } else {
            amountToBePaid = 3;
            for (double i = 1; i <= time-2; i++) {
                amountToBePaid += (Math.pow(2, i)*2);
            }
            return amountToBePaid;
        }
    }

    @Override
    public double calculateParkingRateVip(double time) {
        if(time<=1){
            return amountToBePaid = 0;
        }else if(time<=2){
            return amountToBePaid = 2;
        }else{
            amountToBePaid = 2;
            for (int i = 1; i <= time-2; i++) {
                amountToBePaid += Math.pow(1.5, i)*2;
            }
            return Math.floor(amountToBePaid*100)/100;
        }

    }


}
