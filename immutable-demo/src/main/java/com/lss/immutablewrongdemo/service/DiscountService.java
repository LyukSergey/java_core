package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.entity.ShoppingCart;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final double discountPercentage = 10;

    public BigDecimal calculateDiscount(ShoppingCart cart) {
        BigDecimal discountRate = cart.totalBeforeDiscount().compareTo(new BigDecimal(100)) > 0
                ? new BigDecimal(discountPercentage)
                : BigDecimal.ZERO;
        return cart.totalBeforeDiscount().multiply(discountRate.divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
