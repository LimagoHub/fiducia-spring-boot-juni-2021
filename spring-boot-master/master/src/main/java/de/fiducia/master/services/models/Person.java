package de.fiducia.master.services.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


// Lombok
@Data
@Builder
public class Person {

    @Setter(AccessLevel.PRIVATE)
    private String id;


    private String vorname;


    private String nachname;
}
