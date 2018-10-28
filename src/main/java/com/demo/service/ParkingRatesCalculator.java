package com.demo.service;

import com.demo.model.Driver;

import java.math.BigDecimal;

public interface ParkingRatesCalculator {

    static final Integer FIRST_HOUR = 1;
    static final Integer SECOND_HOUR = 2;

    BigDecimal calculateParkingRate(int time, Driver driver);
}
