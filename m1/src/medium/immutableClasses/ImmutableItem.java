package medium.immutableClasses;

import java.math.BigDecimal;

public final class ImmutableItem {

    private final String name;
    private final BigDecimal price;

    public ImmutableItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ImmutableItem(ImmutableItem other) {
        this.name = other.name;
        this.price = other.price;
    }

    public ImmutableItem withPrice(BigDecimal price) {
        return new ImmutableItem(this.name, price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ImmutableItem{" + "name='" + name + '\'' + ", price=" + price + '}';
    }

}
