package deadlockExample;

public class NoDeadlockExample {

    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Потік 1: захоплює resource1, потім resource2
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Потік 1: захопив ресурс 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                synchronized (resource2) {
                    System.out.println("Потік 1: захопив ресурс 2");
                }
            }
        }).start();

        // Потік 2: також захоплює resource1, потім resource2
        new Thread(() -> {
            // Дотримуємося ТОГО Ж ПОРЯДКУ
            synchronized (resource1) {
                System.out.println("Потік 2: захопив ресурс 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                synchronized (resource2) {
                    System.out.println("Потік 2: захопив ресурс 2");
                }
            }
        }).start();
    }
}
