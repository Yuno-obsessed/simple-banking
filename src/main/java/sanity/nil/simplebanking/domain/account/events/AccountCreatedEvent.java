package sanity.nil.simplebanking.domain.account.events;

import sanity.nil.simplebanking.domain.account.aggregate.Account;
import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/*
    When the account was created
 */
public class AccountCreatedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID accountID;
    private String iban;
    private UUID owner;
    private Integer bankID;
    private BigDecimal balance;
    private String currency;
    private String accountType;
    private String accountStatus;

    public AccountCreatedEvent(Account account) {
        this.baseEvent = new BaseEvent("AccountCreated");
        this.accountID = account.getAccountID().getID();
        this.iban = account.getIban();
        this.owner = account.getOwner().getUserID();
        this.bankID = account.getBank().getBankID();
        this.balance = account.getBalance().getBalance();
        this.currency = account.getBalance().getCurrency().getCurrency();
        this.accountType = account.getAccountType().name();
        this.accountStatus = account.getAccountStatus().name();
    }

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
}
