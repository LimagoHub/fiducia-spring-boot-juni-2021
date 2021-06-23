package de.fiducia.master.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="tbl_personen")
@NamedQuery(name = "PersonEntity.findeAlle", query = "select p from PersonEntity p")
public class PersonEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(length = 30, nullable = true)
    private String vorname;

    @Column(length = 30, nullable = false)
    private String nachname;


}
