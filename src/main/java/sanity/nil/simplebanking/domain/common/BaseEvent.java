package sanity.nil.simplebanking.domain.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEvent implements Serializable {

    private UUID eventID;

    private String eventType;

    private LocalDateTime eventTimestamp;

    private transient boolean publicated;

    public BaseEvent(String eventType) {
        this.eventID = UUID.randomUUID();
        this.eventType = eventType;
        this.eventTimestamp = LocalDateTime.now();
        this.publicated = false;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(LocalDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public boolean isPublicated() {
        return publicated;
    }

    public void setPublicated(boolean publicated) {
        this.publicated = publicated;
    }
}
