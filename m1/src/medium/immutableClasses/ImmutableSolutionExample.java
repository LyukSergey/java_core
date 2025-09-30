package medium.immutableClasses;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ImmutableSolutionExample {

    public static void main(String[] args) {
        // Створюємо immutable товар і додаємо його в immutable кошик
        ImmutableItem laptop = new ImmutableItem("Super Laptop", new BigDecimal("1500.00"));
        ImmutableShoppingCart cart = new ImmutableShoppingCart(laptop);

        System.out.println("🛒 Початковий стан кошика: " + cart.getItems());
        System.out.println("💰 Початкова сума: " + cart.calculateTotal());
        System.out.println("-------------------------------------------------");
        System.out.println("🔒 Починаємо надійний процес оплати...");

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            executor.submit(() -> {
                System.out.println("  [Checkout] Починаю розрахунок для версії кошика: " + cart.hashCode());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                BigDecimal finalPrice = cart.calculateTotal();
                System.out.println("  [Checkout] 🏁 Фінальна сума до сплати: " + finalPrice + " (коректна!)");
            });

            executor.submit(() -> {
                System.out.println("  [Promotion] Знайдено акційний товар! Створюю новий кошик зі знижкою...");

                ImmutableShoppingCart discountedCart = new ImmutableShoppingCart(
                        cart.getItems().stream()
                                .map(item -> item.getName().equals("Super Laptop") ? item.withPrice(new BigDecimal("999.00")) : item)
                                .collect(Collectors.toList())
                );

                System.out.println("  [Promotion] ✅ Створено новий кошик: " + discountedCart.hashCode());
                System.out.println("  [Promotion] ✅ Сума в новому кошику: " + discountedCart.calculateTotal());
            });

            executor.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println("-------------------------------------------------");
            System.out.println("🛡️ КІНЦЕВИЙ СТАН ОРИГІНАЛЬНОГО КОШИКА: " + cart.getItems());
            System.out.println("✅ Оригінальний об'єкт залишився недоторканим. Проблема Race Condition вирішена!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
