package com.lss.immutablewrongdemo.entity;

import java.util.List;
import lombok.Getter;

@Getter
public class ImmutableAccount implements Account {

    private final String id;
    private final String name;
    private final Balance balance;
    private List<Transaction> transactionHistory;

    public ImmutableAccount(String id, String name, Balance balance, List<Transaction> transactionHistory) {
        this.id = id;
        this.name = name;
        this.balance = new Balance(balance);
        this.transactionHistory = List.copyOf(transactionHistory);
    }

    public Balance getBalance() {
        return new Balance(balance);
    }

    public List<Transaction> getTransactionHistory() {
        return List.copyOf(transactionHistory);
    }

    @Override
    public String toString() {
        return "ImmutableAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", transactionHistory=" + transactionHistory +
                '}';
    }

}
