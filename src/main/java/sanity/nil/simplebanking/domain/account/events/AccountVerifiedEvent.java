package sanity.nil.simplebanking.domain.account.events;

import sanity.nil.simplebanking.domain.account.aggregate.Account;
import sanity.nil.simplebanking.domain.account.entities.Transaction;
import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;

import java.io.Serializable;
import java.util.UUID;

/*
    When account passed verification on being able to execute requested transaction
 */
public class AccountVerifiedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID accountID;
    private UUID transactionID;

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

    public AccountVerifiedEvent(Account account, Transaction transaction) {
        this.baseEvent = new BaseEvent("AccountVerified");
        this.accountID = account.getAccountID().getID();
        this.transactionID = transaction.getTransactionID();
    }

}
