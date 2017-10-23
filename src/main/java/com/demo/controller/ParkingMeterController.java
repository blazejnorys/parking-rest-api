package com.demo.controller;

import com.demo.model.ParkingMeter;
import com.demo.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
public class ParkingMeterController {

    @Autowired
    ParkingMeterService parkingMeterService;

    @PostMapping("parking-meter")
    public ParkingMeter addParkingMeter(){
        ParkingMeter parkingMeter = new ParkingMeter();
        return parkingMeterService.addNewParkingMeter(parkingMeter);
    }

    @GetMapping("parking-meter")
    public List<ParkingMeter> getAllParkingMeters(){
        return parkingMeterService.findAll();
    }
}
