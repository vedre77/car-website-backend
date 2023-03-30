package com.udacity.vehicles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

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
    public void carCRUD() throws Exception {;
        // GET cars
        mockMvc().perform(get("/cars"))
                .andExpect(status().isOk());
        // POST car
        Car car = new Car();
        // mandatory fields to POST: condition, details.body, details.model, details.manufacturer
        car.setCondition(Condition.USED);
        Details details = new Details();
        Manufacturer manufacturer = new Manufacturer(100);
        details.setManufacturer(manufacturer);
        details.setBody("Low");
        details.setModel("A3");
        car.setDetails(details);
        URI uri = new URI("/cars");
        mockMvc().perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(car)))
                .andExpect(status().isOk());
        // PUT car
        car.setPrice("99999-test");
        mockMvc().perform(MockMvcRequestBuilders.put("/cars/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("99999-test"))
                .andReturn();
        // DELETE car
        mockMvc().perform(delete("/cars/1"))
                .andExpect(status().isNoContent());

    }
    @Test
    public void testGetCars() throws Exception {
        mockMvc().perform(get("/cars"))
                .andExpect(status().isOk());
    }



}
