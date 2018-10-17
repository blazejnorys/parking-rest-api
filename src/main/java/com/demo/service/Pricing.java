package com.demo.service;


public abstract class Pricing {

    int firstHourFee;
    int secondHourFee;
    double multiplier;

    int getFirstHourFee() {
        return firstHourFee;
    }

    int getSecondHourFee() {
        return secondHourFee;
    }

    double getMultiplier() {
        return multiplier;
    }
}
