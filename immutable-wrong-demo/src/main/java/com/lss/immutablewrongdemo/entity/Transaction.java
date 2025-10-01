package com.lss.immutablewrongdemo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Transaction(LocalDateTime timestamp, BigDecimal amount, String description) {

    public Transaction(LocalDateTime timestamp, BigDecimal amount, String description) {
        this.timestamp = Objects.requireNonNull(timestamp);
        this.amount = Objects.requireNonNull(amount);
        this.description = Objects.requireNonNull(description);
    }
}
