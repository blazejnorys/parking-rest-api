package com.demo.service;

import com.demo.model.ParkingEvent;
import com.demo.repository.ParkingEventRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Data
public class ParkingEventService {

    @Autowired
    private ParkingEventRepository parkingEventRepository;

    public List<ParkingEvent> getAllEvents() {
        return parkingEventRepository.findAll();
    }

    public void addNewEvent(double payment, Timestamp date) {
        ParkingEvent parkingEvent = new ParkingEvent(payment, date);
        parkingEventRepository.saveAndFlush(parkingEvent);
    }

    public double getPaymentSum() {
        return parkingEventRepository.getPaymentSum();
    }

    public double getPaymentSumByDate(String year, String month, String day) {
        String first = year + "-" + month + "-" + day;
        String last = year + "-" + month + "-" + day;
        first += " 00:00:00.000000";
        last += " 23:59:59.999999";
        return parkingEventRepository.getPaymentSumByDate(Timestamp.valueOf(first), Timestamp.valueOf(last));
    }


}
