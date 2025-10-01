package com.lss.immutablewrongdemo.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private BigDecimal total;
    private String currency;

    public Balance(Balance other) {
        this.total = other.total;
        this.currency = other.currency;
    }

    public Balance withTotal(BigDecimal total) {
        return new Balance(total, this.currency);
    }

}
