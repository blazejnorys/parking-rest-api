package com.demo.service;

import com.demo.model.ParkingEvent;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingEventServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ParkingEventService parkingEventService;

    @Test
    public void shouldListAllEvents() {
        //given
        //when
        List<ParkingEvent> parkingEventList = parkingEventService.getAllEvents();
        //then
        Assertions.assertThat(parkingEventList.size()).isEqualTo(3);
    }

    @Test
    public void shouldAddNewEvent() {
        //given
        parkingEventService.addNewEvent(new BigDecimal(10), new Timestamp(3123123L));
        //when
        List<ParkingEvent> parkingEventsList = parkingEventService.getAllEvents();
        //then
        Assertions.assertThat(parkingEventsList.size()).isEqualTo(4);
        Assertions.assertThat(parkingEventsList.get(3).getPaymentDate()).isEqualTo(new Timestamp(3123123L));
        Assertions.assertThat(parkingEventsList.get(3).getPaymentPln()).isEqualTo(new BigDecimal(10));
    }

    @Test
    public void shouldReturnPaymenSum() {
        //given
        //when
        BigDecimal paymentSum = parkingEventService.getPaymentSum();
        //then
        Assertions.assertThat(paymentSum).isEqualTo(new BigDecimal(22).setScale(2));

    }

    @Test
    public void shouldReturnPaymentSumByDate() {
        //given
        //when
        BigDecimal paymentSum20102017 = parkingEventService.getPaymentSumByDate("20", "10", "2017");
        BigDecimal paymentSum21102017 = parkingEventService.getPaymentSumByDate("21", "10", "2017");
        //then
        Assertions.assertThat(paymentSum20102017).isEqualTo(new BigDecimal(15).setScale(2));
        Assertions.assertThat(paymentSum21102017).isEqualTo(new BigDecimal(7).setScale(2));

    }
}
