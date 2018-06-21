package br.edu.ifrs.alvorada.check.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Search {

    @NotNull
    @Positive
    private Long criteria;
}
