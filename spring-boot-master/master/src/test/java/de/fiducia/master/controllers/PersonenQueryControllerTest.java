package de.fiducia.master.controllers;

import de.fiducia.master.controllers.dtos.PersonDTO;
import de.fiducia.master.services.PersonServiceExecption;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static de.fiducia.master.services.models.Person.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/create.sql", "/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenQueryControllerTest {

//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @MockBean
//    private PersonenService personenServiceMock;
//
//    @Test
//    void getPerson_1() throws Exception{
//        // Arrange
//        Person person = Person.builder().id("2707b527-46ef-4dfc-8e03-09f866cad8ea").vorname("John").nachname("Doe").build();
//        Optional<Person> optional = Optional.of(person);
//        when(personenServiceMock.findePersonMachId(anyString())).thenReturn(optional);
//
//        PersonDTO result = restTemplate.getForObject("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", PersonDTO.class);
//        assertEquals("John", result.getVorname());
//    }
//    @Test
//    void getPerson_2() throws Exception{
//
////        String person = restTemplate.getForObject("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", String.class);
////        //assertEquals("Gabi", person.getVorname());
////        System.out.println(person);
//
//        // Arrange
//
//        Optional<Person> optional = Optional.empty();
//        when(personenServiceMock.findePersonMachId(anyString())).thenReturn(optional);
//
//        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", PersonDTO.class);
//        assertEquals(404, entity.getStatusCodeValue());
//    }
//
//    @Test
//    void getPerson_4() throws Exception{
//        // Arrange
//
//        List<Person> personen = List.of(Person.builder().id("2707b527-46ef-4dfc-8e03-09f866cad8ea").vorname("John").nachname("Doe").build());
//        when(personenServiceMock.findeAllePersonen()).thenReturn(personen);
//
//       // List<PersonDTO> result = restTemplate.getForObject("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", new ParameterizedTypeReference<List<PersonDTO>>() {} );
//        ResponseEntity<List<PersonDTO>> entity =
//                restTemplate.exchange("/v1/personen", HttpMethod.GET
//                        ,null
//                        ,new ParameterizedTypeReference<List<PersonDTO>>() {} );
//        assertEquals(1, entity.getBody().size());
//    }
//
////    @Test
////    void getPerson_3() {
////
////        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", PersonDTO.class);
////
////        PersonDTO person = entity.getBody();
////        assertEquals("Gabi", person.getVorname());
////        assertEquals(200, entity.getStatusCodeValue());
////    }
//
//    @Test
//    void delete_1() throws Exception{
//        when(personenServiceMock.loeschen(anyString())).thenReturn(true);
//
//        ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", HttpMethod.DELETE,null,Void.class);
//        assertEquals(200, entity.getStatusCodeValue());
//    }
//    @Test
//    void delete_2() throws Exception{
//        when(personenServiceMock.loeschen(anyString())).thenReturn(false);
//
//        ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen/2707b527-46ef-4dfc-8e03-09f866cad8ef", HttpMethod.DELETE,null,Void.class);
//        assertEquals(404, entity.getStatusCodeValue());
//    }
//    @Test
//    void put_1() throws Exception{
//
//        // Arrange
//        var toUpload = PersonDTO.builder().id("2707b527-46ef-4dfc-8e03-09f866cad8ea").vorname("John").nachname("Doe").build();
//        var passedToService = Person.builder().id("2707b527-46ef-4dfc-8e03-09f866cad8ea").vorname("John").nachname("Doe").build();
//
//        HttpEntity<PersonDTO> entity = new HttpEntity<>(toUpload);
//
//        when(personenServiceMock.speichern(any(Person.class))).thenReturn(false); // Recordmode
//
//        // Act (Method under test)
//        ResponseEntity<Void> result = restTemplate.exchange("/v1/personen", HttpMethod.PUT,entity,Void.class);
//
//        // Assert
//        assertEquals(201, result.getStatusCodeValue());
//        verify(personenServiceMock, times(1)).speichern(passedToService);
//    }
//    @Test
//    void put_2() throws Exception{
//
//        // Arrange
//        var toUpload = PersonDTO.builder().id("2707b527-46ef-4dfc-8e03-09f866cad8ea").vorname("John").nachname("Doe").build();
//
//
//        HttpEntity<PersonDTO> entity = new HttpEntity<>(toUpload);
//
//        when(personenServiceMock.speichern(any(Person.class))).thenThrow(new PersonServiceExecption("Upps"));
//
//        // Act (Method under test)
//        ResponseEntity<Map<String, Object>> result =
//                restTemplate.exchange("/v1/personen", HttpMethod.PUT,entity,
//                        new ParameterizedTypeReference<Map<String, Object>>() {});
//
//        System.out.println(result.getBody().get("message"));
//        // Assert
//        assertEquals(400, result.getStatusCodeValue());
//        //verify(personenServiceMock, times(1)).speichern(passedToService);
//    }

}