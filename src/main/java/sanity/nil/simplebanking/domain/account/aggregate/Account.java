package sanity.nil.simplebanking.domain.account.aggregate;

import sanity.nil.simplebanking.domain.account.consts.AccountStatus;
import sanity.nil.simplebanking.domain.account.consts.AccountType;
import sanity.nil.simplebanking.domain.account.consts.TransactionType;
import sanity.nil.simplebanking.domain.account.entities.Balance;
import sanity.nil.simplebanking.domain.account.entities.Bank;
import sanity.nil.simplebanking.domain.account.entities.Customer;
import sanity.nil.simplebanking.domain.account.entities.Transaction;
import sanity.nil.simplebanking.domain.account.events.*;
import sanity.nil.simplebanking.domain.account.exceptions.AccountInvalid;
import sanity.nil.simplebanking.domain.account.exceptions.AccountNotEligible;
import sanity.nil.simplebanking.domain.account.exceptions.WithdrawalGreaterThanBalance;
import sanity.nil.simplebanking.domain.account.vo.AccountID;
import sanity.nil.simplebanking.domain.account.vo.Currency;
import sanity.nil.simplebanking.domain.common.AggregateRoot;

import java.math.BigDecimal;
import java.util.UUID;

import static sanity.nil.simplebanking.domain.account.consts.AccountType.CERTIFICATE_OF_DEPOSIT;
import static sanity.nil.simplebanking.domain.account.consts.AccountType.SAVINGS;

public class Account extends AggregateRoot {

    private AccountID accountID;
    private String iban;
    private Customer owner;
    private Bank bank;
    private Balance balance;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private boolean blocked;
    private boolean valid;

    public void verifyForOperation(Transaction transaction) {
        validate();
        TransactionType transactionType = transaction.getTransactionType();
        switch (transactionType) {
            case WITHDRAWAL -> {
                if (accountType.equals(CERTIFICATE_OF_DEPOSIT)) {
                    accountVerificationError(AccountNotEligible.notEligibleType(transactionType), transaction);
                }
                this.recordEvent(new AccountVerifiedEvent(this, transaction));
            }
            case DEPOSIT -> {
                this.recordEvent(new AccountVerifiedEvent(this, transaction));
            }
            case TRANSFER -> {
                if (accountType.equals(SAVINGS)) {
                    accountVerificationError(AccountNotEligible.notEligibleType(transactionType), transaction);
                }
                this.recordEvent(new AccountVerifiedEvent(this, transaction));
            }
        }

        validate();
    }

    public Account create(UUID accountID, String iban, UUID userID, Integer bankID, String country,
                          BigDecimal balance, Currency currency, AccountType accountType) {
        this.accountID = new AccountID(accountID);
        this.iban = iban;
        this.owner = new Customer(userID);
        this.bank = new Bank(bankID, country);
        this.balance = new Balance(balance, currency);
        this.accountType = accountType;
        this.accountStatus = AccountStatus.AWAITING;
        this.blocked = false;
        this.valid = true;

        this.validate();

        this.recordEvent(new AccountCreatedEvent(this));

        return this;
    }

    public void updateBalance(Transaction transaction) {
        this.validate();

        BigDecimal updatedBalance;
        if (balance.getCurrency().equals(transaction.getBalance().getCurrency())) {
            updatedBalance = transaction.getBalance().getBalance();
        } else {
            updatedBalance = transaction.getBalance().balanceInAnotherCurrency(balance.getCurrency());
        }
        BigDecimal newBalance = balance.getBalance();
        switch (transaction.getTransactionParticipant()) {
            case SENDER -> {
                newBalance = newBalance.add(updatedBalance);
            }
            case RECIPIENT -> {
                if (updatedBalance.compareTo(newBalance) < 0) {
                    this.recordEvent(new AccountBalanceUpdateRejectedEvent(this, transaction));
                    throw new WithdrawalGreaterThanBalance(updatedBalance, newBalance);
                }
                newBalance = newBalance.subtract(updatedBalance);
            }
        }

       balance.setBalance(newBalance);

        this.recordEvent(new AccountBalanceUpdatedEvent(this, transaction));
        validate();
    }

    public void validate() {
        if (blocked && !valid) {
           this.recordEvent(new AccountBlockedEvent(this));
           throw AccountInvalid.isBlockedOrNotValid();
        }
        if (balance.getBalance().compareTo(BigDecimal.ZERO) > -1) {
            throw AccountInvalid.balanceInvalid();
        }
        if (accountStatus != AccountStatus.REJECTED) {
            throw AccountInvalid.isRejected();
        }
    }

    public void accountVerificationError(RuntimeException exception, Transaction transaction) {
        this.recordEvent(new AccountNotVerifiedEvent(this, transaction, exception.getMessage()));
        throw exception;
    }

    public AccountID getAccountID() {
        return accountID;
    }

    public String getIban() {
        return iban;
    }

    public Customer getOwner() {
        return owner;
    }

    public Bank getBank() {
        return bank;
    }

    public Balance getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isValid() {
        return valid;
    }
}
