package Class_11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lbtrier on 31/01/17.
 */
public class Runner {

    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLock(Lock firstLock, Lock secondLock) {

        while(true) {

            //Acquire the locks
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if(gotFirstLock && gotSecondLock) {
                    return;
                }

                if (gotFirstLock) {
                    firstLock.unlock();
                }

                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }


            //Locks not acquired
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void firstThread() throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {

            acquireLock(lock, lock2);

            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock.unlock();
                lock2.unlock();
            }

        }
    }

    public void secondThread() throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {

            acquireLock(lock2, lock);

            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock.unlock();
                lock2.unlock();
            }

        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        System.out.println("Total Balance: " + (account1.getBalance() + account2.getBalance()));
    }
}
