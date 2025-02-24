import static org.junit.jupiter.api.Assertions.*;

import org.example.ATM;
import org.example.Account;
import org.example.PinValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

class ATMTest {

    private Account account;
    private ATM atm;

    @BeforeEach
    void setUp() {
        account = new Account("12345", "1234", 1000);
        PinValidator pinValidator = new PinValidator();
        atm = new ATM(account, pinValidator);
    }

    @Test
    @Tag("transaction")
    void testDeposit() {
        assertEquals(1500, atm.deposit(500)); // Deposit 500, balance should be 1500
    }

    @Test
    @Tag("transaction")
    void testWithdraw() {
        assertEquals(500, atm.withdraw(500)); // Withdraw 500, balance should be 500
    }

    @Test
    @Tag("transaction")
    void testCheckBalance() {
        assertEquals(1000, atm.checkBalance()); // Balance should be 1000
    }

    @Test
    @Tag("authentication")
    void testInvalidPin() {
        assertFalse(atm.validatePin("0000")); // Invalid PIN should return false
    }

    @Test
    @Tag("authentication")
    void testValidPin() {
        assertTrue(atm.validatePin("1234")); // Correct PIN should return true
    }

    @Test
    @Tag("transaction")
    void testWithdrawInsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () -> atm.withdraw(1500)); // Withdrawal exceeds balance
    }

    @ParameterizedTest
    @CsvSource({
            "1234, true",
            "0000, false",
            "4321, false"
    })
    @Tag("authentication")
    void testPinValidation(String enteredPin, boolean expected) {
        assertEquals(expected, atm.validatePin(enteredPin)); // Parameterized test for PIN validation
    }

    @RepeatedTest(5)
    @Tag("transaction")
    void testRepeatedWithdrawal() {
        atm.withdraw(100); // Withdraw 100 five times, balance should decrease consistently
        assertEquals(900, atm.checkBalance()); // Ensure balance is correctly updated after repeated withdrawals
    }

    @Test
    @Tag("transaction")
    void testDepositBoundary() {
        assertEquals(1001, atm.deposit(1)); // Test boundary case with small deposit
    }

    @Test
    @Tag("transaction")
    void testMultipleDeposits() {
        atm.deposit(100);
        atm.deposit(200);
        assertEquals(1300, atm.checkBalance()); // Test multiple deposits
    }

    @Test
    @Tag("transaction")
    void testLargeWithdraw() {
        assertEquals(800, atm.withdraw(200)); // Test withdrawal with large amount
    }

    @Test
    @Tag("transaction")
    void testBalanceAfterWithdraw() {
        atm.withdraw(200);
        assertEquals(800, atm.checkBalance()); // Ensure balance is updated correctly after withdrawal
    }

    @Test
    @Tag("transaction")
    void testInvalidWithdrawal() {
        assertThrows(IllegalArgumentException.class, () -> atm.withdraw(1500)); // Invalid withdrawal due to insufficient balance
    }

    @Test
    @Tag("transaction")
    void testWithdrawExactBalance() {
        assertEquals(0, atm.withdraw(1000)); // Test withdrawing the exact balance
    }

    @Test
    @Tag("authentication")
    void testPinLength() {
        assertEquals(4, account.getPin().length()); // Ensure the pin has the correct length
    }

    @Test
    @Tag("authentication")
    void testPinMismatch() {
        assertFalse(atm.validatePin("5678")); // Mismatch in PIN should return false
    }

    @Test
    @Tag("authentication")
    void testEmptyPin() {
        assertThrows(IllegalArgumentException.class, () -> atm.validatePin("")); // Test for empty PIN
    }

    @Test
    @Tag("transaction")
    void testLargeDeposit() {
        assertEquals(1000000, atm.deposit(999000)); // Test with large deposit amount
    }
}