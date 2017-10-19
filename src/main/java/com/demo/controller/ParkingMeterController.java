package com.demo.controller;

import com.demo.model.ParkingMeter;
import com.demo.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingMeterController {

    @Autowired
    ParkingMeterService parkingMeterService;

    @PostMapping("parking-meter")
    public void addParkingMeter(){
        ParkingMeter parkingMeter = new ParkingMeter();
        parkingMeterService.addNewParkingMeter(parkingMeter);
    }
}
