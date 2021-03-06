package com.demo.service;

import com.demo.model.Driver;
import com.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public void addNewDriver(Driver driver) {
        driverRepository.saveAndFlush(driver);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(Long id) {
        return driverRepository.findOne(id);
    }

    public void update(Driver driver) {
        driverRepository.saveAndFlush(driver);
    }


}
