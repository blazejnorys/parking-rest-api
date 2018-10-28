package com.demo.service;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.repository.ParkingMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import static java.math.BigDecimal.ROUND_HALF_UP;

@Service
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

    private int getDifferenceInHours(ParkingMeter parkingMeter) {
        return (int) Math.ceil((double) java.time.Duration.
                between(parkingMeter.getStartTime().toLocalDateTime(), parkingMeter.getEndTime().toLocalDateTime())
                .getSeconds() / 3600);
    }

    public BigDecimal calculateParkingRate(ParkingMeter parkingMeter, Driver driver) {
        int diffInHours = this.getDifferenceInHours(parkingMeter);
        return (parkingRatesCalculator.calculateParkingRate(diffInHours, driver));
    }

    public void chargeDriverAccount(Driver driver, BigDecimal amountToBePaid) {
        String bankAccount = driver.getBankAccountNumber();
        System.out.println("You sent a payment of " + amountToBePaid + "PLN");
    }
}
