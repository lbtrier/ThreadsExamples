package Class_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lbtrier on 30/01/17.
 */
public class Worker {

    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public synchronized void stageOne(){

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list.add(random.nextInt(100));
        }
    }

    public synchronized void stageTwo() {

        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(this::process);
        t1.start();
        Thread t2 = new Thread(this::process);
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List 1: " + list.size());
        System.out.println("List 2: " + list2.size());

    }
}
