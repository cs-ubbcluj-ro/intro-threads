package ro.ubbcluj.apm.syncronization;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicBankAccount {
    private final String accountNumber;
    private final AtomicInteger balance;

    public AtomicBankAccount(String accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = new AtomicInteger(initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance.get();
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance.accumulateAndGet(amount, Integer::sum);
        }
    }
}
