package com.udacity.boogle.maps.repository;

import com.udacity.boogle.maps.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByCarId(Long carId);
    Address findByCarId(Long carId);
}
