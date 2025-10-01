package com.lss.immutablewrongdemo.service;


import com.lss.immutablewrongdemo.entity.MutableAccount;
import com.lss.immutablewrongdemo.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MutableTransactionService {

    private final AccountRepository accountRepository;

    @Async
    public CompletableFuture<Void> processSale(String accountId, BigDecimal amount) {
        accountRepository.findById(accountId)
                .map(MutableAccount.class::cast)
                .ifPresent(account -> {
                    BigDecimal newBalance = account.getBalance().getTotal().subtract(amount);
                    account.setBalance(account.getBalance().withTotal(newBalance));
                    log.warn("[MUTABLE] [Thread: {}] 🔴 Баланс рахунку {} змінено на {}",
                            Thread.currentThread().getName(), accountId, newBalance);
                });

        return CompletableFuture.completedFuture(null);
    }
}
