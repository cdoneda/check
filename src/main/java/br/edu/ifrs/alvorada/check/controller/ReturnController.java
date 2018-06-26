package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.config.Messages;
import br.edu.ifrs.alvorada.check.config.auth.UserImpl;
import br.edu.ifrs.alvorada.check.domain.Item;
import br.edu.ifrs.alvorada.check.domain.Loan;
import br.edu.ifrs.alvorada.check.domain.Search;
import br.edu.ifrs.alvorada.check.domain.StatusLoan;
import br.edu.ifrs.alvorada.check.service.ItemService;
import br.edu.ifrs.alvorada.check.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/return")
public class ReturnController {

    private final LoanService loanService;
    private final ItemService itemService;
    private final Messages messages;

    @GetMapping(value = {"", "/"})
    public ModelAndView form(@AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/return/return");
        mav.addObject("search", new Search());
        mav.addObject("loans", loanService.getLoans(activeUser.getUser()));
        return mav;
    }

    @PostMapping(value = {"", "/"})
    public ModelAndView getItemAndSaveLoan(@AuthenticationPrincipal UserImpl activeUser,
                                           @Valid Search search,
                                           BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("/return/return");
        Item newItem = itemService.getOneById(search.getCriteria(), bindingResult, activeUser.getUser());
        Loan loan = loanService.checkReturn(newItem, bindingResult, activeUser.getUser(), loanService.isAdmin(activeUser));

        if (!bindingResult.hasErrors()) {
            loanService.saveReturn(activeUser.getUser(), loan.getId(), StatusLoan.INPUT);
            mav.addObject("message", messages.get("field.saved"));
        }
        mav.addObject("loans", loanService.getLoans(activeUser.getUser()));
        return mav;
    }
}
