package de.fiducia.master.repositories;

import de.fiducia.master.repositories.entities.PersonEntity;
import de.fiducia.master.repositories.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonenRepository extends CrudRepository<PersonEntity, String>, PersonenCustomRepository {

    List<PersonEntity> findByVorname(String vorname);
    List<PersonEntity> findByNachname(String nachname);
    List<PersonEntity> findByVornameOrNachnameOrderByNachnameAsc(String vorname,String nachname);
    List<PersonEntity> findByVornameAndNachnameOrderByNachnameAsc(String vorname,String nachname);

    List<PersonEntity> findeAlle();

    @Query("select new de.fiducia.master.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity p")
    List<TinyPerson> findAllTinyPersons();

    @Query("update PersonEntity set vorname = :vorname where id=:id")
    void updatePersonVorname(String id, String vorname);
}
