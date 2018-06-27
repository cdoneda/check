package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.service.LoanService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest extends BaseControllerTest {

	@MockBean
	private LoanService loanService;

	// TODO Cassiano

	@Test
	public void redirectToHome() throws Exception {

		this.mvc.perform(get("/")
				.with(user(userDetails))
				.with(csrf())
				.accept(MediaType.TEXT_HTML)
		)
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/home/"))
		;
	}
/*
	@Test
	public void view_home() throws Exception {
		when(loanService.findTotalLoanedCountersByUser(user)).thenReturn(new ArrayList<>());
		this.mvc.perform(get("/home")
				.with(user(userDetails))
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("/index"));
	}*/
//
//	mav.addObject("loanedCountersByUser", loanService.findTotalLoanedCountersByUser(activeUser.getUser()));
//        mav.addObject("loansCountersByUser", loanService.findTotalLoansCountersByUser(activeUser.getUser()));
//        mav.addObject("totalLoanedCounters", loanService.findTotalLoanedCounters());
//        mav.addObject("totalLoansCounters", loanService.findTotalLoansCounters());
//        mav.addObject("totalLoansToday", loanService.findLoansByDays(0));
//        mav.addObject("totalLoansLastSevenDays", loanService.findLoansByDays(-7));
//        mav.addObject("totalLoansLastThirtyDays", loanService.findLoansByDays(-30));
//        mav.addObject("loansFull", loanService.getAllLoansAndReturns());

}