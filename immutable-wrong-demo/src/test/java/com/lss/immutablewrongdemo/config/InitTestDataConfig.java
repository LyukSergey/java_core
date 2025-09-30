package com.lss.immutablewrongdemo.config;

import com.lss.immutablewrongdemo.entity.MutableAccount;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class InitTestDataConfig {

    @Bean
    @Primary
    public Map<String, MutableAccount> database() {
        final MutableAccount account1 = new MutableAccount("A1", "Apple shares", new BigDecimal("100"));
        final MutableAccount account2 = new MutableAccount("A2", "Google shares", new BigDecimal("150"));
        final Map<String, MutableAccount> accountDatabase = new ConcurrentHashMap<>();
        accountDatabase.put(account1.getId(), account1);
        accountDatabase.put(account2.getId(), account2);
        return accountDatabase;
    }

}
