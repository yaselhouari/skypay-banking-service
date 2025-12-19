package ma.skypay.bankingservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    // Helper for TDD tests
    public int getBalance() {
        return balance;
    }

    // Production deposit (uses current date)
    @Override
    public void deposit(int amount) {
        deposit(amount, LocalDate.now());
    }

    // Deposit with specific date (for testing)
    public void deposit(int amount, LocalDate date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    // Production withdraw (uses current date)
    @Override
    public void widthdraw(int amount) {
        widthdraw(amount, LocalDate.now());
    }

    // Withdraw with specific date (for testing)
    public void widthdraw(int amount, LocalDate date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    // Returns the statement as a string (testable)
    public String printStatementOutcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date || Amount || Balance\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            String formattedDate = t.getDate().format(formatter);
            sb.append(formattedDate)
                    .append(" || ")
                    .append(t.getAmount())
                    .append(" || ")
                    .append(t.getBalanceAfter());

            // Append consistent newline (\n) except after the last transaction
            if (i != 0) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }


    // Prints the statement to console
    @Override
    public void printStatement() {
        System.out.println(printStatementOutcome());
    }
}
