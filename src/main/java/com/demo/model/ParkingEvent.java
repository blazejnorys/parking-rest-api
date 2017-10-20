package com.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
@Component
@Data
public class ParkingEvent {

    @Id
    @GeneratedValue
    private long Id;
    private double paymentPln;
    protected Timestamp paymentDate;

    public ParkingEvent() {
    }

    public ParkingEvent(double paymentPln, Timestamp paymentDate) {
        this.paymentPln = paymentPln;
        this.paymentDate = paymentDate;
    }
}
