package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.config.auth.UserImpl;
import br.edu.ifrs.alvorada.check.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {


    private LoanService loanService;
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("redirect:/home/");
        return mav;
    }


    // TODO cassiano private landing page
    @GetMapping("/home")
    public ModelAndView home(@AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/index");
        mav.addObject("loanedCountersByUser", loanService.findTotalLoanedCountersByUser(activeUser.getUser()));
        mav.addObject("loansCountersByUser", loanService.findTotalLoansCountersByUser(activeUser.getUser()));
        mav.addObject("totalLoanedCounters", loanService.findTotalLoanedCounters());
        mav.addObject("totalLoansCounters", loanService.findTotalLoansCounters());
        mav.addObject("totalLoansToday", loanService.findLoansByDays(0));
        mav.addObject("totalLoansLastSevenDays", loanService.findLoansByDays(-7));
        mav.addObject("totalLoansLastThirtyDays", loanService.findLoansByDays(-30));
        mav.addObject("loansFull", loanService.getAllLoansAndReturns());
        return mav;
    }
}
