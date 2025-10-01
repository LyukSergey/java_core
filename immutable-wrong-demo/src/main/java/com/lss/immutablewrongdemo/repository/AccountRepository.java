package com.lss.immutablewrongdemo.repository;

import com.lss.immutablewrongdemo.entity.Account;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final Map<String, Account> database;

    public void save(Account account) {
        database.put(account.getId(), account);
    }

    public List<Account> findAll() {
        return database.values().stream().toList();
    }

}
