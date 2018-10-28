package com.demo.service;

import com.demo.model.ClientType;

import java.math.BigDecimal;

public class PricingPLN extends Pricing {

    private final BigDecimal firstHourRegularFee = new BigDecimal(1);
    private final BigDecimal secondHourRegularFee = new BigDecimal(2);
    private final BigDecimal multiplierForRegular = new BigDecimal(2);


    private final BigDecimal firstHourVipFee = BigDecimal.ZERO;
    private final BigDecimal secondHourVipFee = new BigDecimal(2);
    private final BigDecimal multiplierForVip = new BigDecimal(1.5);

    public PricingPLN(ClientType clientType) {
        if (clientType == ClientType.REGULAR) {
            super.firstHourFee = this.firstHourRegularFee;
            super.secondHourFee = this.secondHourRegularFee;
            super.multiplier = this.multiplierForRegular;
        } else if (clientType == ClientType.VIP) {
            super.firstHourFee = this.firstHourVipFee;
            super.secondHourFee = this.secondHourVipFee;
            super.multiplier = this.multiplierForVip;
        }
    }
}
