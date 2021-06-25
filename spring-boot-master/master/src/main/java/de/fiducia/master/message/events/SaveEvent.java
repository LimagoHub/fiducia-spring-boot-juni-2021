package de.fiducia.master.message.events;

import de.fiducia.master.controllers.dtos.PersonDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveEvent extends Event{

    private PersonDTO payload;


}
