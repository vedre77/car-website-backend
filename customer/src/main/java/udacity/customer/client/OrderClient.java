package udacity.customer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import udacity.customer.model.Item;
import java.util.Map;

@Component
public class OrderClient {

    private static final Logger log = LoggerFactory.getLogger(OrderClient.class);
    final private WebClient client;

    public OrderClient() {
        this.client = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    public Item getCar(Long id) {
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl("http://localhost:8080")
                    .build();

            ResponseEntity<Map<String, Object>> response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/cars/{id}")
                            .build(id)
                    )
                    .exchange()
                    .flatMap(res -> res.toEntity(new ParameterizedTypeReference<Map<String, Object>>() {}))
                    .block();

            System.out.println(response);

            Map<String, Object> responseBody = response.getBody();
            Map<String, Object> details = (Map<String, Object>) responseBody.get("details");
            String model = (String) details.get("model");
            String price = (String) responseBody.get("price");

            Item newItem = new Item();
            newItem.setModelName(model);
            newItem.setModelPrice(price);

            return newItem;

        } catch (Exception e) {
            log.warn("Car service is down");
        }
        return null;
    }
}
