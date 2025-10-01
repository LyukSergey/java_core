package com.lss.immutablewrongdemo.repository;

import com.lss.immutablewrongdemo.entity.Account;
import com.lss.immutablewrongdemo.entity.ImmutableAccount;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final Map<String, Account> database;

    public void save(Account account) {
        database.put(account.getId(), account);
    }

    public Optional<Account> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<Account> findAll() {
        return database.values().stream().toList();
    }

}
