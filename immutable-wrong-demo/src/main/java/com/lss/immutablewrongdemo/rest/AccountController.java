package com.lss.immutablewrongdemo.rest;

import com.lss.immutablewrongdemo.dto.PortfolioDto;
import com.lss.immutablewrongdemo.service.PortfolioService;
import com.lss.immutablewrongdemo.service.StockSaleService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final StockSaleService transactionService;
    private final PortfolioService portfolioService;

    @GetMapping("/portfolio")
    public ResponseEntity<PortfolioDto> calculatePortfolio() {
        // Simulate a stock sale transaction that modifies the account balance while we are calculating the total
        BigDecimal calculatedTotal = portfolioService.calculatePortfolioTotal();
        return ResponseEntity.ok(new PortfolioDto(calculatedTotal));
    }

}
