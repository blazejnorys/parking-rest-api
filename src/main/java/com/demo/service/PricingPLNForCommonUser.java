package com.demo.service;

import java.math.BigDecimal;

class PricingPLNForCommonUser implements Pricing {

    private final BigDecimal FIRST_HOUR_PRICE = new BigDecimal(1);
    private final BigDecimal SECOND_HOUR_PRICE = new BigDecimal(2);
    private final BigDecimal MULTIPLIER = new BigDecimal(2);

    @Override
    public BigDecimal getFirstHourPrice() {
        return FIRST_HOUR_PRICE;
    }

    @Override
    public BigDecimal getSecondHourPrice() {
        return SECOND_HOUR_PRICE;
    }

    @Override
    public BigDecimal getMultiplier() {
        return MULTIPLIER;
    }
}
