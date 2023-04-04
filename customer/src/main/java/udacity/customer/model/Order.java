package udacity.customer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    public Order() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                '}';
    }
}
