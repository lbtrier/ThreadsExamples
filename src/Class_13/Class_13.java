package Class_13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by lbtrier on 01/02/17.
 */
public class Class_13 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Random random = new Random();
                int duration = random.nextInt(4000);

                if(duration > 2000) {
                  throw new IOException("Sleeping for too long");
                }

                System.out.println("Starting");

                Thread.sleep(duration);

                System.out.println("Finished");

                return duration;
            }
        });

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Integer duration = (Integer) future.get();
            System.out.println("Result is: " + duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
