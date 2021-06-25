package de.fiducia.master.message.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteEvent extends Event{

    private String id; // Payload;
}
