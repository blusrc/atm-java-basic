import static org.junit.jupiter.api.Assertions.*;

import org.example.PinValidator;
import org.junit.jupiter.api.Test;

class PinValidatorTest {

    private final String correctPin = "1234";
    private final PinValidator pinValidator = new PinValidator();

    // Test Case 1: enteredPin is empty
    @Test
    void testEmptyEnteredPin() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pinValidator.isValid("", correctPin);
        });
        assertEquals("Empty pin", exception.getMessage());
    }

    // Test Case 2: enteredPin is not empty and equals correctPin
    @Test
    void testValidPin() {
        assertTrue(pinValidator.isValid("1234", correctPin));
    }

    // Test Case 3: enteredPin is not empty and does not equal correctPin
    @Test
    void testInvalidPin() {
        assertFalse(pinValidator.isValid("0000", correctPin));
    }
}
