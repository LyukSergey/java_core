package com.lss.immutablewrongdemo.entity;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MutableAccount implements Account {

    private String id;
    private String name;
    private Balance balance;
    private List<Transaction> transactionHistory;

    @Override
    public String toString() {
        return "MutableAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
