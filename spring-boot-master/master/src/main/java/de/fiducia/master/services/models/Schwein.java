package de.fiducia.master.services.models;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
public class Schwein {
    @Setter(AccessLevel.PRIVATE)
    private String id;
    private String name;
    @Setter(AccessLevel.PRIVATE)
    private int gewicht;

    public void fuettern() {
        gewicht ++;
    }
}
