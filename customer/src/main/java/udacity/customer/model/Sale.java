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

    private SaleStatus saleStatus;

    public Sale() {
    }

    public Integer getSaleId() {
        return saleId;
    }

    public Order getOrder() {
        return order;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setId(Integer id) {
        this.saleId = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "order=" + order +
                ", saleStatus=" + saleStatus +
                '}';
    }
}
