package com.udacity.boogle.maps.service;

import com.udacity.boogle.maps.Address;
import com.udacity.boogle.maps.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address searchCarAddress(Address randomAddress, Long id) {


        Boolean carAddressExists = addressRepository.existsByCarId(id);
        //System.out.println(carAddressExists);
        if (carAddressExists) {
            Address savedCarAddress = addressRepository.findByCarId(id);
            return savedCarAddress;
        } else {
            Address newCarAddress = new Address();
            newCarAddress.setCarId(id);
            //
            newCarAddress.setAddress(randomAddress.getAddress());
            newCarAddress.setCity(randomAddress.getCity());
            newCarAddress.setState(randomAddress.getState());
            newCarAddress.setZip(randomAddress.getZip());

            addressRepository.save(newCarAddress);

            return newCarAddress;
        }
    }
}
