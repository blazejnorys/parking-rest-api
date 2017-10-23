package com.demo.restControllers;

import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.service.DriverService;
import com.demo.service.ParkingMeterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DriverControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    DriverService driverService;
    @Autowired
    ParkingMeterService parkingMeterService;
    @Autowired
    MockMvc mvc;
    private MediaType mediaType = MediaType.APPLICATION_JSON;

    @Before
    public void before(){
        Driver restDriverTest = new Driver("RestDriverName","RestDriverSurname","RestDriverCar","XXXXXXXX",false);
        Driver restDriverTest1 = new Driver("VipRestDriverName","VipRestDriverSurname","VipRestDriverCar","XXXXXXXX",true);
        driverService.addNewDriver(restDriverTest);
        driverService.addNewDriver(restDriverTest1);
        Long driverId = restDriverTest.getId();
    }

    @Test
    public void shouldReturnAllDrivers() throws Exception{

        mvc.perform(get("/drivers")
        .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$",hasSize(4)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[1].name",is("Marcin")))
                .andExpect(jsonPath("$[2].surname",is("RestDriverSurname")))
                .andExpect(jsonPath("$[1].vip",is(true)))
                .andExpect(jsonPath("$[3].vip",is(true)));

    }
    @Test
    public void shouldAddNewDrive() throws Exception{

        mvc.perform(post("/drivers?name=AddedName&surname=AddedSurname&car=Tojota&bankAccountNumber=YYY&isVip=true")
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("AddedName")))
                .andExpect(jsonPath("$.car",is("Tojota")))
                .andExpect(jsonPath("$.vip",is(true)));
    }

    @Test
    public void shouldStartParkingMeter() throws Exception{
        ParkingMeter parkingMeter = new ParkingMeter(false,null,null);
        Driver driver = new Driver("X","Y","Z","M",false);
        driverService.addNewDriver(driver);
        parkingMeterService.addNewParkingMeter(parkingMeter);
        Long driverId = driver.getId();
        Long parkingMeterId = parkingMeter.getId();

        mvc.perform(post("/start/"+driverId+"/"+parkingMeterId)
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is("You have chosen spot nr "+parkingMeterId)));

        mvc.perform(get("/drivers")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[4].id",is(driverId.intValue())))
                .andExpect(jsonPath("$[4].name",is("X")))
                .andExpect(jsonPath("$[4].surname",is("Y")))
                .andExpect(jsonPath("$[4].vip",is(false)))
                .andExpect(jsonPath("$[4].parkingMeter",is(notNullValue())));

    }

    @Test
    public void shouldStopParkingMeter() throws Exception{
        ParkingMeter parkingMeter = new ParkingMeter(false,null,null);
        Driver driver = new Driver("X","Y","Z","M",false);
        driverService.addNewDriver(driver);
        parkingMeterService.addNewParkingMeter(parkingMeter);
        Long driverId = driver.getId();
        Long parkingMeterId = parkingMeter.getId();

        mvc.perform(post("/start/"+driverId+"/"+parkingMeterId)
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is("You have chosen spot nr "+parkingMeterId)));


        mvc.perform(post("/stop/"+driverId)
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk());

        mvc.perform(get("/drivers")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[4].id",is(driverId.intValue())))
                .andExpect(jsonPath("$[4].name",is("X")))
                .andExpect(jsonPath("$[4].surname",is("Y")))
                .andExpect(jsonPath("$[4].vip",is(false)))
                .andExpect(jsonPath("$[4].parkingMeter",is(nullValue())));

    }

}
