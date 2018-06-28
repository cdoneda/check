package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.domain.Loan;
import br.edu.ifrs.alvorada.check.domain.Search;
import br.edu.ifrs.alvorada.check.domain.StatusLoan;
import br.edu.ifrs.alvorada.check.domain.User;
import br.edu.ifrs.alvorada.check.service.ItemService;
import br.edu.ifrs.alvorada.check.service.LoanService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;

public class LoanControllerTest {

    @MockBean
    LoanService loanService;
    @MockBean
    ItemService itemService;


    private final Long USER_ID = 1002L;
    private final String USERNAME = "Cassiano Doneda";
    private final String EMAIL = "cassiano.doneda@alvorada.ifrs.edu.br";
    private StatusLoan STATUS_LOAN_OUTPUT = StatusLoan.OUTPUT;

    private User loanTestUser(){
        User user = new User();
        user.setId(USER_ID);
        user.setName(USERNAME);
        user.setEmail(EMAIL);

        return user;
    }
    private Loan loadTestLoan() {
        Loan loan = new Loan();
        loan.setUser(loanTestUser());
        loan.setStatusLoan(STATUS_LOAN_OUTPUT);
        return loan;
    }

    private Search loanTestSearch(){
        Search search = new Search();
        return search;
    }

    @Test
    public void form() {

    }

    @Test
    public void getItemAndSaveLoan() {
    }
}