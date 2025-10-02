package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.entity.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public boolean isItemAvailable(ShoppingCart initialCart) {
        // Simulate inventory check
        return initialCart.items().isEmpty();
    }

}
