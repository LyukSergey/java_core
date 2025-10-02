package com.lss.immutablewrongdemo.rest;

import com.lss.immutablewrongdemo.entity.ShoppingCart;
import com.lss.immutablewrongdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/async")
    public ResponseEntity<ShoppingCart> processCartAsync(@RequestBody ShoppingCart initialCart) {
        return ResponseEntity.ok(orderService.processAndFinalizeCartAsync(initialCart));
    }

}
