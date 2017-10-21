package com.demo.service;

import com.demo.model.ParkingEvent;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingEventServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ParkingEventService parkingEventService;

    @Test
    public void shouldListAllEvents(){
        //given
        //when
        List<ParkingEvent> parkingEventList = parkingEventService.getAllEvents();
        //then
        Assertions.assertThat(parkingEventList.size()).isEqualTo(3);
    }

    @Test
    public void shouldAddNewEvent(){
        //given
        parkingEventService.addNewEvent(10.0,new Timestamp(3123123L));
        //when
        List<ParkingEvent> parkingEventsList = parkingEventService.getAllEvents();
        //then
        Assertions.assertThat(parkingEventsList.size()).isEqualTo(4);
        Assertions.assertThat(parkingEventsList.get(3).getPaymentDate()).isEqualTo(new Timestamp(3123123L));
        Assertions.assertThat(parkingEventsList.get(3).getPaymentPln()).isEqualTo(10.0);
    }

    @Test
    public void shouldReturnPaymenSum(){
        //given
        //when
        double paymentSum = parkingEventService.getPaymentSum();
        //then
        Assertions.assertThat(paymentSum).isEqualTo(22.0);

    }

    @Test
    public void shouldReturnPaymentSumByDate(){
        //given
        //when
        double paymentSum20102017 = parkingEventService.getPaymentSumByDate("2017","10","20");
        double paymentSum21102017 = parkingEventService.getPaymentSumByDate("2017","10","21");
        //then
        Assertions.assertThat(paymentSum20102017).isEqualTo(15.0);
        Assertions.assertThat(paymentSum21102017).isEqualTo(7);

    }
}
