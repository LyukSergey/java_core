package medium.mutableClasses;

import java.math.BigDecimal;

public class MutableItem {

    private String name;
    private BigDecimal price;

    public MutableItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MutableItem{" + "name='" + name + '\'' + ", price=" + price + '}';
    }

}
