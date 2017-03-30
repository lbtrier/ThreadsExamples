package Class_2;

import java.util.Scanner;

/**
 * Created by lbtrier on 29/01/17.
 */
public class Class_2 {

    public static void main(String[] args) {

        Processor processor = new Processor();
        processor.start();

        System.out.println("Press Return to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();

    }
}

class Processor extends Thread {

    private volatile boolean running = true;

    @Override public void run() {

        while(running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
