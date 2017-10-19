package com.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Component
@Data
public class ParkingMeter {

    @Id
    @GeneratedValue
    private long id;
    private boolean occupied = false;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ParkingMeter() {
    }

    public ParkingMeter(boolean occupied, LocalDateTime startTime, LocalDateTime endTime) {
        this.occupied = occupied;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
