package com.udacity.vehicles.domain.manufacturer;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Declares class to hold car manufacturer information.
 */
@Entity
public class Manufacturer {

    @Id
    private Integer code;
    private String name;

    public Manufacturer() { }

    public Manufacturer(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    // added for POST requests that pass a String code in the body:
    public Manufacturer(String code) {
        this.code = Integer.parseInt(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
