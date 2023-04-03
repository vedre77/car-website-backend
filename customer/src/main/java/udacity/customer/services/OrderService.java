package udacity.customer.services;

import org.springframework.stereotype.Service;
import udacity.customer.model.Order;
import udacity.customer.repository.OrderRepository;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
