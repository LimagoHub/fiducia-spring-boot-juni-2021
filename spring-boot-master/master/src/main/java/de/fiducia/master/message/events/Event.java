package de.fiducia.master.message.events;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    private final String eventID = UUID.randomUUID().toString();
    private final LocalDateTime timestamp = LocalDateTime.now();

    public String getEventId() {
        return eventID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
