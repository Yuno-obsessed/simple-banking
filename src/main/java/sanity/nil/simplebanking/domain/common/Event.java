package sanity.nil.simplebanking.domain.common;

public interface Event {

    BaseEvent getBaseEvent();

    void publicate();

    boolean isPublicated();
}
