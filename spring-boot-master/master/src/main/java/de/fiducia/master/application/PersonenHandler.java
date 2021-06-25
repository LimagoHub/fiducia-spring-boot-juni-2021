package de.fiducia.master.application;

import de.fiducia.master.controllers.mapper.PersonDTOMapper;
import de.fiducia.master.message.events.DeleteEvent;
import de.fiducia.master.message.events.SaveEvent;
import de.fiducia.master.services.PersonServiceExecption;
import de.fiducia.master.services.PersonenService;
import de.fiducia.master.services.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PersonenHandler {

    private final PersonenService personenService ;
    private final PersonDTOMapper mapper;


    public PersonenHandler(PersonenService personenService, PersonDTOMapper mapper) {
        this.personenService = personenService;
        this.mapper = mapper;
    }


    public void handle(DeleteEvent event) throws Exception{

        try {
            personenService.loeschen(event.getId());
            // Domainevent auslösen -> an Kafka erfolgreich gelöscht;
        } catch (PersonServiceExecption personServiceExecption) {
            // Domainevent auslösen -> an Kafka misserfolg;
            throw personServiceExecption;
        }
    }

    public void handle(SaveEvent event) throws Exception{

        try {
            personenService.speichern(mapper.convert(event.getPayload()));
            // Domainevent auslösen -> an Kafka erfolgreich gelöscht;
        } catch (PersonServiceExecption personServiceExecption) {
            // Domainevent auslösen -> an Kafka misserfolg;
            throw personServiceExecption;
        }
    }

}
