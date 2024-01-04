package sanity.nil.simplebanking.domain.account.events;

import sanity.nil.simplebanking.domain.account.aggregate.Account;
import sanity.nil.simplebanking.domain.account.entities.Transaction;
import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/*
    When account's balance was successfully updated after transaction
 */
public class AccountBalanceUpdatedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID accountID;
    private UUID transactionID;
    private BigDecimal newBalance;
    private String transactionParticipant;

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

    public AccountBalanceUpdatedEvent(Account account, Transaction transaction) {
        this.baseEvent = new BaseEvent("AccountBalanceUpdated");
        this.accountID = account.getAccountID().getID();
        this.transactionID = transaction.getTransactionID();
        this.newBalance = account.getBalance().getBalance();
        this.transactionParticipant = transaction.getTransactionParticipant().name();
    }
}
