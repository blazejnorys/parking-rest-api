package com.demo.service;

import com.demo.model.ClientType;
import com.demo.model.Driver;

import java.math.BigDecimal;

public interface ParkingRatesCalculator {

    Integer FIRST_HOUR = 1;
    Integer SECOND_HOUR = 2;

    BigDecimal calculateParkingRate(int time, Driver driver);
    Pricing getPricingByClientType(ClientType clientType);
}
