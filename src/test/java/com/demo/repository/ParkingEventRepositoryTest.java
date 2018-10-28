package com.demo.repository;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingEventRepositoryTest {

    @Autowired
    private ParkingEventRepository parkingEventRepository;

    @Test
    public void shouldReturnSumOfPayments() {
        //given
        //when
        BigDecimal allPayments = parkingEventRepository.getPaymentSum();
        //then
        Assert.assertThat(allPayments, Matchers.comparesEqualTo(new BigDecimal(22)));

    }

    @Test
    public void shouldReturnSumOfPaymentsByDate() {
        //given
        //when
        BigDecimal allPaymentsByDate20171021 = parkingEventRepository.getPaymentSumByDate(new Timestamp(1508536800000L), new Timestamp(1508623199000L));
        BigDecimal allPaymentsByDate20171020 = parkingEventRepository.getPaymentSumByDate(new Timestamp(1508450400000L), new Timestamp(1508536799000L));
        //then
        Assert.assertThat(allPaymentsByDate20171020, Matchers.comparesEqualTo(new BigDecimal(15.00)));
        Assert.assertThat(allPaymentsByDate20171021, Matchers.comparesEqualTo(new BigDecimal(7)));
    }
}
