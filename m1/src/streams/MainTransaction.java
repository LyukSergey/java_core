package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainTransaction {

    public static void main(String[] args) {

        Transaction transaction1 = new Transaction("1", new java.math.BigDecimal("100.00"), "USD", "New York");
        Transaction transaction4 = new Transaction("4", new java.math.BigDecimal("150.00"), "USD", "Los Angeles");
        Transaction transaction7 = new Transaction("7", new java.math.BigDecimal("50.00"), "USD", "Chicago");
        Transaction transaction8 = new Transaction("8", new java.math.BigDecimal("300.00"), "USD", "Houston");
        Transaction transaction2 = new Transaction("2", new java.math.BigDecimal("200.00"), "EUR", "Berlin");
        Transaction transaction5 = new Transaction("5", new java.math.BigDecimal("250.00"), "EUR", "Munich");
        Transaction transaction3 = new Transaction("3", new java.math.BigDecimal("300.00"), "UAH", "Kyiv");
        Transaction transaction6 = new Transaction("6", new java.math.BigDecimal("350.00"), "UAH", "Lviv");
        System.out.println("Max transaction by currency:");
        final List<Transaction> transactions = List.of(transaction1, transaction2, transaction3, transaction4,
                transaction5, transaction6, transaction7, transaction8);
        final Map<String, Optional<Transaction>> grouppedByCurrency = groupTransactions(transactions);
        printTransactions(grouppedByCurrency);
        System.out.println("Top 3 by currency:");
        final Map<String, List<Transaction>> top3 = getTopThreeTransactions(transactions);
        printTransactions1(top3);
    }

    private static Map<String, List<Transaction>> getTopThreeTransactions(List<Transaction> transactions) {
        final Collector<Transaction, ?, List<Transaction>> collector = Collectors.toList();
        return transactions.stream().collect(Collectors.groupingBy(
                Transaction::getCurrency,
                Collectors.collectingAndThen(
                        // 1. Спочатку збираємо всі транзакції для даної валюти у список
                        collector,
                        // 2. Потім обробляємо цей список:
                        list -> {
                            final List<Transaction> collect = list.stream()
                                    // Сортуємо за спаданням суми
                                    .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                                    // Беремо перші 3 елементи
                                    .limit(3)
                                    // І збираємо їх у новий список
                                    .collect(Collectors.toList());
                            return collect;
                        }
                )
        ));
    }

    private static Map<String, Optional<Transaction>> groupTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency,
                        Collectors.maxBy((t1, t2) -> t1.getAmount().compareTo(t2.getAmount()))));
    }

    private static void printTransactions(Map<String, Optional<Transaction>> result) {
        result.forEach((currency, transaction) -> {
            System.out.println("Currency: " + currency);
            transaction.ifPresent(t -> System.out.println("Transaction: " + t));
        });

    }

    private static void printTransactions1(Map<String, List<Transaction>> result) {
        result.forEach((currency, transaction) -> {
            System.out.println("Currency: " + currency);
            System.out.println("Transaction: " + transaction);
        });

    }

}
