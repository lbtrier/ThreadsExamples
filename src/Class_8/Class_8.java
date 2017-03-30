package Class_8;

import java.util.Scanner;

/**
 * Created by lbtrier on 31/01/17.
 */
public class Class_8 {

    public static void main(String[] args) {

        final Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Processor {

    public void produce() throws InterruptedException {

        synchronized (this) {
            System.out.println("Producer Thread running");
            wait();
            System.out.println("Resumed");
        }
    }

    public void consume() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
        }
    }
}
