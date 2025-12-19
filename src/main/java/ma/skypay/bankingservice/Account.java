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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void printStatement() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
