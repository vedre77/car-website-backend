package udacity.customer.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import udacity.customer.model.Item;
import udacity.customer.repository.ItemRepository;
import udacity.customer.repository.OrderRepository;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderClient {

    private static final Logger log = LoggerFactory.getLogger(OrderClient.class);
    private final ItemRepository itemRepository;
    private final WebClient client;

    public OrderClient(ItemRepository itemRepository) {
        this.client = WebClient.builder().baseUrl("http://localhost:8080").build();
        this.itemRepository = itemRepository;
    }
    /** gets a list of cars, converts them to items, saves to DB and returns a list of items**/
    public List<Item> getCarsAsItems() {
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl("http://localhost:8080")
                    .build();

            Mono<Object> response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/cars")
                            .build()
                    )
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Object.class).log();

            Object obj = response.block();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(obj, JsonNode.class);
            JsonNode carList = rootNode.path("_embedded").path("carList");
            for (JsonNode carNode : carList) {
                String model = carNode.path("details").path("model").asText();
                JsonNode priceNode = carNode.path("price");
                String price = priceNode.isNull() ? "USD 10000.10" : String.valueOf(priceNode);

                Item newItem = new Item();
                newItem.setModelName(model);
                newItem.setModelPrice(price);
                itemRepository.save(newItem);
            }
            return itemRepository.findAll();

        } catch (Exception e) {
            log.warn("Car service is down");
        }
        return null;
    }
}
