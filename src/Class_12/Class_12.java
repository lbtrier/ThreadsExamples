package Class_12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by lbtrier on 01/02/17.
 */
public class Class_12 {

    public static void main(String[] args) throws InterruptedException {

        Connection.getInstance().connect();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> Connection.getInstance().connect());
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        Semaphore semaphore = new Semaphore(1);

        semaphore.release();    //Increment number of permits
        semaphore.acquire();    //Decrement the number of permits

        System.out.println("Available permits: " + semaphore.availablePermits());
    }
}
