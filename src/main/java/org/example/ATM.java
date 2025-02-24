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
//        account.setBalance(account.getBalance());

        // mutant 1
//        account.setBalance(account.getBalance() - amount);

        // mutant 2
//        account.setBalance(account.getBalance() + (amount + 1));

        // mutant 3
//        account.setBalance(amount);

        return account.getBalance();

        //mutant 4
//        double oldBalance = account.getBalance();
//        account.setBalance(oldBalance + amount);
//        return oldBalance;
    }

//    public double withdraw(double amount) {
//        if (amount <= account.getBalance()) {
//            account.setBalance(account.getBalance() - amount);
//            return account.getBalance();
//        } else {
//            throw new IllegalArgumentException("Insufficient funds");
//        }
//    }

    /**
     * Modified withdraw method with multiple decisions:
     * - Reject negative amounts.
     * - If amount is zero, return current balance.
     * - If amount exceeds the balance, throw an exception.
     * - If amount is not a multiple of 10, throw an exception.
     * - Otherwise, subtract amount and return new balance.
     */
    public double withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative withdrawal not allowed");
        }
        if (amount == 0) {
            return account.getBalance();
        }
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("Withdrawal amount must be a multiple of 10");
        }
        account.setBalance(account.getBalance() - amount);
        return account.getBalance();
    }

    public double checkBalance() {
        return account.getBalance();
    }
}
