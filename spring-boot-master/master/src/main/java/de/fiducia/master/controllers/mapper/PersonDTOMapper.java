package de.fiducia.master.controllers.mapper;

import de.fiducia.master.controllers.dtos.PersonDTO;
import de.fiducia.master.services.models.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {

    PersonDTO convert(Person person);
    Person convert(PersonDTO person);

    List<PersonDTO> convertToDTO(List<Person> persons);
    List<Person> convertToModel(List<PersonDTO> persons);
}
