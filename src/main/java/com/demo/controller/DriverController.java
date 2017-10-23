package com.demo.controller;


import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.service.DriverService;
import com.demo.service.ParkingEventService;
import com.demo.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private ParkingMeterService parkingMeterService;
    @Autowired
    private ParkingEventService parkingEventService;


    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return driverService.findAll();
    }

    @PostMapping("/drivers")
    public Driver addDriver(
            @RequestBody Driver myDriver
    ) {
        driverService.addNewDriver(myDriver);
        return myDriver;
    }

    @PostMapping("start/{driverId}/{parkingMeterId}")
    public String startParkingMeter(
            @PathVariable Long driverId,
            @PathVariable Long parkingMeterId
    ) {
        ParkingMeter parkingMeter = parkingMeterService.findById(parkingMeterId);
        if (parkingMeter.isOccupied()) {
            return "This spot is occupied. Please choose another one";
        }
        Driver driver = driverService.findById(driverId);
        driver.setParkingMeter(parkingMeter);
        parkingMeterService.startParkingMeter(parkingMeter);
        parkingMeterService.update(parkingMeter);
        driverService.update(driver);
        return "You have chosen spot nr " + parkingMeterId;
    }

    @PostMapping("stop/{driverId}")
    public String stopParkingMeter(
            @PathVariable Long driverId
    ) {
        Driver driver = driverService.findById(driverId);
        ParkingMeter parkingMeter = driver.getParkingMeter();
        parkingMeterService.stopParkingMeter(parkingMeter,driver);
        double calculatedAmountToBePaid = parkingMeterService.calculateAmountToBePaid(parkingMeter, driver);
        parkingMeterService.chargeDriverAccount(driver, calculatedAmountToBePaid);
        parkingMeterService.reset(parkingMeter);
        parkingEventService.addNewEvent(calculatedAmountToBePaid, parkingMeter.getEndTime());
        return "You sent a payment of " + calculatedAmountToBePaid + "PLN";

    }
}
