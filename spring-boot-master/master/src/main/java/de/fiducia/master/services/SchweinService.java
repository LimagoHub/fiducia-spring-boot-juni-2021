package de.fiducia.master.services;

import de.fiducia.master.services.models.Schwein;

import java.util.Optional;

public interface SchweinService {
    Optional<Schwein> findeSchweinNachId(String id) throws SchweineServiceException;
    boolean fuettereSchweinMitId(String id) throws SchweineServiceException;


}
