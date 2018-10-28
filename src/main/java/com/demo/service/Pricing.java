package com.demo.service;


import java.math.BigDecimal;

public abstract class Pricing {

    BigDecimal firstHourFee;
    BigDecimal secondHourFee;
    BigDecimal multiplier;

    BigDecimal getFirstHourFee() {
        return firstHourFee;
    }

    BigDecimal getSecondHourFee() {
        return secondHourFee;
    }

    BigDecimal getMultiplier() {
        return multiplier;
    }
}
