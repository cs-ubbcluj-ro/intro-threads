package ro.ubbcluj.apm.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeNumberCounterDemo {

    public static final int N = 100_000_000;
    public static final int NUMBER_OF_THREADS = 10;
    public static final int RANGE = N / NUMBER_OF_THREADS;

    public static void main(String[] args) {
        usingThreads();
        usingExecutorService();
        usingVirtualThreads();
    }

    public static void usingExecutorService() {
        List<Future<Integer>> futures = new ArrayList<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                int start = i * RANGE;
                int end = start + RANGE;
                Future<Integer> future = executorService.submit(new PrimeCounterCallable(start, end));
                futures.add(future);
            }
        }

        int totalPrimeCount = 0;
        for (Future<Integer> future : futures) {
            try {
                totalPrimeCount += future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            } catch (ExecutionException e) {
                throw new RuntimeException("Exception during task execution", e);
            }
        }

        System.out.println("Total prime numbers up to " + N + ": " + totalPrimeCount);
    }

    public static void usingThreads() {
        PrimeCounterThread[] threads = new PrimeCounterThread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            int start = i * RANGE;
            int end = start + RANGE;
            threads[i] = new PrimeCounterThread(start, end);
            threads[i].start();
        }

        int totalPrimeCount = 0;
        for (PrimeCounterThread thread : threads) {
            try {
                thread.join();
                totalPrimeCount += thread.getPrimeCount();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            }
        }

        System.out.println("Total prime numbers up to " + N + ": " + totalPrimeCount);
    }

    public static void usingVirtualThreads() {
        List<Future<Integer>> futures = new ArrayList<>();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                int start = i * RANGE;
                int end = start + RANGE;
                Future<Integer> future = executorService.submit(new PrimeCounterCallable(start, end));
                futures.add(future);
            }
        }

        int totalPrimeCount = 0;
        for (Future<Integer> future : futures) {
            try {
                totalPrimeCount += future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            } catch (ExecutionException e) {
                throw new RuntimeException("Exception during task execution", e);
            }
        }

        System.out.println("Total prime numbers up to " + N + ": " + totalPrimeCount);
    }
}
