package de.fiducia.master.services.impl;

import de.fiducia.master.repositories.PersonenRepository;
import de.fiducia.master.services.PersonServiceExecption;
import de.fiducia.master.services.models.Person;
import de.fiducia.master.services.models.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository personenRepositoryMock;

    @Mock
    private PersonMapper personMapperMock;

    @Mock
    private List<String> antipathenMock;

    @Test
    void speichern_parameterIsNull_throwsPersonenServiceException () {
        PersonServiceExecption ex = assertThrows(PersonServiceExecption.class, ()->objectUnderTest.speichern(null));
        assertEquals("Parameter darf nicht null sein.", ex.getMessage());
    }

    @Test
    void speichern_VornameIsNull_throwsPersonenServiceException () {
        Person p = Person.builder().id("123").vorname(null).nachname("Doe").build();
        PersonServiceExecption ex = assertThrows(PersonServiceExecption.class, ()->objectUnderTest.speichern(p));
        assertEquals("Vorname muss mindestens 2 Zeichen enthalten.", ex.getMessage());
    }

    @Test
    void speichern_VornameTooShort_throwsPersonenServiceException () {
        Person p = Person.builder().id("123").vorname("J").nachname("Doe").build();
        PersonServiceExecption ex = assertThrows(PersonServiceExecption.class, ()->objectUnderTest.speichern(p));
        assertEquals("Vorname muss mindestens 2 Zeichen enthalten.", ex.getMessage());
    }
    @Test
    void speichern_NachnameIsNull_throwsPersonenServiceException () {
        Person p = Person.builder().id("123").vorname("John").nachname(null).build();
        PersonServiceExecption ex = assertThrows(PersonServiceExecption.class, ()->objectUnderTest.speichern(p));
        assertEquals("Nachname muss mindestens 2 Zeichen enthalten.", ex.getMessage());
    }

    @Test
    void speichern_NachnameTooShort_throwsPersonenServiceException () {
        Person p = Person.builder().id("123").vorname("John").nachname("D").build();
        PersonServiceExecption ex = assertThrows(PersonServiceExecption.class, ()->objectUnderTest.speichern(p));
        assertEquals("Nachname muss mindestens 2 Zeichen enthalten.", ex.getMessage());
    }
    @Test
    void speichern_Antipath_throwsPersonenServiceException () {
        when(antipathenMock.contains(anyString())).thenReturn(true);
        Person p = Person.builder().id("123").vorname("John").nachname("Doe").build();
        PersonServiceExecption ex = assertThrows(PersonServiceExecption.class, ()->objectUnderTest.speichern(p));
        assertEquals("Antipath.", ex.getMessage());
    }

}