package deadlockExample;

public class DeadlockExample {

    // Створюємо два ресурси, які будуть "захоплюватися" потоками
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Створюємо та запускаємо перший потік
        Thread thread1 = new Thread(() -> {
            // Потік 1 захоплює resource1, а потім намагається захопити resource2
            synchronized (resource1) {
                System.out.println("Потік 1: захопив ресурс 1");

                try {
                    // Робимо невелику паузу, щоб інший потік встиг захопити свій ресурс
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Потік 1: намагається захопити ресурс 2...");
                synchronized (resource2) {
                    System.out.println("Потік 1: захопив ресурс 1 та ресурс 2");
                }
            }
        });

        // Створюємо та запускаємо другий потік
        Thread thread2 = new Thread(() -> {
            // Потік 2 захоплює resource2, а потім намагається захопити resource1
            synchronized (resource2) {
                System.out.println("Потік 2: захопив ресурс 2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Потік 2: намагається захопити ресурс 1...");
                synchronized (resource1) {
                    System.out.println("Потік 2: захопив ресурс 2 та ресурс 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
