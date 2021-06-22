package de.fiducia.master.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// nur für Jersey-Framework
@XmlRootElement
public class PersonDTO {
    private String id;
    private String vorname;
    private String nachname;
}
