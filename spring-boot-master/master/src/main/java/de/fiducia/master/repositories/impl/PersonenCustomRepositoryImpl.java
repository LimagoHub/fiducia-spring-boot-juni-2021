package de.fiducia.master.repositories.impl;

import de.fiducia.master.repositories.PersonenCustomRepository;
import de.fiducia.master.repositories.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonenCustomRepositoryImpl implements PersonenCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<PersonEntity> xyz() {
        TypedQuery<PersonEntity> query = entityManager.createQuery("from PersonEntity", PersonEntity.class);
        query.setFirstResult(100);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
