package com.demo.controller;


import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.service.DriverService;
import com.demo.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;
    @Autowired
    ParkingMeterService parkingMeterService;


    @GetMapping("/drivers")
    public List<Driver> getAllDrivers(){
        return driverService.findAll();
    }

    @PostMapping("/driver")
    public Driver addDriver(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("car") String car
    ){
         Driver driver = new Driver(name,surname,car);
         driverService.addNewDriver(driver);
            return driver;
    }

    @PostMapping("start/{driverId}/{parkingMeterId}")
    public void startParkingMeter(
            @PathVariable Long driverId,
            @PathVariable Long parkingMeterId
    ){
        ParkingMeter parkingMeter = parkingMeterService.findById(parkingMeterId);
        Driver driver = driverService.findById(driverId);
        driver.setParkingMeter(parkingMeter);
        parkingMeter.setOccupied(true);
        parkingMeter.setStartTime(LocalDateTime.now());
        parkingMeterService.update(parkingMeter);
        driverService.updateDriver(driver,parkingMeter);


    }

    @PostMapping("stop/{driverId}")
    public void stopParkingMeter(
            @PathVariable Long driverId
    ){
        Driver driver = driverService.findById(driverId);
        ParkingMeter parkingMeter = driver.getParkingMeter();
        double diffInHours = parkingMeterService.resetParkingMeter(parkingMeter,driver);
        double calculatedAmountToBePaid = parkingMeterService.calculateAmountToBePaid(diffInHours,driver);
        parkingMeterService.chargeDriverAccount(driver, calculatedAmountToBePaid);
        parkingMeterService.update(parkingMeter);

    }
}