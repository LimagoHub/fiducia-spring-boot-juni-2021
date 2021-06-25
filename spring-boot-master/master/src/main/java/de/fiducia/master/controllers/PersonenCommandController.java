package de.fiducia.master.controllers;

import de.fiducia.master.application.PersonenHandler;
import de.fiducia.master.controllers.dtos.PersonDTO;
import de.fiducia.master.controllers.mapper.PersonDTOMapper;
import de.fiducia.master.message.events.DeleteEvent;
import de.fiducia.master.message.events.SaveEvent;
import de.fiducia.master.services.PersonServiceExecption;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/personen")
public class PersonenCommandController {

    private final PersonenHandler handler;
    private final PersonDTOMapper mapper;

    public PersonenCommandController(PersonenHandler handler, PersonDTOMapper mapper) {
        this.handler = handler;
        this.mapper = mapper;
    }


    @DeleteMapping(path="/{id}")
    @ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {

        DeleteEvent event = DeleteEvent.builder().id(id).build();
        handler.handle(event);
        return ResponseEntity.ok().build();


    }


    @PutMapping(path="", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    @ApiResponse(responseCode = "200", description = "Person wurde geändert")
    @ApiResponse(responseCode = "201", description = "Person wurde erstellt")
    public ResponseEntity<Void> saveOrUpdateIdempotent(@Valid @RequestBody PersonDTO person) throws Exception {


        SaveEvent event = SaveEvent.builder().payload(person).build();
        handler.handle(event);
        return ResponseEntity.ok().build();


    }
}
