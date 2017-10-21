package com.demo.service;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import org.assertj.core.api.Assertions;
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
public class ParkingMeterTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ParkingMeterService parkingMeterService;

    @Test
    public void shouldListAllParkingMeters(){
        //given
        //when
        List<ParkingMeter> parkingMeterList=parkingMeterService.findAll();
        //then
        Assertions.assertThat(parkingMeterList.size()).isEqualTo(2);
    }

    @Test
    public void shouldAddNewParkingMeter(){
        //given
        ParkingMeter parkingMeter = new ParkingMeter(false,new Timestamp(10L),new Timestamp(20L));
        //when
        parkingMeterService.addNewParkingMeter(parkingMeter);
        List<ParkingMeter> parkingMeterList=parkingMeterService.findAll();
        //then
        Assertions.assertThat(parkingMeterList.size()).isEqualTo(3);

    }

    @Test
    public void shouldFindById(){
        //given
        ParkingMeter parkingMeter = new ParkingMeter(false,new Timestamp(500000L),new Timestamp(10000000L));
        //when
        parkingMeterService.addNewParkingMeter(parkingMeter);
        ParkingMeter parkingMeterById = parkingMeterService.findById(parkingMeter.getId());
        //then
        Assertions.assertThat(parkingMeterById.getStartTime()).isEqualTo(new Timestamp(500000L));
        Assertions.assertThat(parkingMeterById.getEndTime()).isEqualTo(new Timestamp(10000000L));
        Assertions.assertThat(parkingMeterById.isOccupied()).isEqualTo(false);
    }

    @Test
    public void shouldUpdateParkingMeter(){
        //given
        ParkingMeter parkingMeter = new ParkingMeter(false,new Timestamp(500000L),new Timestamp(10000000L));
        parkingMeterService.addNewParkingMeter(parkingMeter);
        parkingMeter.setOccupied(true);
        parkingMeter.setStartTime(new Timestamp(10L));
        parkingMeter.setEndTime(new Timestamp(20L));
        //when
        parkingMeterService.update(parkingMeter);
        ParkingMeter parkingMeterById = parkingMeterService.findById(parkingMeter.getId());
        //then
        Assertions.assertThat(parkingMeterById.getStartTime()).isEqualTo(new Timestamp(10L));
        Assertions.assertThat(parkingMeterById.getEndTime()).isEqualTo(new Timestamp(20L));
        Assertions.assertThat(parkingMeterById.isOccupied()).isEqualTo(true);
    }

    @Test
    public void shouldStartParkingMeter(){
        //given
        ParkingMeter parkingMeter = new ParkingMeter(false,null,null);
        parkingMeterService.addNewParkingMeter(parkingMeter);
        //when
        parkingMeterService.startParkingMeter(parkingMeter);
        //then
        Assertions.assertThat(parkingMeter.getStartTime()).isNotNull();
        Assertions.assertThat(parkingMeter.getEndTime()).isNull();
        Assertions.assertThat(parkingMeter.isOccupied()).isEqualTo(true);
    }

    @Test
    public void shouldResetParkingMeter(){
        //given
        ParkingMeter parkingMeter = new ParkingMeter(true,new Timestamp(10L),null);
        Driver driver = new Driver("TestDriverName","TestDriveSurname","TestDriverCar");
        driver.setParkingMeter(parkingMeter);
        //when
        double diffInHours = parkingMeterService.resetParkingMeter(parkingMeter,driver);
        System.out.println(diffInHours);
        //then
        Assertions.assertThat(parkingMeter.isOccupied()).isEqualTo(false);
        Assertions.assertThat(parkingMeter.getEndTime()).isNotNull();
        Assertions.assertThat(driver.getParkingMeter()).isNull();
        Assertions.assertThat(diffInHours).isGreaterThan(0);
    }

    @Test
    public void shouldCalculateAmountToBePaidForRegularAndVipDriver(){
        //given
        Driver testDriverRegular = new Driver("TestDriverName","TestDriveSurname","TestDriverCar","23123123",false);
        Driver testDriverVip = new Driver("TestDriverName1","TestDriveSurname1","TestDriverCar1","324524234",true);
        double diffInHours = 3.0;
        //when
        double amountToBePaidRegularDriver = parkingMeterService.calculateAmountToBePaid(diffInHours,testDriverRegular);
        double amountToBePaidVipDriver = parkingMeterService.calculateAmountToBePaid(diffInHours,testDriverVip);
        //then
        Assertions.assertThat(amountToBePaidRegularDriver).isEqualTo(7.0);
        Assertions.assertThat(amountToBePaidVipDriver).isEqualTo(5.0);

    }

}
