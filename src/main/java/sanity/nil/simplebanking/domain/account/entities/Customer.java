package sanity.nil.simplebanking.domain.account.entities;

import java.util.UUID;

public class Customer {

    private UUID userID;

    public Customer(UUID userID) {
        this.userID = userID;
    }

    public UUID getUserID() {
        return userID;
    }
}
