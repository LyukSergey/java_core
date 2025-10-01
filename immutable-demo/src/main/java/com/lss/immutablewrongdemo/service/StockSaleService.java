package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.config.Properties;
import com.lss.immutablewrongdemo.entity.Account;
import com.lss.immutablewrongdemo.entity.Balance;
import com.lss.immutablewrongdemo.entity.ImmutableAccount;
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

    private final AccountRepository accountRepository;
    private final Properties properties;

    @Async
    @Scheduled(fixedDelay = 1000, initialDelay = 1000)// Every second
    public void stockSale() {
        log.info("[TRANSACTION_SERVICE] [Thread: {}] Start stock sale", Thread.currentThread().getName());
        final List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            // Imitate some processing time, call to third party service etc.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            final Balance oldBalance = account.getBalance();
            final BigDecimal newTotal = oldBalance.getTotal().add(BigDecimal.valueOf(1));// Simulate some processing time
            final Balance balance = account.getBalance().withTotal(newTotal);

            if (properties.isMutable()) {
                log.info("[TRANSACTION_SERVICE] [Thread: {}] Mutable", Thread.currentThread().getName());
            } else {
                final ImmutableAccount immutableAccount = new ImmutableAccount(account.getId(), account.getName(), balance,
                        account.getTransactionHistory());
                accountRepository.save(immutableAccount);
                log.info("[TRANSACTION_SERVICE] [Thread: {}] Immutable", Thread.currentThread().getName());
            }

        }
        log.info("[TRANSACTION_SERVICE] [Thread: {}] Finish stock sale. Accounts after updating: {}", Thread.currentThread().getName(),
                accounts);
    }
}
