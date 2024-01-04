package sanity.nil.simplebanking.domain.transaction.events;

import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;
import sanity.nil.simplebanking.domain.transaction.aggregate.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/*
    When transaction's processing was completed (all transaction members accounts made their changes)
 */
public class TransactionProcessingCompleted implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID transactionID;
    private UUID sender;
    private UUID recipient;
    private BigDecimal amount;
    private String currency;

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    @Override
    public void publicate() {
        baseEvent.setPublicated(true);
    }

    @Override
    public boolean isPublicated() {
        return baseEvent.isPublicated();
    }

    public TransactionProcessingCompleted(Transaction transaction) {
        this.baseEvent = new BaseEvent("TransactionProcessingCompleted");
        this.transactionID = transaction.getTransactionID().getID();
        this.sender = transaction.getSender().getAccountID();
        this.recipient = transaction.getRecipient().getAccountID();
        this.amount = transaction.getAmount().getBalance();
        this.currency = transaction.getAmount().getCurrency().getCurrency();
    }
}
