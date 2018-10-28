package com.demo.controller;


import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.service.DriverService;
import com.demo.service.ParkingEventService;
import com.demo.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class DriverController {

    private DriverService driverService;
    private ParkingMeterService parkingMeterService;
    private ParkingEventService parkingEventService;

    @Autowired
    public DriverController(DriverService driverService, ParkingMeterService parkingMeterService, ParkingEventService parkingEventService) {
        this.driverService = driverService;
        this.parkingMeterService = parkingMeterService;
        this.parkingEventService = parkingEventService;
    }

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
    public void startParkingMeter(
            @PathVariable Long driverId,
            @PathVariable Long parkingMeterId
    ) {
        ParkingMeter parkingMeter = parkingMeterService.findById(parkingMeterId);
        if (parkingMeter.isOccupied()) {
        }
        Driver driver = driverService.findById(driverId);
        driver.setParkingMeter(parkingMeter);
        parkingMeterService.startParkingMeter(parkingMeter);
        parkingMeterService.update(parkingMeter);
        driverService.update(driver);
    }

    @PostMapping("stop/{driverId}")
    public void stopParkingMeter(
            @PathVariable Long driverId
    ) {
        Driver driver = driverService.findById(driverId);
        ParkingMeter parkingMeter = driver.getParkingMeter();
        parkingMeterService.stopParkingMeter(parkingMeter, driver);
        BigDecimal calculatedAmountToBePaid = parkingMeterService.calculateParkingRate(parkingMeter, driver);
        parkingMeterService.chargeDriverAccount(driver, calculatedAmountToBePaid);
        parkingMeterService.reset(parkingMeter);
        parkingEventService.addNewEvent(calculatedAmountToBePaid, parkingMeter.getEndTime());
    }
}
