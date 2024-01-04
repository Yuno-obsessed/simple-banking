package sanity.nil.simplebanking.domain.account.vo;

import java.util.UUID;

public class AccountID {

    private UUID id;

    public AccountID() {
        this.id = UUID.randomUUID();
    }

    public AccountID(UUID id) {
        this.id = id;
    }

    public UUID getID() {
        return id;
    }
}
