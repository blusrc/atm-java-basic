import static org.junit.jupiter.api.Assertions.*;

import org.example.Account;
import org.example.ATM;
import org.example.PinValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ATMWithdrawTest {

    private ATM atm;

    @BeforeEach
    void setUp() {
        // Initial balance is 1000
        Account account = new Account("12345", "1234", 1000);
        PinValidator pinValidator = new PinValidator();
        atm = new ATM(account, pinValidator);
    }

    // Path 1: amount < 0
    @Test
    void testWithdrawNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> atm.withdraw(-50));
        assertEquals("Negative withdrawal not allowed", exception.getMessage());
    }

    // Path 2: amount == 0
    @Test
    void testWithdrawZeroAmount() {
        double balanceBefore = atm.checkBalance();
        double result = atm.withdraw(0);
        assertEquals(balanceBefore, result, "Balance should remain unchanged for zero withdrawal");
    }

    // Path 3: amount > account.getBalance()
    @Test
    void testWithdrawInsufficientFunds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> atm.withdraw(1500));
        assertEquals("Insufficient funds", exception.getMessage());
    }

    // Path 4: amount is not a multiple of 10
    @Test
    void testWithdrawNotMultipleOfTen() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> atm.withdraw(55));
        assertEquals("Withdrawal amount must be a multiple of 10", exception.getMessage());
    }

    // Path 5: Valid withdrawal (multiple of 10, ≤ balance, > 0)
    @Test
    void testValidWithdrawal() {
        double result = atm.withdraw(100);
        assertEquals(900, result, "Balance should be reduced correctly after valid withdrawal");
    }
}
