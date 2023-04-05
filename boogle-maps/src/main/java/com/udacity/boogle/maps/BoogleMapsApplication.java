package com.udacity.boogle.maps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoogleMapsApplication {

	/**
	 * Due to added functionality, the test curl command via the terminal
	 * should include car ID, example:
	 * curl -X GET "http://localhost:9191/maps/?lat=0.0&lon=0.0&carId=1"
	 */

	public static void main(String[] args) {
		SpringApplication.run(BoogleMapsApplication.class, args);
	}

}
