package com.demo.service;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.repository.ParkingMeterRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
public class ParkingMeterService {

    @Autowired
    ParkingMeterRepository parkingMeterRepository;
    @Autowired
    ParkingRatesCalculator parkingRatesCalculator;

    public void addNewParkingMeter(ParkingMeter parkingMeter){
        parkingMeterRepository.saveAndFlush(parkingMeter);
    }

    public List<ParkingMeter> findAll(){
        return parkingMeterRepository.findAll();
    }

    public ParkingMeter findById(Long id){
        return parkingMeterRepository.findOne(id);
    }

    public void  update(ParkingMeter parkingMeter){
        ParkingMeter parkingMeterToUpdate = parkingMeterRepository.findOne(parkingMeter.getId());
        parkingMeterToUpdate.setStartTime(parkingMeter.getStartTime());
        parkingMeterToUpdate.setEndTime(parkingMeter.getEndTime());
        parkingMeterToUpdate.setOccupied(parkingMeter.isOccupied());
        parkingMeterRepository.saveAndFlush(parkingMeterToUpdate);

    }

    public double resetParkingMeter(ParkingMeter parkingMeter, Driver driver){
        driver.setParkingMeter(null);
        parkingMeter.setOccupied(false);
        parkingMeter.setEndTime(LocalDateTime.now());
        double diffInMinutes = Math.ceil((double) java.time.Duration.between(parkingMeter.getStartTime(),parkingMeter.getEndTime()).toMinutes());
        System.out.println("Your car has been here for "+diffInMinutes);
        return diffInMinutes;
    }

    public double calculateAmountToBePaid(double diffInHours, Driver driver) {
        if (driver.isVip()) {
            return parkingRatesCalculator.calculateParkingRateVip(diffInHours);
        } else {
            return parkingRatesCalculator.calculateParkingRateRegular(diffInHours);
        }
    }


    public void chargeDriverAccount(Driver driver, double AmountToBePaid){
        String bankAccount = driver.getBankAccountNumber();
        System.out.println("You sent a payment of "+AmountToBePaid+"PLN");




    }

}
