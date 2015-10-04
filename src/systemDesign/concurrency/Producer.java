package systemDesign.concurrency;

import java.util.List;

public class Producer implements Runnable {
    private List<Integer> taskQueue;
    private int MAX_CAPACITY;
    //instead of having an int counter,
    //we use a wrapper object, so that it can be passed by reference
    private Counter counter;

    public Producer(List<Integer> taskQueue, int max_capacity, Counter counter) {
        this.taskQueue = taskQueue;
        this.MAX_CAPACITY = max_capacity;
        this.counter = counter;
    }

    @Override
    public void run() {
        //infinite loop so that producer keeps producing elements at regular interval.
        while (true) {
            try {
                synchronized (taskQueue) {

                    //here it has to be while instead of if since if after it is waken up,
                    //someone else takes the lock and change the taskQueue size
                    //if check will still go through and start producing
                    while (taskQueue.size() == MAX_CAPACITY) {
                        System.out.println(Thread.currentThread().getName() +
                                ": Queue(size=" + taskQueue.size() + ")is full, now start waiting...");
                        taskQueue.wait();
                    }
                    //simulating time delays in consuming elements.
                    Thread.sleep(100);
                    taskQueue.add(counter.val);
                    System.out.println(Thread.currentThread().getName() + ": Produced:" + counter.val);
                    counter.increase();
                    //Calling notifyAll() because the last-time wait() method was called by consumer thread
                    //(that’s why producer is out of waiting state), consumer gets the notification.
                    taskQueue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<100000;i++){
                //do something
            }
        }
    }
}
