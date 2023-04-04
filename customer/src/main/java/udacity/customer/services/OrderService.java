package udacity.customer.services;

import org.springframework.stereotype.Service;
import udacity.customer.client.OrderClient;
import udacity.customer.model.Item;
import udacity.customer.model.Order;
import udacity.customer.repository.OrderRepository;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderClient orderClient;

    public OrderService(OrderRepository orderRepository, OrderClient orderClient) {
        this.orderRepository = orderRepository;
        this.orderClient = orderClient;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Item> orderCar() {

        return orderClient.getCarsAsItems();
    }
}
