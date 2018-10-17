package com.demo.service;

import com.demo.model.Driver;

public interface ParkingRatesCalculator {

    int calculateParkingRate(int time, Driver driver);
}
