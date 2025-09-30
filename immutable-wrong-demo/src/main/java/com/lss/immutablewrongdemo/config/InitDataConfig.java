package com.lss.immutablewrongdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lss.immutablewrongdemo.entity.MutableAccount;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class InitDataConfig {

    @Bean
    @Profile("!test")
    public Map<String, MutableAccount> database() {
        final MutableAccount account1 = new MutableAccount("A1", "Apple shares", new BigDecimal("10000"));
        final MutableAccount account2 = new MutableAccount("A2", "Google shares", new BigDecimal("15000"));
        final Map<String, MutableAccount> accountDatabase = new ConcurrentHashMap<>();
        accountDatabase.put(account1.getId(), account1);
        accountDatabase.put(account2.getId(), account2);
        return accountDatabase;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
