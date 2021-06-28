package de.fiducia.master.repositories.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchweinEntity {

    private String id;
    private String name;
    private int gewicht;
}
