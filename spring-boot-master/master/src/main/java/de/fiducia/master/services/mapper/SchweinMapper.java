package de.fiducia.master.services.mapper;


import de.fiducia.master.repositories.entities.SchweinEntity;
import de.fiducia.master.services.models.Schwein;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity entity);
    SchweinEntity convert(Schwein schwein);
    List<Schwein> convert(List<SchweinEntity> entities);
}
