package udacity.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udacity.customer.model.Order;
import udacity.customer.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByUser(User user);
}
