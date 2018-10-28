package com.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Data
public class ParkingEvent {

    @Id
    @GeneratedValue
    private long Id;
    private BigDecimal paymentPln;
    protected Timestamp paymentDate;

    public ParkingEvent() {
    }

    public ParkingEvent(BigDecimal paymentPln, Timestamp paymentDate) {
        this.paymentPln = paymentPln;
        this.paymentDate = paymentDate;
    }
}
