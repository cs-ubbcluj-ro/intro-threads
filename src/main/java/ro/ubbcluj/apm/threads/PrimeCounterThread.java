package ro.ubbcluj.apm.threads;

public class PrimeCounterThread extends Thread {
    private final int start;
    private final int end;
    private int primeCount;

    public PrimeCounterThread(int start, int end) {
        this.start = start;
        this.end = end;
        this.primeCount = 0;
    }

    @Override
    public void run() {
        for (int number = start; number < end; number++) {
            if (PrimeUtils.isPrime(number)) {
                primeCount++;
            }
        }
    }

    public int getPrimeCount() {
        return primeCount;
    }
}
