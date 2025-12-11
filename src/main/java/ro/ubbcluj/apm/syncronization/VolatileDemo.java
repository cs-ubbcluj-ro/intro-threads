package ro.ubbcluj.apm.syncronization;

import java.time.Duration;

public class VolatileDemo {
    public static final int N = 10;
    public static volatile int counter = 0;

    public static void main(String[] args) {

        Thread workerThread = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                counter++;
                System.out.println("Increase counter=" + counter);
                try {
                    Thread.sleep(Duration.ofMillis(100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted", e);
                }
            }
        });

        Thread readerThread = new Thread(() -> {
            int local = counter;
            while (local < N) {
                if (local != counter) {
                    System.out.println("Reader thread, counter=" + counter);
                    local = counter;
                }
            }
        });

        workerThread.start();
        readerThread.start();
    }
}
