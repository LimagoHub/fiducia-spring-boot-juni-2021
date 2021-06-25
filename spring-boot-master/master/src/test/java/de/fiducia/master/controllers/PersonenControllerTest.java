package de.fiducia.master.controllers;

import de.fiducia.master.controllers.dtos.PersonDTO;
import de.fiducia.master.services.PersonenService;
import de.fiducia.master.services.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static de.fiducia.master.services.models.Person.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/create.sql", "/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService personenServiceMock;

    @Test
    void getPerson_1() throws Exception{
        // Arrange
        Person person = Person.builder().id("2707b527-46ef-4dfc-8e03-09f866cad8ea").vorname("John").nachname("Doe").build();
        Optional<Person> optional = Optional.of(person);
        when(personenServiceMock.findePersonMachId(anyString())).thenReturn(optional);

        PersonDTO result = restTemplate.getForObject("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", PersonDTO.class);
        assertEquals("John", result.getVorname());
    }
    @Test
    void getPerson_2() throws Exception{

//        String person = restTemplate.getForObject("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", String.class);
//        //assertEquals("Gabi", person.getVorname());
//        System.out.println(person);

        // Arrange

        Optional<Person> optional = Optional.empty();
        when(personenServiceMock.findePersonMachId(anyString())).thenReturn(optional);

        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", PersonDTO.class);
        assertEquals(404, entity.getStatusCodeValue());
    }

    @Test
    void getPerson_3() {

        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", PersonDTO.class);

        PersonDTO person = entity.getBody();
        assertEquals("Gabi", person.getVorname());
        assertEquals(200, entity.getStatusCodeValue());
    }

}