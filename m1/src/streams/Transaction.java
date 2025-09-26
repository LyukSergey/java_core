package streams;

import java.math.BigDecimal;

public class Transaction {

    String id;
    BigDecimal amount;
    String currency; // "USD", "EUR", "UAH"
    String city;

    public Transaction(String id, BigDecimal amount, String currency, String city) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + this.id + '\'' +
                ", amount=" + this.amount +
                ", currency='" + this.currency + '\'' +
                ", city='" + this.city + '\'' +
                '}';
    }
}
