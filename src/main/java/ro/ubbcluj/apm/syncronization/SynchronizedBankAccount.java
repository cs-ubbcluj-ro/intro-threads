package ro.ubbcluj.apm.syncronization;

public class SynchronizedBankAccount {
    private final String accountNumber;
    private int balance;

    public SynchronizedBankAccount(String accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        synchronized (this) {
            if (amount > 0) {
                balance += amount;
            }
        }

    }
}
