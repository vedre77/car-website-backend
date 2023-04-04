package udacity.customer.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    private String modelName;
    private String modelPrice;
    private Integer quantity = 0;

    public Item() {
    }
    public Integer getItemId() {
        return itemId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelPrice() {
        return modelPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelPrice(String modelPrice) {
        this.modelPrice = modelPrice;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "modelName='" + modelName + '\'' +
                ", modelPrice='" + modelPrice + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
