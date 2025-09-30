package medium.mutableClasses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MutableShoppingCart {

    private final List<MutableItem> items = new ArrayList<>();

    public void addItem(MutableItem item) {
        items.add(item);
    }

    public List<MutableItem> getItems() {
        return items;
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(MutableItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
