package sanity.nil.simplebanking.domain.transaction.vo;

import java.util.UUID;

public class TransactionID {

    private UUID id;

    public TransactionID(UUID id) {
        this.id = id;
    }

    public UUID getID() {
        return id;
    }
}
