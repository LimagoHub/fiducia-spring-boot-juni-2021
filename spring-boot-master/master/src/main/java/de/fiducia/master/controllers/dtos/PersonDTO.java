package de.fiducia.master.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.RegExp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// nur für Jersey-Framework
@XmlRootElement

// Validation
public class PersonDTO {
    @NotNull
    @Size(min=36, max=36)
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
    private String id;

    @Size(min=2, max=30)
    private String vorname;

    @NotNull
    @Size(min=2, max=30)
    private String nachname;
}
