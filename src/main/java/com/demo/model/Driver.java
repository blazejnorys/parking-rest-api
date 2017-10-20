package com.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Component
@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String car;
    private String bankAccountNumber;
    private boolean isVip;
    @OneToOne
    ParkingMeter parkingMeter;

    public Driver() {
    }

    public Driver(String name, String surname, String car) {
        this.name = name;
        this.surname = surname;
        this.car = car;
    }
}
