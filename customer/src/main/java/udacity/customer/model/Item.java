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
    private BigDecimal modelPrice;

    public Item() {
    }
    public Integer getItemId() {
        return itemId;
    }

    public String getModelName() {
        return modelName;
    }

    public BigDecimal getModelPrice() {
        return modelPrice;
    }

    public void setId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelPrice(BigDecimal modelPrice) {
        this.modelPrice = modelPrice;
    }
}
