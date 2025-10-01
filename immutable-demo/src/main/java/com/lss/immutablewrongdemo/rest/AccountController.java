package com.lss.immutablewrongdemo.rest;

import com.lss.immutablewrongdemo.dto.PortfolioDto;
import com.lss.immutablewrongdemo.dto.SellRequest;
import com.lss.immutablewrongdemo.service.ImmutableTransactionService;
import com.lss.immutablewrongdemo.service.MutableTransactionService;
import com.lss.immutablewrongdemo.service.PortfolioService;
import com.lss.immutablewrongdemo.service.StockSaleService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final StockSaleService transactionService;
    private final PortfolioService portfolioService;
    private final MutableTransactionService mutableTransactionService;
    private final ImmutableTransactionService immutableTransactionService;

    @GetMapping("/mutable/portfolio")
    public ResponseEntity<PortfolioDto> calculatePortfolio() {
        // Simulate a stock sale transaction that modifies the account balance while we are calculating the total
        BigDecimal calculatedTotal = portfolioService.calculatePortfolioTotal();
        return ResponseEntity.ok(new PortfolioDto(calculatedTotal));
    }


    @PostMapping("/mutable/sell")
    public ResponseEntity<Void> triggerMutableSale(@RequestBody SellRequest request) {
        mutableTransactionService.processSale(request.accountId(), request.amount());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/immutable/portfolio")
    public ResponseEntity<PortfolioDto> calculatePortfolioImmutable() {
        // Simulate a stock sale transaction that modifies the account balance while we are calculating the total
        BigDecimal calculatedTotal = portfolioService.calculatePortfolioTotal();
        return ResponseEntity.ok(new PortfolioDto(calculatedTotal));
    }

    @PostMapping("/immutable/sell")
    public ResponseEntity<Void> triggerImmutableSale(@RequestBody SellRequest request) {
        immutableTransactionService.processSale(request.accountId(), request.amount());
        return ResponseEntity.noContent().build();
    }

}
