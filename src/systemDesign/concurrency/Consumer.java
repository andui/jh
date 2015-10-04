package systemDesign.concurrency;

import java.util.List;

public class Consumer implements Runnable {
    private List<Integer> taskQueue;

    public Consumer(List<Integer> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        //infinite loop so that consumer keeps consuming elements whenever it finds something in taskQueue..
        while (true) {
            synchronized (taskQueue) {
                try {
                    while (taskQueue.size() == 0) {
                        System.out.println(Thread.currentThread().getName() +
                                ": Queue(size=" + taskQueue.size() + ")is empty, now start waiting...");
                        taskQueue.wait();
                    }
                    //simulating time delays in consuming elements.
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ": consuming " + taskQueue.remove(0));
                    //Once the wait() is over, consumer removes an element in taskQueue and called notifyAll() method.
                    //Because the last-time wait() method was called by producer thread
                    //(that’s why producer is in waiting state), producer gets the notification.
                    taskQueue.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //do something else
            for(int i=0;i<100000;i++){
                //do something
            }
        }
    }
}
