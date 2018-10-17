package com.demo.restControllers;

import com.demo.model.ClientType;
import com.demo.model.Driver;
import com.demo.model.ParkingMeter;
import com.demo.service.DriverService;
import com.demo.service.ParkingMeterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DriverControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DriverService driverService;
    @Autowired
    private ParkingMeterService parkingMeterService;
    @Autowired
    private MockMvc mvc;
    private MediaType mediaType = MediaType.APPLICATION_JSON;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() {
        Driver restDriverTest = new Driver("RestDriverName", "RestDriverSurname", "RestDriverCar", "XXXXXXXX", ClientType.REGULAR);
        Driver restDriverTest1 = new Driver("VipRestDriverName", "VipRestDriverSurname", "VipRestDriverCar", "XXXXXXXX", ClientType.VIP);
        driverService.addNewDriver(restDriverTest);
        driverService.addNewDriver(restDriverTest1);
    }

    @Test
    public void shouldReturnAllDrivers() throws Exception {
        mvc.perform(get("/drivers")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("Marcin")))
                .andExpect(jsonPath("$[2].surname", is("RestDriverSurname")))
                .andExpect(jsonPath("$[1].clientType", is("VIP")))
                .andExpect(jsonPath("$[3].clientType", is("VIP")));
    }

    @Test
    public void shouldAddNewDriver() throws Exception {
        Driver newDriver = new Driver("X", "Y", "Tojota", "3243242", ClientType.VIP);
        String driverJson = objectMapper.writeValueAsString(newDriver);

        mvc.perform(post("/drivers")
                .contentType(mediaType)
                .content(driverJson)
                .accept(mediaType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("X")))
                .andExpect(jsonPath("$.car", is("Tojota")))
                .andExpect(jsonPath("$.clientType", is("VIP")));
    }

    @Test
    public void shouldStartParkingMeter() throws Exception {
        ParkingMeter parkingMeter = new ParkingMeter(false, null, null);
        Driver driver = new Driver("X", "Y", "Z", "M", ClientType.REGULAR);
        driverService.addNewDriver(driver);
        parkingMeterService.addNewParkingMeter(parkingMeter);
        Long driverId = driver.getId();
        Long parkingMeterId = parkingMeter.getId();

        mvc.perform(post("/start/" + driverId + "/" + parkingMeterId)
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk());

        mvc.perform(get("/drivers")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[4].id", is(driverId.intValue())))
                .andExpect(jsonPath("$[4].name", is("X")))
                .andExpect(jsonPath("$[4].surname", is("Y")))
                .andExpect(jsonPath("$[4].clientType", is("REGULAR")))
                .andExpect(jsonPath("$[4].parkingMeter", is(notNullValue())));
    }

    @Test
    public void shouldStopParkingMeter() throws Exception {
        ParkingMeter parkingMeter = new ParkingMeter(false, null, null);
        Driver driver = new Driver("X", "Y", "Z", "M", ClientType.REGULAR);
        driverService.addNewDriver(driver);
        parkingMeterService.addNewParkingMeter(parkingMeter);
        Long driverId = driver.getId();
        Long parkingMeterId = parkingMeter.getId();

        mvc.perform(post("/start/" + driverId + "/" + parkingMeterId)
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk());


        mvc.perform(post("/stop/" + driverId)
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk());

        mvc.perform(get("/drivers")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[4].id", is(driverId.intValue())))
                .andExpect(jsonPath("$[4].name", is("X")))
                .andExpect(jsonPath("$[4].surname", is("Y")))
                .andExpect(jsonPath("$[4].clientType", is("REGULAR")))
                .andExpect(jsonPath("$[4].parkingMeter", is(nullValue())));
    }
}
