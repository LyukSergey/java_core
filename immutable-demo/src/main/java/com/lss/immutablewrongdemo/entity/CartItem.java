package com.lss.immutablewrongdemo.entity;

import java.math.BigDecimal;

public record CartItem(
        String productId,
        int quantity,
        BigDecimal unitPrice
) {

}