package de.fiducia.master.services.mapper;

import de.fiducia.master.repositories.entities.PersonEntity;
import de.fiducia.master.services.models.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonEntity entity);
    PersonEntity convert(Person person);
    List<Person> convert(List<PersonEntity> entities);
}
