package com.demo.service;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceTest extends AbstractTransactionalJUnit4SpringContextTests {


    @Autowired
    DriverService driverService;

    Driver testDriver = new Driver("TestDriverName","TestDriverSurname","TestDrvierCar");
    Driver testDriver1 = new Driver("TestDriverName1","TestDriverSurname1","TestDrvierCar1");
    Driver testDriver2 = new Driver("TestDriverName2","TestDriverSurname2","TestDrvierCar2");
    ParkingMeter parkingMeter = new ParkingMeter(false,new Timestamp(10L),new Timestamp(20L));

    @Before
    public void before(){
        driverService.addNewDriver(testDriver2);
        driverService.addNewDriver(testDriver1);
        driverService.addNewDriver(testDriver);
    }

    @Test
    public void shouldFindDriver(){
        //given
        //when
        Driver driverById = driverService.findById(testDriver.getId());
        //then
        Assertions.assertThat(driverById.getName()).isEqualTo("TestDriverName");
    }

    @Test
    public void shouldAddDriver(){
        //given
        Driver driver = new Driver("TestDriverName3","TestDriverSurname3","TestDrvierCar3");
        //when
        driverService.addNewDriver(driver);
        Driver driverById = driverService.findById(driver.getId());
        //then
        Assertions.assertThat(driverById.getName()).isEqualTo("TestDriverName3");
    }

    @Test
    public void shouldListAllDriver(){
        //given
        //when
        //then
        Assertions.assertThat(driverService.findAll().size()).isEqualTo(5);
    }

    @Test
    public void shouldUpdateDriverParkingSpot(){
        //given
        //when
        System.out.println(testDriver.getParkingMeter());
        driverService.updateDriverParkingSpot(testDriver,parkingMeter);
        Driver driverUpdated = driverService.findById(testDriver.getId());
        //then
        Assertions.assertThat(driverUpdated.getParkingMeter()).isNotNull();

    }

    @Test
    public void getDriver(){
        List<Driver> list = driverService.findAll();
        System.out.println(list.size());
        for (Driver driver : list) {
            System.out.println(driver.getName());
        }
    }
}
