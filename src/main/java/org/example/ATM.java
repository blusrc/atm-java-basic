package org.example;

public class ATM {
    private final Account account;
    private final PinValidator pinValidator;

    public ATM(Account account, PinValidator pinValidator) {
        this.account = account;
        this.pinValidator = pinValidator;
    }

    public boolean validatePin(String pin) {
        return pinValidator.isValid(pin, account.getPin());
    }

    public double deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        return account.getBalance();
    }

    public double withdraw(double amount) {
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            return account.getBalance();
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public double checkBalance() {
        return account.getBalance();
    }
}
