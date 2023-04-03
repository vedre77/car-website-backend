package udacity.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udacity.customer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    boolean existsByUsername(String username);
    User findByUsername(String username);
}
