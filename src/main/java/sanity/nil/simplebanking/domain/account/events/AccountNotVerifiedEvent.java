package sanity.nil.simplebanking.domain.account.events;

import sanity.nil.simplebanking.domain.account.aggregate.Account;
import sanity.nil.simplebanking.domain.account.entities.Transaction;
import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;

import java.io.Serializable;
import java.util.UUID;

/*
    When account failed verification on being able to execute requested transaction
 */
public class AccountNotVerifiedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID accountID;
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

    public AccountNotVerifiedEvent(Account account, Transaction transaction, String reason) {
        this.baseEvent = new BaseEvent("AccountNotVerified");
        this.accountID = account.getAccountID().getID();
        this.transactionID = transaction.getTransactionID();
        this.reason = reason;
    }
}
