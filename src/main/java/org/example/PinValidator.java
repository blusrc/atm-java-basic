package org.example;

public class PinValidator {
    public boolean isValid(String enteredPin, String correctPin) {
        if(enteredPin.isEmpty()){
            throw new IllegalArgumentException("Empty pin");

        }
        return enteredPin.equals(correctPin);
    }
}

