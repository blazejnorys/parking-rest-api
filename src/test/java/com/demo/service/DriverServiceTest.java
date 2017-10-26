package com.demo.service;

import com.demo.model.ClientType;
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
    private DriverService driverService;

    private Driver testDriver = new Driver("TestDriverName", "TestDriverSurname", "TestDrvierCar", "52344", ClientType.VIP);
    private Driver testDriver1 = new Driver("TestDriverName1", "TestDriverSurname1", "TestDrvierCar1", "452345", ClientType.REGULAR);
    private Driver testDriver2 = new Driver("TestDriverName2", "TestDriverSurname2", "TestDrvierCar2", "7456546", ClientType.VIP);
    private ParkingMeter parkingMeter = new ParkingMeter(false, new Timestamp(10L), new Timestamp(20L));

    @Before
    public void before() {
        driverService.addNewDriver(testDriver2);
        driverService.addNewDriver(testDriver1);
        driverService.addNewDriver(testDriver);
    }

    @Test
    public void shouldFindDriver() {
        //given
        //when
        Driver driverById = driverService.findById(testDriver.getId());
        //then
        Assertions.assertThat(driverById.getName()).isEqualTo("TestDriverName");
    }

    @Test
    public void shouldAddDriver() {
        //given
        Driver driver = new Driver("TestDriverName3", "TestDriverSurname3", "TestDrvierCar3", "43523512", ClientType.REGULAR);
        //when
        driverService.addNewDriver(driver);
        Driver driverById = driverService.findById(driver.getId());
        //then
        Assertions.assertThat(driverById.getName()).isEqualTo("TestDriverName3");
    }

    @Test
    public void shouldListAllDriver() {
        //given
        //when
        //then
        Assertions.assertThat(driverService.findAll().size()).isEqualTo(5);
    }

    @Test
    public void shouldUpdateDriverParkingSpot() {
        //given
        Driver driver = new Driver("TestDriverName3", "TestDriverSurname3", "TestDrvierCar3", "43523512", ClientType.REGULAR);
        //when
        driver.setName("Changed");
        driverService.update(driver);
        Driver driverUpdated = driverService.findById(driver.getId());
        //then
        Assertions.assertThat(driverUpdated.getName()).isEqualTo("Changed");

    }

}
