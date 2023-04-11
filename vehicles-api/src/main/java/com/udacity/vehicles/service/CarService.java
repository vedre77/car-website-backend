package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient mapsClient;
    private final PriceClient priceClient;

    public CarService(CarRepository repository, MapsClient mapsClient, PriceClient priceClient) {

        this.repository = repository;
        WebClient webClientMaps = WebClient.builder().baseUrl("http://localhost:9191").build();
        WebClient webClientPrice = WebClient.builder().baseUrl("http://localhost:8762").build();
        ModelMapper modelMapper = new ModelMapper();
        this.mapsClient = new MapsClient(webClientMaps, modelMapper);
        this.priceClient = new PriceClient(webClientPrice);
    }
    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {

        List<Car> carList = repository.findAll();
        for (Car car: carList) {
            String price = this.priceClient.getPrice(car.getId());
            car.setPrice(price);
            Location mapSuppliedLocation = mapsClient.getAddress(car.getLocation(), car.getId());
            car.setLocation(mapSuppliedLocation);
        }

        return carList;
    }
    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {

        Optional<Car> optionalCar = repository.findById(id);
        Car car = optionalCar.orElseThrow(CarNotFoundException::new);
        // make a request to the pricing microservice
        String price = this.priceClient.getPrice(id);
        car.setPrice(price);
        // make a request to the maps app
        Location mapSuppliedLocation = mapsClient.getAddress(car.getLocation(), id);
        car.setLocation(mapSuppliedLocation);
        /**
        EXAMPLE: without a client, we might use a RestTemplate:
        RestTemplate template = new RestTemplate();
        String priceUrl = "http://localhost:8762/prices/{id}";
        String priceResponse = template.getForObject(priceUrl, String.class, id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(priceResponse);
            String price = jsonNode.get("price").asText();
            car.setPrice(price);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /**
         * Note: The car class file uses @transient, meaning you will need to call
         * the pricing service each time to get the price.
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         */
        return car;
    }
    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        carToBeUpdated.setCondition(car.getCondition());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }
        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        Optional<Car> optionalCar = repository.findById(id);
        Car car = optionalCar.orElseThrow(CarNotFoundException::new);
        repository.delete(car);
        this.priceClient.deletePrice(id);
    }
}
