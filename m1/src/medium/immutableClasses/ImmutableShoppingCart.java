package medium.immutableClasses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ImmutableShoppingCart {

    private final List<ImmutableItem> items;

    public ImmutableShoppingCart(ImmutableItem item) {
        this.items = new ArrayList<>();
        this.items.add(new ImmutableItem(item));
    }

    public ImmutableShoppingCart(List<ImmutableItem> items) {
        this.items = new ArrayList<>();
        this.items.addAll(List.copyOf(items.stream()
                .map(ImmutableItem::new)
                .toList()));
    }

    public ImmutableShoppingCart withItem(ImmutableItem item) {
        final List<ImmutableItem> immutableItems = new ArrayList<>(List.copyOf(this.items));
        immutableItems.add(item);
        return new ImmutableShoppingCart(immutableItems);
    }

    public List<ImmutableItem> getItems() {
        return items;
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(ImmutableItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
