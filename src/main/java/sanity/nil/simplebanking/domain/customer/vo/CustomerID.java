package sanity.nil.simplebanking.domain.customer.vo;

import java.util.UUID;

public class CustomerID {

    private UUID id;

    public CustomerID(UUID id) {
        this.id = id;
    }

    public UUID getID() {
        return id;
    }
}
