package de.fiducia.master.services.impl;

import de.fiducia.master.repositories.PersonenRepository;
import de.fiducia.master.services.PersonServiceExecption;
import de.fiducia.master.services.PersonenService;
import de.fiducia.master.services.models.Person;
import de.fiducia.master.services.models.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;
    private final List<String> antipathen;

    public PersonenServiceImpl(PersonenRepository repo, PersonMapper mapper, List<String> antipathen) {
        this.repo = repo;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }


    /*
        wenn person null -> PSE
        wenn vorname null -> PSE
        wenn vorname weniger als 2 Zeicher -> PSE
        wenn nachname null -> PSE
        wenn nachname weniger als 2 Zeicher -> PSE

        wenn vorname Attila -> PSE

        wenn runtimeexception im underlying service -> RuntimeException in PSE wandeln

        happy day -> person ans repo übergeben

        person exits return true else false
     */
    @Override
    public boolean speichern(Person person) throws PersonServiceExecption {
        try {
            return speichernImpl(person);
        } catch (RuntimeException ex) {
            // Log
            throw new PersonServiceExecption("Fehler im darunter liegenden Service", ex);
        }
    }

    private boolean speichernImpl(Person person) throws PersonServiceExecption {
        checkPerson(person);
        boolean exits = repo.existsById(person.getId());
        repo.save(mapper.convert(person));
        return exits;
    }

    private void checkPerson(Person person) throws PersonServiceExecption {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonServiceExecption {
        if(antipathen.contains(person.getVorname()))
            throw new PersonServiceExecption("Antipath.");
    }

    private void validatePerson(Person person) throws PersonServiceExecption {
        if(person == null)
            throw new PersonServiceExecption("Parameter darf nicht null sein.");
        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonServiceExecption("Vorname muss mindestens 2 Zeichen enthalten.");
        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersonServiceExecption("Nachname muss mindestens 2 Zeichen enthalten.");
    }

    @Override
    public boolean loeschen(Person person) throws PersonServiceExecption {
        return false;
    }

    @Override
    public boolean loeschen(String id) throws PersonServiceExecption {
        return false;
    }

    @Override
    public List<Person> findeAllePersonen() throws PersonServiceExecption {
        return null;
    }

    @Override
    public Optional<Person> findePersonMachId(String id) throws PersonServiceExecption {
        return Optional.empty();
    }
}
