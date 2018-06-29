package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.domain.*;
import br.edu.ifrs.alvorada.check.service.ItemService;
import br.edu.ifrs.alvorada.check.service.LoanService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Arrays;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LoanController.class)
public class LoanControllerTest extends BaseControllerTest {

    @MockBean
    LoanService loanService;
    @MockBean
    ItemService itemService;

    private final Long USER_ID = 1002L;
    private final String USERNAME = "Cassiano Doneda";
    private final String EMAIL = "cassiano.doneda@alvorada.ifrs.edu.br";
    private StatusLoan STATUS_LOAN_OUTPUT = StatusLoan.OUTPUT;

    private Loan loadTestLoanOne() {
        Loan loan = new Loan();
        loan.setId(200L);
        loan.setUser(user);
        loan.setStatusLoan(STATUS_LOAN_OUTPUT);
        loan.setItem(loanTestItem());
        loan.setDateTimeLoan(LocalDateTime.now());
        return loan;
    }

    private Loan loadTestLoanTwo() {
        Loan loan = new Loan();
        loan.setId(300L);
        loan.setUser(user);
        loan.setStatusLoan(STATUS_LOAN_OUTPUT);
        loan.setItem(loanTestItem());
        loan.setDateTimeLoan(LocalDateTime.now());
        return loan;
    }

    private Item loanTestItem(){
        Item item = new Item();
        item.setId(1000L);
        item.setName("Item");
        return item;
    }


    private Search loanTestSearch() {
        Search search = new Search();
        return search;
    }

    @Test
    public void loadFormWithLoans() throws Exception {

        when(loanService.getLoans(user)).thenReturn(Arrays.asList(loadTestLoanOne(), loadTestLoanTwo()));

        this.mvc.perform(get("/loan/")
                .with(user(userDetails))
                .with(csrf())
                .accept(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("/loan/loan"))
                .andExpect(model().attribute("search", hasProperty("criteria")))
                .andExpect(model().attribute("loans", hasSize(2)))
        ;
        verify(loanService, times(1))
                .getLoans(user);
        verifyNoMoreInteractions(loanService);
    }



    @Test
    public void getItemAndSaveLoan() {

    }
}