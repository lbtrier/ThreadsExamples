package Class_3;

/**
 * Created by lbtrier on 30/01/17.
 */
public class Class_3 {

    private int count = 0;

    public static void main(String[] args) {
        Class_3 class_3 = new Class_3();
        class_3.doWork();
    }

    public synchronized void increment() {
        count++;
    }

    public void doWork() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();  //wait for thread to finish
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }
}
