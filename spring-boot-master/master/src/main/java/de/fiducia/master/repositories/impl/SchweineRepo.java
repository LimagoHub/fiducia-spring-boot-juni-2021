package de.fiducia.master.repositories.impl;

import de.fiducia.master.repositories.entities.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

public interface SchweineRepo extends CrudRepository<SchweinEntity,String> {
}
