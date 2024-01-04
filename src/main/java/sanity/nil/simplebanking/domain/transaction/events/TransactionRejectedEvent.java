package sanity.nil.simplebanking.domain.transaction.events;

import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;
import sanity.nil.simplebanking.domain.transaction.aggregate.Transaction;

import java.io.Serializable;
import java.util.UUID;

/*
    When transaction's execution was rejected during processing with some error
 */
public class TransactionRejectedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID transactionID;
    private String reason;

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

    public TransactionRejectedEvent(Transaction transaction, String reason) {
        this.baseEvent = new BaseEvent("TransactionRejected");
        this.transactionID = transaction.getTransactionID().getID();
        this.reason = reason;
    }
}
