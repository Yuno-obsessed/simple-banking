package sanity.nil.simplebanking.domain.account.entities;

import sanity.nil.simplebanking.domain.account.consts.TransactionParticipant;
import sanity.nil.simplebanking.domain.account.consts.TransactionType;
import sanity.nil.simplebanking.domain.transaction.entities.Balance;

import java.util.UUID;

public class Transaction {

    private UUID transactionID;
    private TransactionParticipant transactionParticipant;
    private TransactionType transactionType;
    private Balance balance;

    public Transaction(UUID transactionID, TransactionParticipant transactionParticipant,
                       TransactionType transactionType, Balance balance) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionParticipant = transactionParticipant;
        this.balance = balance;
    }

    public UUID getTransactionID() {
        return transactionID;
    }

    public TransactionParticipant getTransactionParticipant() {
        return transactionParticipant;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Balance getBalance() {
        return balance;
    }

}
