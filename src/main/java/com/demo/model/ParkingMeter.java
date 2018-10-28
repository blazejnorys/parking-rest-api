package com.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class ParkingMeter {

    @Id
    @GeneratedValue
    private long id;
    private boolean occupied = false;
    private Timestamp startTime;
    private Timestamp endTime;

    @Version
    private Integer version;

    public ParkingMeter() {
    }

    public ParkingMeter(boolean occupied, Timestamp startTime, Timestamp endTime) {
        this.occupied = occupied;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
