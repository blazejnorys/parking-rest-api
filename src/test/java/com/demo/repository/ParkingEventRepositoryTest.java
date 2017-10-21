package com.demo.repository;

import com.demo.model.ParkingEvent;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingEventRepositoryTest {

    @Autowired
    private ParkingEventRepository parkingEventRepository;

    @Test
    public void shouldReturnSumOfPayments(){
        //given
        //when
        double allPayments = parkingEventRepository.getPaymentSum();
        //then
        Assertions.assertThat(allPayments).isEqualTo(22.0);
    }

    @Test
    public void shouldReturnSumOfPaymentsByDate(){
        //given
        //when
        double allPaymentsByDate20171021 = parkingEventRepository.getPaymentSumByDate(new Timestamp(1508536800000L),new Timestamp(1508623199000L));
        double allPaymentsByDate20171020 = parkingEventRepository.getPaymentSumByDate(new Timestamp(1508450400000L),new Timestamp(1508536799000L));
        //then
        Assertions.assertThat(allPaymentsByDate20171020).isEqualTo(15.0);
        Assertions.assertThat(allPaymentsByDate20171021).isEqualTo(7.0);
    }
}
