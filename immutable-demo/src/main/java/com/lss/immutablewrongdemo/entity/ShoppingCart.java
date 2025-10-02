package com.lss.immutablewrongdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.List;

public record ShoppingCart(
        String cartId,
        String userId,
        List<CartItem> items,
        BigDecimal totalBeforeDiscount,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal finalTotal
) {

    public ShoppingCart {
        items = List.copyOf(items);
    }

    public ShoppingCart withFinalTotal(BigDecimal newTotal) {
        return new ShoppingCart(
                this.cartId,
                this.userId,
                this.items,
                this.totalBeforeDiscount,
                newTotal
        );
    }

    public static BigDecimal calculateInitialTotal(List<CartItem> items) {
        return items.stream()
                .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
