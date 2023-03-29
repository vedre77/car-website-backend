package com.udacity.vehicles.api;

import com.udacity.vehicles.domain.car.Car;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Maps the CarController to the Car class using HATEOAS
 * HATEOAS stands for "Hypermedia as the Engine of Application State"
 * and is a constraint of the REST architecture style. It means that a client interacts with
 * a web service entirely through hypermedia provided dynamically by the server.
 * In the context of Spring, HATEOAS is implemented through the Spring HATEOAS module,
 * which provides a set of tools for building hypermedia-driven RESTful web services.
 * The module includes several classes and interfaces that enable developers to easily create
 * and manipulate links between resources, define resource representations using the HAL format
 * (Hypertext Application Language), and create custom media types for their APIs.
 */
@Component
public class CarResourceAssembler implements ResourceAssembler<Car, Resource<Car>> {

    @Override
    public Resource<Car> toResource(Car car) {
        return new Resource<>(car,
                linkTo(methodOn(CarController.class).get(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).list()).withRel("cars"));

    }
}
