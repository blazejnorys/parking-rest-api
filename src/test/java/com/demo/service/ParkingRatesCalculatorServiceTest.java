package com.demo.service;

import com.demo.model.ClientType;
import com.demo.model.Driver;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

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
        int paymentForOneHour = parkingRatesCalculator.calculateParkingRate(oneHour, regularDriver);
        int paymentForTwoHours = parkingRatesCalculator.calculateParkingRate(twoHours, regularDriver);
        int paymentForThreeHours = parkingRatesCalculator.calculateParkingRate(threeHours, regularDriver);
        int paymentForFourHours = parkingRatesCalculator.calculateParkingRate(fourHours, regularDriver);
        int paymentForFiveHours = parkingRatesCalculator.calculateParkingRate(fiveHours, regularDriver);
        //then
        Assertions.assertThat(paymentForOneHour).isEqualTo(100);
        Assertions.assertThat(paymentForTwoHours).isEqualTo(100 + 200);
        Assertions.assertThat(paymentForThreeHours).isEqualTo(100 + 200 + 400);
        Assertions.assertThat(paymentForFourHours).isEqualTo(100 + 200 + 400 + 800);
        Assertions.assertThat(paymentForFiveHours).isEqualTo(100 + 200 + 400 + 800 + 1600);

    }

    @Test
    public void shouldCalculateVipPayment() {
        //given
        Driver vipDriver = new Driver("Vip", "Vip", "Vip", "Vip", ClientType.VIP);
        //when
        int paymentForOneHour = parkingRatesCalculator.calculateParkingRate(oneHour, vipDriver);
        int paymentForTwoHours = parkingRatesCalculator.calculateParkingRate(twoHours, vipDriver);
        int paymentForThreeHours = parkingRatesCalculator.calculateParkingRate(threeHours, vipDriver);
        int paymentForFourHours = parkingRatesCalculator.calculateParkingRate(fourHours, vipDriver);
        int paymentForFiveHours = parkingRatesCalculator.calculateParkingRate(fiveHours, vipDriver);
        //then
        Assertions.assertThat(paymentForOneHour).isEqualTo(0);
        Assertions.assertThat(paymentForTwoHours).isEqualTo(200);
        Assertions.assertThat(paymentForThreeHours).isEqualTo(200 + 300);
        Assertions.assertThat(paymentForFourHours).isEqualTo(200 + 300 + 450);
        Assertions.assertThat(paymentForFiveHours).isEqualTo(200 + 300 + 450 + 675);
    }
}
