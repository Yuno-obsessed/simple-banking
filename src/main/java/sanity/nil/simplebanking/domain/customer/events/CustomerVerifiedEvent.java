package sanity.nil.simplebanking.domain.customer.events;

import sanity.nil.simplebanking.domain.common.BaseEvent;
import sanity.nil.simplebanking.domain.common.Event;
import sanity.nil.simplebanking.domain.customer.aggregate.Customer;

import java.io.Serializable;
import java.util.UUID;

/*
    When customer successfully verified for account creation
 */
public class CustomerVerifiedEvent implements Event, Serializable {

    private BaseEvent baseEvent;
    private UUID customerID;

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

    public CustomerVerifiedEvent(Customer customer) {
        this.baseEvent = new BaseEvent("CustomerVerified");
        this.customerID = customer.getCustomerID().getID();
    }
}
