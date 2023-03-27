package com.udacity.pricing.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class DataInitializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder();
    }

    @Autowired
    private PriceRepository priceRepository;

    @PostConstruct
    public void initializeData() throws IOException {
        ObjectMapper objectMapper = jacksonBuilder().build();
        InputStream inputStream = getClass().getResourceAsStream("/prices.json");
        List<Price> entities = objectMapper.readValue(inputStream, new TypeReference<List<Price>>(){});
        priceRepository.saveAll(entities);
    }
}
