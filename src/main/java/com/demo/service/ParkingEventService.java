package com.demo.service;

import com.demo.model.ParkingEvent;
import com.demo.repository.ParkingEventRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class ParkingEventService {

    @Autowired
    private ParkingEventRepository parkingEventRepository;

    public List<ParkingEvent> getAllEvents() {
        return parkingEventRepository.findAll();
    }

    public void addNewEvent(BigDecimal payment, Timestamp date) {
        ParkingEvent parkingEvent = new ParkingEvent(payment, date);
        parkingEventRepository.saveAndFlush(parkingEvent);
    }

    public BigDecimal getPaymentSum() {
        return parkingEventRepository.getPaymentSum();
    }

    public BigDecimal getPaymentSumByDate(String day, String month, String year) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMyyyy");
        DateTime dateTimeStart = formatter.parseDateTime(day + month + year);
        DateTime dateTimeEnd = dateTimeStart.plusDays(1).minusMillis(1);
        return parkingEventRepository.getPaymentSumByDate(new Timestamp(dateTimeStart.getMillis()), new Timestamp(dateTimeEnd.getMillis()));
    }
}
