package com.demo.service;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.repository.ParkingMeterRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Service
@Data
public class ParkingMeterService {

    @Autowired
    private ParkingMeterRepository parkingMeterRepository;
    @Autowired
    private ParkingRatesCalculator parkingRatesCalculator;

    public ParkingMeter addNewParkingMeter(ParkingMeter parkingMeter) {
        return parkingMeterRepository.saveAndFlush(parkingMeter);
    }

    public List<ParkingMeter> findAll() {
        return parkingMeterRepository.findAll();
    }

    public ParkingMeter findById(Long id) {
        return parkingMeterRepository.findOne(id);
    }

    public void update(ParkingMeter parkingMeter) {
        parkingMeterRepository.saveAndFlush(parkingMeter);

    }

    public void reset(ParkingMeter parkingMeter) {
        parkingMeter.setStartTime(null);
        parkingMeter.setEndTime(null);
        parkingMeterRepository.saveAndFlush(parkingMeter);

    }

    public void startParkingMeter(ParkingMeter parkingMeter) {
        parkingMeter.setOccupied(true);
        parkingMeter.setStartTime(new Timestamp(System.currentTimeMillis()));
    }

    public void stopParkingMeter(ParkingMeter parkingMeter, Driver driver) {
        driver.setParkingMeter(null);
        parkingMeter.setOccupied(false);
        parkingMeter.setEndTime(new Timestamp(System.currentTimeMillis()));
    }

    private double getDifferenceInHours(ParkingMeter parkingMeter){
        return Math.ceil((double) java.time.Duration.
                between(LocalDateTime.ofInstant(Instant.ofEpochMilli(parkingMeter.getStartTime().getTime()),
                        TimeZone.getDefault().toZoneId()),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(parkingMeter.getEndTime().getTime()),
                                TimeZone.getDefault().toZoneId()))
                .getSeconds() / 3600);
    }

    public double calculateAmountToBePaid(ParkingMeter parkingMeter, Driver driver) {
        double diffInHours = this.getDifferenceInHours(parkingMeter);
        if (driver.isVip()) {
            return parkingRatesCalculator.calculateParkingRateVip(diffInHours);
        } else {
            return parkingRatesCalculator.calculateParkingRateRegular(diffInHours);
        }
    }

    public void chargeDriverAccount(Driver driver, double AmountToBePaid) {
        String bankAccount = driver.getBankAccountNumber();
        System.out.println("You sent a payment of " + AmountToBePaid + "PLN");
    }

}
