package producerConsumer;

import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private int value = 0;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Виробник створив: " + value);
                queue.put(value++); // Кладемо елемент у чергу
                Thread.sleep(1000); // Імітуємо час на створення нового елемента
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
