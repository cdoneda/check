package br.edu.ifrs.alvorada.check.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    private User user;

    private LocalDateTime dateTimeLoan;

    private LocalDateTime dateTimeReturn;

    @OneToOne
    private Item item;

    @NotNull
    private StatusLoan statusLoan;

    @OneToOne
    private User userReturn;
}
