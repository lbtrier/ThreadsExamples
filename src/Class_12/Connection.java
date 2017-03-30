package Class_12;

import java.util.concurrent.Semaphore;

/**
 * Created by lbtrier on 01/02/17.
 */
public class Connection {

    private static Connection instance;
    private int connections = 0;
    private Semaphore semaphore = new Semaphore(10, true);

    private Connection() {

    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }

        return instance;
    }

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current connection " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }

        semaphore.release();
    }
}
