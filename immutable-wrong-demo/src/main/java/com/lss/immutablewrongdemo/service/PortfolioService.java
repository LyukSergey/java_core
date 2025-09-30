package com.lss.immutablewrongdemo.service;

import com.lss.immutablewrongdemo.entity.MutableAccount;
import com.lss.immutablewrongdemo.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final AccountRepository accountRepository;

    public BigDecimal calculatePortfolioTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (MutableAccount account : accountRepository.findAll()) {
            log.info("[PORTFOLIO_SERVICE] [Thread: {}] Get mutable account data {}.", Thread.currentThread().getName(), account.getId());

            // Imitate some processing time
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Mutate the account balance
            // This is the problematic part
            total = total.add(account.getBalance());
            log.info("[PORTFOLIO_SERVICE] [Thread: {}] Intermediate summary: {}", Thread.currentThread().getName(), total);
        }

        log.info("[PORTFOLIO_SERVICE] [Thread: {}] Final calculation completed. Amount: {}", Thread.currentThread().getName(), total);
        return total;
    }

    public void resetData() {
        accountRepository.clear();
        accountRepository.save(new MutableAccount("A1", "Apple shares", new BigDecimal("10000")));
        accountRepository.save(new MutableAccount("A2", "Google shares", new BigDecimal("15000")));
        log.info("Reset data for mutable scenario");
    }

}
