package medium.mutableClasses;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MutableChaosExample {

    public static void main(String[] args) {
        MutableItem laptop = new MutableItem("Super Laptop", new BigDecimal("1500.00"));
        MutableShoppingCart cart = new MutableShoppingCart();
        cart.addItem(laptop);

        System.out.println("🛒 Початковий стан кошика: " + cart.getItems());
        System.out.println("💰 Початкова сума: " + cart.calculateTotal());
        System.out.println("-------------------------------------------------");
        System.out.println("🔥 Починаємо процес оплати і одночасно запускаємо акцію...");

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            executor.submit(() -> {
                System.out.println("  [Checkout] Починаю розрахунок суми...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                BigDecimal finalPrice = cart.calculateTotal();
                System.out.println("  [Checkout] 🏁 Фінальна сума до сплати: " + finalPrice);
            });

            executor.submit(() -> {
                System.out.println("  [Promotion] Знайдено акційний товар! Застосовую знижку...");
                laptop.setPrice(new BigDecimal("999.00"));
                System.out.println("  [Promotion] ✅ Ціну на ноутбук змінено на " + laptop.getPrice());
            });

            executor.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println("-------------------------------------------------");
            System.out.println("КІНЦЕВИЙ СТАН КОШИКА: " + cart.getItems());
            System.out.println("Користувач бачив суму 1500, а система розрахувала 999. Це Race Condition!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
