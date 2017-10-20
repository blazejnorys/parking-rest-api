package com.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Component
@Data
public class ParkingMeter {

    @Id
    @GeneratedValue
    private long id;
    private boolean occupied = false;
    private Timestamp startTime;
    private Timestamp endTime;

    public ParkingMeter() {
    }

    public ParkingMeter(boolean occupied, Timestamp startTime, Timestamp endTime) {
        this.occupied = occupied;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
