package Class_9;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by lbtrier on 31/01/17.
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {

        int value = 0;

        while (true) {

            synchronized (lock) {

                while(list.size() == LIMIT) {
                    lock.wait();
                }

                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consumes() throws InterruptedException {

        Random random = new Random();

        while(true) {

            synchronized (lock) {

                while (list.size() == 0) {
                    lock.wait();
                }

                System.out.println("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is " + value);

                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }
}