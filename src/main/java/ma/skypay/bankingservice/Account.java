package ma.skypay.bankingservice;

public class Account implements AccountService {

    private int balance = 0;

    // For TDD: helper to check balance in the test
    public int getBalance() {
        return balance;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    @Override
    public void widthdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    @Override
    public void printStatement() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
