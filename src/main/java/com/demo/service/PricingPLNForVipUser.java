package com.demo.service;

import java.math.BigDecimal;

class PricingPLNForVipUser implements Pricing {

    private final BigDecimal FIRST_HOUR_PRICE = BigDecimal.ZERO;
    private final BigDecimal SECOND_HOUR_PRICE = new BigDecimal(2);
    private final BigDecimal MULTIPLIER = new BigDecimal(1.5);

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
