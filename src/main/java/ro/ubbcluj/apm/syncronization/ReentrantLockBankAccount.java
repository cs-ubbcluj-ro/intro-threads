package ro.ubbcluj.apm.syncronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBankAccount {
    private final Lock lock;
    private final String accountNumber;
    private int balance;

    public ReentrantLockBankAccount(String accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            if (amount > 0) {
                balance += amount;
            }
        } finally {
            lock.unlock();
        }
    }
}
