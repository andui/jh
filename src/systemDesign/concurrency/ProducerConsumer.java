package systemDesign.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) {
        Counter c = new Counter();
        List<Integer> taskQueue = new ArrayList<>();
        Thread tProducer1 = new Thread(new Producer(taskQueue, 5, c), "Producer1");
        Thread tProducer2 = new Thread(new Producer(taskQueue, 5, c), "Producer2");
        Thread tConsumer1 = new Thread(new Consumer(taskQueue), "Consumer1");
        Thread tConsumer2 = new Thread(new Consumer(taskQueue), "Consumer2");
        tProducer1.start();
        tProducer2.start();
        tConsumer1.start();
        tConsumer2.start();
    }
}
