package com.demo.restControllers;

import com.demo.service.ParkingEventService;
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

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingEventControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private MockMvc mvc;
    private MediaType mediaType = MediaType.APPLICATION_JSON;
    @Autowired
    private ParkingEventService parkingEventService;

    @Before
    public void before() {
        parkingEventService.addNewEvent(new BigDecimal(3.33), new Timestamp(946681200000L));// 01.01.2000 00:00:00
        parkingEventService.addNewEvent(new BigDecimal(4.44), new Timestamp(949359600000L));// 01.02.2000 00:00:00
    }

    @Test
    public void shouldReturnPaymentSum() throws Exception {
        mvc.perform(get("/events-sum")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", is(22.0 + 3.33 + 4.44)));
    }

    @Test
    public void shouldReturnPaymentSumByDate() throws Exception {
        mvc.perform(get("/events-sum-date/01/01/2000")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", is(3.33)));
    }

    @Test
    public void shouldListAllParkingEvents() throws Exception {
        mvc.perform(get("/events")
                .contentType(mediaType))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(mediaType))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paymentPln", is(5.0)))
                .andExpect(jsonPath("$[2].paymentPln", is(7.0)))
                .andExpect(jsonPath("$[1].paymentDate", is(1508515932100L))) //20.10.2017 18:12:12
                .andExpect(jsonPath("$[2].paymentDate", is(1508595376100L)));//21.10.2017 16:16:16
    }
}
