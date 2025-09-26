package producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {

    public static void main(String[] args) {
        // Створюємо спільну чергу з обмеженим розміром (наприклад, 5)
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);

        // Створюємо та запускаємо потік-виробник
        Thread producerThread = new Thread(new Producer(sharedQueue));
        producerThread.start();

        // Створюємо та запускаємо потік-споживач
        Thread consumerThread = new Thread(new Consumer(sharedQueue));
        consumerThread.start();
    }

}
