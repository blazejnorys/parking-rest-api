package com.demo.service;

import org.assertj.core.api.Assertions;
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
    ParkingRatesCalculator parkingRatesCalculator;

    double oneHour = 1.0;
    double twoHours = 2.0;
    double threeHours = 3.0;
    double fourHours = 4.0;
    double fiveHours = 5.0;

    @Test
    public void shouldCalculateRegularPayment(){
        //given
        //when
        double paymentForOneHour = parkingRatesCalculator.calculateParkingRateRegular(oneHour);
        double paymentForTwoHours = parkingRatesCalculator.calculateParkingRateRegular(twoHours);
        double paymentForThreeHours = parkingRatesCalculator.calculateParkingRateRegular(threeHours);
        double paymentForFourHours = parkingRatesCalculator.calculateParkingRateRegular(fourHours);
        double paymentForFiveHours = parkingRatesCalculator.calculateParkingRateRegular(fiveHours);
        //then
        Assertions.assertThat(paymentForOneHour).isEqualTo(1.0);
        Assertions.assertThat(paymentForTwoHours).isEqualTo(1.0+2.0);
        Assertions.assertThat(paymentForThreeHours).isEqualTo(1.0+2.0+4.0);
        Assertions.assertThat(paymentForFourHours).isEqualTo(1.0+2.0+4.0+8.0);
        Assertions.assertThat(paymentForFiveHours).isEqualTo(1.0+2.0+4.0+8.0+16.0);

    }

    @Test
    public void shouldCalculateVipPayment(){
        //given
        //when
        double paymentForOneHour = parkingRatesCalculator.calculateParkingRateVip(oneHour);
        double paymentForTwoHours = parkingRatesCalculator.calculateParkingRateVip(twoHours);
        double paymentForThreeHours = parkingRatesCalculator.calculateParkingRateVip(threeHours);
        double paymentForFourHours = parkingRatesCalculator.calculateParkingRateVip(fourHours);
        double paymentForFiveHours = parkingRatesCalculator.calculateParkingRateVip(fiveHours);
        //then
        Assertions.assertThat(paymentForOneHour).isEqualTo(0.0);
        Assertions.assertThat(paymentForTwoHours).isEqualTo(2.0);
        Assertions.assertThat(paymentForThreeHours).isEqualTo(2.0+1.5*2.0);
        Assertions.assertThat(paymentForFourHours).isEqualTo(2.0+1.5*2.0+1.5*1.5*2.0);
        Assertions.assertThat(paymentForFiveHours).isEqualTo(2.0+(1.5*2.0)+(1.5*1.5*2.0)+(1.5*1.5*1.5*2.0));
    }
}
