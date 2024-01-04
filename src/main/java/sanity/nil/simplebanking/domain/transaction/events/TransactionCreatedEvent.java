package sanity.nil.simplebanking.domain.transaction.events;

import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;
import sanity.nil.simplebanking.domain.transaction.aggregate.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/*
    When transaction was created and is to be verified by counterparts
 */
public class TransactionCreatedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID transactionID;
    private UUID senderID;
    private UUID recipientID;
    private BigDecimal amount;
    private String currency;

    @Override
    public BaseEvent getBaseEvent() {
        return this.baseEvent;
    }

    @Override
    public void publicate() {
        baseEvent.setPublicated(true);
    }

    @Override
    public boolean isPublicated() {
        return baseEvent.isPublicated();
    }

    public TransactionCreatedEvent(Transaction transaction) {
        this.baseEvent = new BaseEvent("TransactionCreated");
        this.transactionID = transaction.getTransactionID().getID();
        this.senderID = transaction.getSender().getAccountID();
        this.recipientID = transaction.getRecipient().getAccountID();
        this.amount = transaction.getAmount().getBalance();
        this.currency = transaction.getAmount().getCurrency().getCurrency();
    }
}
