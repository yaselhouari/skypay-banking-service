package ma.skypay.bankingservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void deposit_validAmount_shouldIncreaseBalance() {
        // Given
        int depositAmount = 1000;

        // When
        account.deposit(depositAmount);

        // Then
        assertEquals(1000, account.getBalance());
    }

    @Test
    void deposit_invalidAmount_shouldThrowException() {
        // Given
        int invalidAmount = -500;

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(invalidAmount)
        );

        assertEquals("Deposit must be positive", exception.getMessage());
    }

    @Test
    void withdraw_validAmount_shouldDecreaseBalance() {
        // Given
        account.deposit(2000);  // initial balance
        int withdrawAmount = 500;

        // When
        account.widthdraw(withdrawAmount);

        // Then
        assertEquals(1500, account.getBalance());
    }

    @Test
    void withdraw_moreThanBalance_shouldThrowException() {
        // Given
        account.deposit(1000);
        int withdrawAmount = 1500;

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.widthdraw(withdrawAmount)
        );

        assertEquals("Insufficient balance", exception.getMessage());
    }

    @Test
    void printStatement_afterTransactions_shouldShowCorrectOrderAndBalance() {
        // Given
        account.deposit(1000, LocalDate.of(2012, 1, 10));
        account.deposit(2000, LocalDate.of(2012, 1, 13));
        account.widthdraw(500, LocalDate.of(2012, 1, 14));

        // When
        String statement = account.printStatementOutcome(); // returns statement as string

        // Then
        String expected =
                "Date || Amount || Balance\n" +
                        "14/01/2012 || -500 || 2500\n" +
                        "13/01/2012 || 2000 || 3000\n" +
                        "10/01/2012 || 1000 || 1000";

        assertEquals(
                expected.replace("\r\n", "\n"),
                statement.replace("\r\n", "\n")
        );
    }

    @Test
    void withdraw_invalidAmount_shouldThrowException() {
        // Given
        int invalidAmount = -500;

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.widthdraw(invalidAmount)
        );

        assertEquals("Withdrawal must be positive", exception.getMessage());
    }

}
