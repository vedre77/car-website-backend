package udacity.customer.services;

import org.springframework.stereotype.Service;
import udacity.customer.client.OrderClient;
import udacity.customer.model.Item;
import udacity.customer.model.Order;
import udacity.customer.model.User;
import udacity.customer.repository.ItemRepository;
import udacity.customer.repository.OrderRepository;
import udacity.customer.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderClient orderClient;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository,
                        ItemRepository itemRepository, OrderClient orderClient) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderClient = orderClient;
        this.itemRepository = itemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order findUserOrder(String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findByUser(user);
    }

    public List<Item> listCars() {
        return orderClient.getCarsAsItems();
    }
    /**
     * checks for existing user order, then checks for presence of the
     * item by modelName, adds or increases quantity accordingly,
     * or creates a new order
     * **/
    public void createOrder(String username, Integer itemId) {
        // find user
        User user = userRepository.findByUsername(username);
        // find order
        Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findByUser(user));
        Order order;
        // if order exists
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
            // check if order has item gotten by the forwarded ID (check by modelName);
            // increase the quantity accordingly;
            Item chosenItem = itemRepository.getOne(itemId);
            String chosenItemModel = chosenItem.getModelName();
            // check if list of items in the order has it, if yes, increase quantity
            List<Item> orderItemList = order.getItems();
            boolean matchIsFound = false;
            for (Item item: orderItemList) {
                if (Objects.equals(item.getModelName(), chosenItemModel)) {
                    Integer currentQuantity = item.getQuantity();
                    item.setQuantity(currentQuantity += 1);
                    matchIsFound = true;
                }
            }
            if (!matchIsFound) {
                orderItemList.add(chosenItem);
            }
            order.setItems(orderItemList);
            orderRepository.save(order);
            // System.out.println("Check list is updated: " + orderRepository.findAll());

        } else {
            order = new Order();
            order.setUser(user);
            List<Item> itemList = new ArrayList<>();
            Item orderedItem = itemRepository.getOne(itemId);
            itemList.add(orderedItem);
            order.setItems(itemList);
        }
        orderRepository.save(order);
    }

    public String calculateTotal(String username) {
        User user = userRepository.findByUsername(username);
        Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findByUser(user));
        List<Item> itemList = optionalOrder.get().getItems();
        Double total = (double) 0;
        for (Item item: itemList) {
            String str = item.getModelPrice();
            double priceAsDouble = Double.parseDouble(str.replaceAll("[^\\d.]+", ""));
            total += priceAsDouble * item.getQuantity();
        }
        return "" + total;
    }
}
