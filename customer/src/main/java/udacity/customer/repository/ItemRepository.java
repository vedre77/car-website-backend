package udacity.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udacity.customer.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByModelName(String modelName);
}
