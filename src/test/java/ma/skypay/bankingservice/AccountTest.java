package ma.skypay.bankingservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
