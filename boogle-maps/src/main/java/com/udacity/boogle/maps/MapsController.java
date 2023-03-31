package com.udacity.boogle.maps;

import com.udacity.boogle.maps.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
public class MapsController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public Address get(@RequestParam Double lat, @RequestParam Double lon, @RequestParam Long carId) {
        Address randomAddress = MockAddressRepository.getRandom();
        return addressService.searchCarAddress(randomAddress, carId, lat, lon);

    }
}
