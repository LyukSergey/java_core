package com.lss.immutablewrongdemo.dto;

import java.math.BigDecimal;

public record SellRequest(String accountId, BigDecimal amount) {

}
