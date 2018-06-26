package br.edu.ifrs.alvorada.check.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Positive
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 2, max = 500)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
