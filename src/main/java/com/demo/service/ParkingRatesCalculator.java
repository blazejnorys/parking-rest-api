package com.demo.service;

public interface ParkingRatesCalculator {

    double calculateParkingRateRegular(double time);
    double calculateParkingRateVip(double time);
}
