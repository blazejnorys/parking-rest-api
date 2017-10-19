package com.demo.service;

import com.demo.model.ParkingMeter;
import com.demo.repository.ParkingMeterRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ParkingMeterService {

    @Autowired
    ParkingMeterRepository parkingMeterRepository;

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

}
