package com.lss.immutablewrongdemo.entity;

import java.util.List;

public interface Account {

    String getId();

    String getName();

    Balance getBalance();

    List<Transaction> getTransactionHistory();

}
