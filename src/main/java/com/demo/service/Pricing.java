package com.demo.service;


import java.math.BigDecimal;

interface Pricing {

    BigDecimal getFirstHourPrice();

    BigDecimal getSecondHourPrice();

    BigDecimal getMultiplier();
}
