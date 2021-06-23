package de.fiducia.master.services;

import de.fiducia.master.services.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonenService {

    boolean speichern(Person person) throws PersonServiceExecption;
    boolean loeschen(Person person) throws PersonServiceExecption;
    boolean loeschen(String id) throws PersonServiceExecption;
    List<Person> findeAllePersonen()  throws PersonServiceExecption;
    Optional<Person> findePersonMachId(String id) throws PersonServiceExecption;

}
