package com.demo.repository;

import com.demo.model.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {

}
