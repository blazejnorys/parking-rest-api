package com.demo.service;

import com.demo.model.ClientType;

public class PricingPLN extends Pricing {

    private final int firstHourRegularFee = 100;
    private final int secondHourRegularFee = 200;
    private final double multiplierForRegular = 2;

    private final int firstHourVipFee = 0;
    private final int secondHourVipFee = 200;
    private final double multiplierForVip = 1.5;

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
