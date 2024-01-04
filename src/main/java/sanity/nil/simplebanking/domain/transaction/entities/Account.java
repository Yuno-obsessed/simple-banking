package sanity.nil.simplebanking.domain.transaction.entities;

import java.util.UUID;

public class Account {

    private UUID accountID;
    private String iban;
    private Bank bank;
    private Balance balance;
    private boolean isValid;
    private boolean isBlocked;

    public Account(UUID accountID, String iban, Bank bank, Balance balance, boolean isValid, boolean isBlocked) {
        this.accountID = accountID;
        this.iban = iban;
        this.bank = bank;
        this.balance = balance;
        this.isValid = isValid;
        this.isBlocked = isBlocked;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public Bank getBank() {
        return bank;
    }

    public Balance getBalance() {
        return balance;
    }
}
