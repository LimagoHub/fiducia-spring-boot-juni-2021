package de.fiducia.master.repositories;

import de.fiducia.master.repositories.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> {

    List<PersonEntity> findByVorname(String vorname);
    List<PersonEntity> findByNachname(String nachname);
    List<PersonEntity> findByVornameOrNachnameOrderByNachnameAsc(String vorname,String nachname);
    List<PersonEntity> findByVornameAndNachnameOrderByNachnameAsc(String vorname,String nachname);

    List<PersonEntity> findeAlle();
}
