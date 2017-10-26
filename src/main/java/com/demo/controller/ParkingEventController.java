package com.demo.controller;

import com.demo.model.ParkingEvent;
import com.demo.service.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class ParkingEventController {

    private ParkingEventService parkingEventService;

    @Autowired
    public ParkingEventController(ParkingEventService parkingEventService) {
        this.parkingEventService = parkingEventService;
    }

    @GetMapping("/events")
    public List<ParkingEvent> getAllEvents() {
        return parkingEventService.getAllEvents();
    }

    @GetMapping("/events-sum")
    public double getPaymentSum() {
        return parkingEventService.getPaymentSum();
    }

    @GetMapping("/events-sum-date/{day}/{month}/{year}")
    public double getPaymentByDate(
            @PathVariable String day,
            @PathVariable String month,
            @PathVariable String year
    ) {
        return parkingEventService.getPaymentSumByDate(day, month, year);
    }

}
