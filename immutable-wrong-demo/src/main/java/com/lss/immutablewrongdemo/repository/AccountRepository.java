package com.lss.immutablewrongdemo.repository;

import com.lss.immutablewrongdemo.entity.MutableAccount;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final Map<String, MutableAccount> database;

    public void save(MutableAccount account) {
        database.put(account.getId(), account);
    }

    public Optional<MutableAccount> findById(String id) {
        MutableAccount account = database.get(id);
        return Optional.ofNullable(account != null ? new MutableAccount(account.getId(), account.getName(), account.getBalance()) : null);
    }

    public List<MutableAccount> findAll() {
        return database.values().stream().toList();
    }

    public void clear() {
        database.clear();
    }

}
