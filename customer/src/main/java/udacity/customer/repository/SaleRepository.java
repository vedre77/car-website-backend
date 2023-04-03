package udacity.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udacity.customer.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
