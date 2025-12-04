package ro.ubbcluj.apm.threads;

import java.util.concurrent.Callable;

public class PrimeCounterCallable implements Callable<Integer> {
    private final int start;
    private final int end;

    public PrimeCounterCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int primeCount = 0;
        for (int number = start; number < end; number++) {
            if (PrimeUtils.isPrime(number)) {
                primeCount++;
            }
        }
        return primeCount;
    }
}
