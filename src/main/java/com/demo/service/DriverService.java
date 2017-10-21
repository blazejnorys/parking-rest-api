package com.demo.service;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.repository.DriverRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public void addNewDriver(Driver driver){
        driverRepository.saveAndFlush(driver);
    }

    public List<Driver> findAll(){
        return driverRepository.findAll();
    }

    public Driver findById(Long id){
        return driverRepository.findOne(id);
    }

    public void updateDriverParkingSpot(Driver driver, ParkingMeter parkingMeter){
        Driver driverToUpdate = driverRepository.findOne(driver.getId());
        driverToUpdate.setParkingMeter(parkingMeter);
    }


}
