package de.fiducia.master.controllers;


import de.fiducia.master.controllers.dtos.PersonDTO;
import de.fiducia.master.controllers.mapper.PersonDTOMapper;
import de.fiducia.master.services.PersonServiceExecption;
import de.fiducia.master.services.PersonenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {


    private final PersonenService personenService;
    private final PersonDTOMapper mapper;

    public PersonenController(PersonenService personenService, PersonDTOMapper mapper) {
        this.personenService = personenService;
        this.mapper = mapper;
    }

    // ERste Methode werte an den server zu übergeben (Daten werden in den pfad eingebaut)
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Fehler im System")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable String id) throws PersonServiceExecption {
         return ResponseEntity.of(personenService.findePersonMachId(id).map(mapper::convert));
    }
    // Zweite Methode werte an den server zu übergeben (Daten werden via ?-Operator)
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> getPersons(
            @RequestParam(required = false, defaultValue = "leer") String vorname,
            @RequestParam(required = false, defaultValue = "leer")String nachname
    )  throws PersonServiceExecption{

        return ResponseEntity.ok(mapper.convertToDTO(personenService.findeAllePersonen()));
    }

    // Dritte Methode werte an den server zu übergeben (im Body mitgegeben) (Hier Post als ersatzget)
    @PostMapping(path="/to-upper", produces = MediaType.APPLICATION_JSON_VALUE, consumes  = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> toUpper(@RequestBody  PersonDTO person) {
        person.setVorname(person.getVorname().toUpperCase());
        person.setNachname(person.getNachname().toUpperCase());
        return ResponseEntity.ok(person);
    }

    @DeleteMapping(path="/{id}")
    @ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<Void> delete(@PathVariable String id) throws PersonServiceExecption {

        if(personenService.loeschen(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();

    }


//    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> saveOrUpdateNotIdempotent(@RequestBody  PersonDTO person, UriComponentsBuilder uriComponentsBuilder) {
//        if(person.getId() == null) {
//            person.setId(UUID.randomUUID().toString());
//        }
//
//        UriComponents uriComponents = uriComponentsBuilder.path("/v1/personen/{id}").buildAndExpand(person.getId());
//
//        System.out.println("Person nit der ID " + person.getId() + " wird nicht idempotent gespeichert oder angelegt");
//        // Call service to save
//        return ResponseEntity.created(uriComponents.toUri()).build();
//    }
    @PutMapping(path="", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    @ApiResponse(responseCode = "200", description = "Person wurde geändert")
    @ApiResponse(responseCode = "201", description = "Person wurde erstellt")
    public ResponseEntity<Void> saveOrUpdateIdempotent(@Valid @RequestBody  PersonDTO person) throws PersonServiceExecption{

       if(personenService.speichern(mapper.convert(person)))
           return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
