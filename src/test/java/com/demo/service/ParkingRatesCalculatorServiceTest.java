package com.demo.service;

import com.demo.model.ClientType;
import com.demo.model.Driver;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingRatesCalculatorServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ParkingRatesCalculator parkingRatesCalculator;

    private int oneHour = 1;
    private int twoHours = 2;
    private int threeHours = 3;
    private int fourHours = 4;
    private int fiveHours = 5;


    @Test
    public void shouldCalculateRegularPayment() {
        //given
        Driver regularDriver = new Driver("Regular", "Regular", "Regular", "Regular", ClientType.REGULAR);
        //when
        BigDecimal paymentForOneHour = parkingRatesCalculator.calculateParkingRate(oneHour, regularDriver);
        BigDecimal paymentForTwoHours = parkingRatesCalculator.calculateParkingRate(twoHours, regularDriver);
        BigDecimal paymentForThreeHours = parkingRatesCalculator.calculateParkingRate(threeHours, regularDriver);
        BigDecimal paymentForFourHours = parkingRatesCalculator.calculateParkingRate(fourHours, regularDriver);
        BigDecimal paymentForFiveHours = parkingRatesCalculator.calculateParkingRate(fiveHours, regularDriver);
        //then
        Assert.assertThat(paymentForOneHour,  Matchers.comparesEqualTo(new BigDecimal(1)));
        Assert.assertThat(paymentForTwoHours,  Matchers.comparesEqualTo(new BigDecimal(3)));
        Assert.assertThat(paymentForThreeHours,  Matchers.comparesEqualTo(new BigDecimal(7)));
        Assert.assertThat(paymentForFourHours,  Matchers.comparesEqualTo(new BigDecimal(15)));
        Assert.assertThat(paymentForFiveHours,  Matchers.comparesEqualTo(new BigDecimal(31)));
    }

    @Test
    public void shouldCalculateVipPayment() {
        //given
        Driver vipDriver = new Driver("Vip", "Vip", "Vip", "Vip", ClientType.VIP);
        //when
        BigDecimal paymentForOneHour = parkingRatesCalculator.calculateParkingRate(oneHour, vipDriver);
        BigDecimal paymentForTwoHours = parkingRatesCalculator.calculateParkingRate(twoHours, vipDriver);
        BigDecimal paymentForThreeHours = parkingRatesCalculator.calculateParkingRate(threeHours, vipDriver);
        BigDecimal paymentForFourHours = parkingRatesCalculator.calculateParkingRate(fourHours, vipDriver);
        BigDecimal paymentForFiveHours = parkingRatesCalculator.calculateParkingRate(fiveHours, vipDriver);
        //then
        Assert.assertThat(paymentForOneHour,  Matchers.comparesEqualTo(BigDecimal.ZERO));
        Assert.assertThat(paymentForTwoHours,  Matchers.comparesEqualTo(new BigDecimal(2)));
        Assert.assertThat(paymentForThreeHours,  Matchers.comparesEqualTo(new BigDecimal(5)));
        Assert.assertThat(paymentForFourHours,  Matchers.comparesEqualTo(new BigDecimal(9.5)));
        Assert.assertThat(paymentForFiveHours,  Matchers.comparesEqualTo(new BigDecimal(16.25)));
    }
}
