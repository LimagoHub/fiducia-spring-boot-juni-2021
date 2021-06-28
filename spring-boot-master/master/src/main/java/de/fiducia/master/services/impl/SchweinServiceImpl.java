package de.fiducia.master.services.impl;

import de.fiducia.master.repositories.impl.SchweineRepo;
import de.fiducia.master.services.SchweinService;
import de.fiducia.master.services.SchweineServiceException;
import de.fiducia.master.services.mapper.SchweinMapper;
import de.fiducia.master.services.models.Schwein;

import java.util.Optional;

public class SchweinServiceImpl implements SchweinService {

    private final SchweineRepo repo;
    private final SchweinMapper mapper;

    public SchweinServiceImpl(SchweineRepo repo, SchweinMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public Optional<Schwein> findeSchweinNachId(String id) throws SchweineServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (Exception e) {
           throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public boolean fuettereSchweinMitId(String id) throws SchweineServiceException {
        try {
            Optional<Schwein> optional = findeSchweinNachId(id);
            if(optional.isPresent()) {
                Schwein piggy = optional.get();
                piggy.fuettern();
                repo.save(mapper.convert(piggy));
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }
}
