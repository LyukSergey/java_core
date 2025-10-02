package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.entity.ShoppingCart;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryService inventoryService;
    private final DiscountService discountService;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public ShoppingCart processAndFinalizeCartAsync(ShoppingCart initialCart) {
        System.out.println("Main thread: " + Thread.currentThread().getName());
        // Async call to product inventory microservice
        final CompletableFuture<Boolean> inventoryCheck = productInventoryCheck(initialCart);
        //Async call to discount microservice
        final CompletableFuture<BigDecimal> discountCalculation = calculateDiscount(initialCart);
        //Combine results of both async calls
        return combineTwoTasks(initialCart, inventoryCheck, discountCalculation).exceptionally(throwable -> {
            throw new IllegalStateException(throwable.getMessage());
        }).join();
    }

    private CompletableFuture<ShoppingCart> combineTwoTasks(ShoppingCart initialCart,
            CompletableFuture<Boolean> inventoryCheck, CompletableFuture<BigDecimal> discountCalculation) {
        return inventoryCheck.thenCombine(discountCalculation, (available, discountAmount) -> {
            System.out.println("Thread 3. Combine results of calls " + Thread.currentThread().getName());
            if (!available) {
                throw new IllegalStateException("Items are out of stock or cart is empty!");
            }
            BigDecimal finalTotal = initialCart.totalBeforeDiscount().subtract(discountAmount);
            return initialCart.withFinalTotal(finalTotal);
        });
    }

    private CompletableFuture<BigDecimal> calculateDiscount(ShoppingCart initialCart) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 2 (Discount Calculation): " + Thread.currentThread().getName());
            try {
                Thread.sleep(100); // Simulate network delay
                return discountService.calculateDiscount(initialCart);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return BigDecimal.ZERO;
            }
        }, executor);
    }

    private CompletableFuture<Boolean> productInventoryCheck(ShoppingCart initialCart) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 1 (Inventory Check): " + Thread.currentThread().getName());
            try {
                Thread.sleep(150); // Simulate network delay
                return !inventoryService.isItemAvailable(initialCart);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }, executor);
    }
}
