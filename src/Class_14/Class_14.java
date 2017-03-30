package Class_14;

import java.util.Random;

/**
 * Created by lbtrier on 01/02/17.
 */
public class Class_14 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting");

        Thread t1 = new Thread(() -> {
            Random random = new Random();

            for (int i = 0; i < 1000000; i++) {

//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Interrupted");
//                    break;
//                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                    break;
                }


                Math.sin(random.nextDouble());
            }
        });
        t1.start();

        Thread.sleep(500);

        //doesn't actually stop the thread, it just sets a flag that tells the Thread that's been interrupted
        t1.interrupt();

        t1.join();

        System.out.println("Finished");
    }
}
