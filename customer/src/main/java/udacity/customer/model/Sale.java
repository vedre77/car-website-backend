package udacity.customer.model;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private boolean status = false;

    public Sale() {
    }

    public Integer getSaleId() {
        return saleId;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.saleId = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
