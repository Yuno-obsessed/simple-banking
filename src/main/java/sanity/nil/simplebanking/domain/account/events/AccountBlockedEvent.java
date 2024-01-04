package sanity.nil.simplebanking.domain.account.events;

import sanity.nil.simplebanking.domain.account.aggregate.Account;
import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;

import java.io.Serializable;
import java.util.UUID;

/*
    When account is blocked
 */
public class AccountBlockedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID accountID;

    @Override
    public BaseEvent getBaseEvent() {
        return null;
    }

    @Override
    public void publicate() {

    }

    @Override
    public boolean isPublicated() {
        return false;
    }

    public AccountBlockedEvent(Account account) {
        this.baseEvent = new BaseEvent("AccountBlocked");
        this.accountID = account.getAccountID().getID();
    }
}
