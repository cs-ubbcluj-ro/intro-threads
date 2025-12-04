package ro.ubbcluj.apm.syncronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferMoneyDemo {

    public static final int N = 10_000_000;
    public static final int NUMBER_OF_THREADS = 8;

    public static void main(String[] args) {
        usingLock();
        usingAtomic();
        usingSynchronized();
    }

    public static void usingLock() {
        ReentrantLockBankAccount account = new ReentrantLockBankAccount("A", 0);

        try (ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            for (int i = 0; i < N; i++) {
                executorService.submit(() -> account.deposit(1));
            }
        }

        System.out.println("Final balance: " + account.getBalance());
    }

    public static void usingAtomic() {
        AtomicBankAccount account = new AtomicBankAccount("A", 0);

        try (ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            for (int i = 0; i < N; i++) {
                executorService.submit(() -> account.deposit(1));
            }
        }

        System.out.println("Final balance: " + account.getBalance());
    }

    public static void usingSynchronized() {
        SynchronizedBankAccount account = new SynchronizedBankAccount("A", 0);

        try (ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            for (int i = 0; i < N; i++) {
                executorService.submit(() -> account.deposit(1));
            }
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}
