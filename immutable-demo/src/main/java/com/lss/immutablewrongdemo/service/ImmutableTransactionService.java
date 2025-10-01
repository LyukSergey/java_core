package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.entity.ImmutableAccount;
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
public class ImmutableTransactionService {

    private final AccountRepository accountRepository;

    @Async
    public CompletableFuture<Void> processSale(String accountId, BigDecimal amount) {
        accountRepository.findById(accountId)
                .map(ImmutableAccount.class::cast)
                .ifPresent(account -> {
                    ImmutableAccount updatedAccount = account.withBalance(account.getBalance().getTotal().subtract(amount));
                    accountRepository.save(updatedAccount);
                });
        return CompletableFuture.completedFuture(null);
    }

}
