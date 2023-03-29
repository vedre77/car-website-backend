package com.udacity.vehicles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.vehicles.domain.car.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiclesApiApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Bean
    public MockMvc mockMvc() {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // defines a toJson method that converts a car object to JSON format
    private String toJson(Car car) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(car);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    // checking if the HTTP POST request to '/cars' returns a response with HTTP status code 200 (OK):
    public void createCar() throws Exception {;

        Car car = new Car();
        URI uri = new URI("/cars");
        String carJson = toJson(car);
        System.out.println("Car JSON: " + carJson);

        mockMvc().perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(car)))
                .andExpect(status().isOk());
    }

}
