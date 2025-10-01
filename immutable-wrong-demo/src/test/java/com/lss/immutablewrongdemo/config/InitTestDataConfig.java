package com.lss.immutablewrongdemo.config;

import com.lss.immutablewrongdemo.entity.Account;
import com.lss.immutablewrongdemo.entity.Balance;
import com.lss.immutablewrongdemo.entity.ImmutableAccount;
import com.lss.immutablewrongdemo.entity.MutableAccount;
import com.lss.immutablewrongdemo.entity.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class InitTestDataConfig {

    @Bean
    public Map<String, Account> database(Properties properties) {
        if (!properties.isMutable()) {
            final MutableAccount account1 = new MutableAccount("A1", "Apple shares", new Balance(BigDecimal.valueOf(100), "USD"),
                    List.of(new Transaction(LocalDateTime.of(2023, 1, 1, 10, 0), new BigDecimal(50), "Initial deposit")));
            final MutableAccount account2 = new MutableAccount("A2", "Google shares", new Balance(BigDecimal.valueOf(150), "USD"),
                    List.of(new Transaction(LocalDateTime.of(2023, 1, 2, 11, 0), new BigDecimal(60), "Initial deposit")));
            final Map<String, Account> accountDatabase = new ConcurrentHashMap<>();
            accountDatabase.put(account1.getId(), account1);
            accountDatabase.put(account2.getId(), account2);
            return accountDatabase;
        }
        final ImmutableAccount account1 = new ImmutableAccount("A1", "Apple shares", new Balance(BigDecimal.valueOf(100), "USD"),
                List.of(new Transaction(LocalDateTime.of(2023, 1, 1, 10, 0), new BigDecimal(40), "Initial deposit")));
        final ImmutableAccount account2 = new ImmutableAccount("A2", "Google shares", new Balance(BigDecimal.valueOf(150), "USD"),
                List.of(new Transaction(LocalDateTime.of(2023, 1, 2, 11, 0), new BigDecimal(30), "Initial deposit")));

        final Map<String, Account> accountDatabase = new ConcurrentHashMap<>();
        accountDatabase.put(account1.getId(), account1);
        accountDatabase.put(account2.getId(), account2);
        return accountDatabase;
    }

}
