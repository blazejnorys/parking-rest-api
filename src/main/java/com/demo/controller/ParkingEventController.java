package com.demo.controller;

import com.demo.model.ParkingEvent;
import com.demo.service.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingEventController {

    @Autowired
    private ParkingEventService parkingEventService;

    @GetMapping("/events")
    public List<ParkingEvent> getAllEvents() {
        return parkingEventService.getAllEvents();
    }

    @GetMapping("/events-sum")
    public double getPaymentSum() {
        return parkingEventService.getPaymentSum();
    }

    @GetMapping("/events-sum-date/{year}/{month}/{day}")
    public double getPaymentByDate(
            @PathVariable String year,
            @PathVariable String month,
            @PathVariable String day
    ) {
        return parkingEventService.getPaymentSumByDate(year, month, day);
    }

}
