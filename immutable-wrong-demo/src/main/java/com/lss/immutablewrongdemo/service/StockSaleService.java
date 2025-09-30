package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.entity.MutableAccount;
import com.lss.immutablewrongdemo.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockSaleService {

    //Mutable - because it changes the state of the account
    private final AccountRepository accountRepository;

    @Async
    @Scheduled(fixedDelay = 1000, initialDelay = 1000)// Every second
    public void stockSale() {
        log.info("[TRANSACTION_SERVICE] [Thread: {}] Start stock sale", Thread.currentThread().getName());
        final List<MutableAccount> accounts = accountRepository.findAll();
        for (MutableAccount account : accounts) {
            // Imitate some processing time, call to third party service etc.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            final BigDecimal newBalance = account.getBalance().add(BigDecimal.valueOf(1));// Simulate some processing time
            account.setBalance(newBalance);
        }
        log.info("[TRANSACTION_SERVICE] [Thread: {}] Finish stock sale. Accounts after updating: {}", Thread.currentThread().getName(), accounts);
    }
}
