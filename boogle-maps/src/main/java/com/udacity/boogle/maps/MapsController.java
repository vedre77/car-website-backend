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
        return addressService.searchCarAddress(randomAddress, carId);

    }
    /**
     * The Boogle Maps application does not actually store the address assigned to a given
     * vehicle based on latitude and longitude, and instead randomly assigns a new one each
     * time it is called. How could you update this to track which address is assigned to which
     * vehicle? What happens if the vehicle latitude and longitude is updated in the Vehicles API?
     */
}
