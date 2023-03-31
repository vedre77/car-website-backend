package com.udacity.boogle.maps.service;

import com.udacity.boogle.maps.Address;
import com.udacity.boogle.maps.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address searchCarAddress(Address randomAddress, Long id, Double lat, Double lon) {

        boolean carAddressExists = addressRepository.existsByCarId(id);
        //System.out.println(carAddressExists);
        if (carAddressExists) {
            // check for latitude and longitude change
            Address savedCarAddress = addressRepository.findByCarId(id);
            if (Objects.equals(savedCarAddress.getLat(), lat) && Objects.equals(savedCarAddress.getLon(), lon)) {
                return savedCarAddress;
            } else {
                addressRepository.delete(savedCarAddress);
            }
        }
        Address newCarAddress = new Address();
        newCarAddress.setCarId(id);
        newCarAddress.setLat(lat);
        newCarAddress.setLon(lon);
        newCarAddress.setAddress(randomAddress.getAddress());
        newCarAddress.setCity(randomAddress.getCity());
        newCarAddress.setState(randomAddress.getState());
        newCarAddress.setZip(randomAddress.getZip());
        addressRepository.save(newCarAddress);
        return newCarAddress;
        }
}
