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
    DriverService driverService;
    @Autowired
    ParkingMeterService parkingMeterService;
    @Autowired
    ParkingEventService parkingEventService;


    @GetMapping("/drivers")
    public List<Driver> getAllDrivers(){
        return driverService.findAll();
    }

    @PostMapping("/drivers")
    public Driver addDriver(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("car") String car,
            @RequestParam("bankAccountNumber") String bankAccountNumber,
            @RequestParam("isVip") boolean isVip

    ){
         Driver driver = new Driver(name,surname,car,bankAccountNumber,isVip);
         driverService.addNewDriver(driver);
            return driver;
    }

    @PostMapping("start/{driverId}/{parkingMeterId}")
    public String startParkingMeter(
            @PathVariable Long driverId,
            @PathVariable Long parkingMeterId
    ){
        ParkingMeter parkingMeter = parkingMeterService.findById(parkingMeterId);
        if(parkingMeter.isOccupied()){
            return "This spot is occupied. Please choose another one";
        }
        Driver driver = driverService.findById(driverId);
        driver.setParkingMeter(parkingMeter);
        parkingMeterService.startParkingMeter(parkingMeter);
        parkingMeterService.update(parkingMeter);
        driverService.updateDriverParkingSpot(driver,parkingMeter);
        return "You have chosen spot nr "+parkingMeterId;
    }

    @PostMapping("stop/{driverId}")
    public String stopParkingMeter(
            @PathVariable Long driverId
    ){
        Driver driver = driverService.findById(driverId);
        ParkingMeter parkingMeter = driver.getParkingMeter();
        double diffInHours = parkingMeterService.resetParkingMeter(parkingMeter,driver);
        double calculatedAmountToBePaid = parkingMeterService.calculateAmountToBePaid(diffInHours,driver);
        parkingMeterService.chargeDriverAccount(driver, calculatedAmountToBePaid);
        parkingMeterService.update(parkingMeter);
        parkingEventService.addNewEvent(calculatedAmountToBePaid,parkingMeter.getEndTime());
        return "You sent a payment of "+calculatedAmountToBePaid+"PLN";

    }
}
