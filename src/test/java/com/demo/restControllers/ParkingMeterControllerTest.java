package com.demo.restControllers;

import com.demo.model.ParkingMeter;
import com.demo.service.ParkingMeterService;
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

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingMeterControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ParkingMeterService parkingMeterService;
    private MediaType mediaType = MediaType.APPLICATION_JSON;

    @Before
    public void before() {
        ParkingMeter parkingMeter = new ParkingMeter(
                true, new Timestamp(946681200000L), new Timestamp(949359600000L));// 01.01.2000 00:00:00, 01.02.2000 00:00:00
        parkingMeterService.addNewParkingMeter(parkingMeter);
    }

    @Test
    public void shouldAddNewParkingMeter() throws Exception {

        List<ParkingMeter> list = parkingMeterService.findAll();
        for (ParkingMeter parkingMeter : list) {
            System.out.println(parkingMeter);
        }
        mvc.perform(post("/parking-meter/")
                .contentType(mediaType)
                .accept(mediaType))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldListAllParkingMeters() throws Exception {
        mvc.perform(get("/parking-meter")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].occupied", is(false)))
                .andExpect(jsonPath("$[2].occupied", is(true)))
                .andExpect(jsonPath("$[2].startTime", is(946681200000L)))
                .andExpect(jsonPath("$[2].endTime", is(949359600000L)));
    }
}
