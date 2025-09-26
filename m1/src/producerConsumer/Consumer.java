package producerConsumer;

import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer value = queue.take(); // Забираємо елемент з черги
                System.out.println("Споживач обробив: " + value);
                Thread.sleep(2000); // Імітуємо час на обробку елемента
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
