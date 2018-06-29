package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.config.auth.UserImpl;
import br.edu.ifrs.alvorada.check.domain.Item;
import br.edu.ifrs.alvorada.check.domain.Search;
import br.edu.ifrs.alvorada.check.domain.StatusLoan;
import br.edu.ifrs.alvorada.check.service.ItemService;
import br.edu.ifrs.alvorada.check.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Secured("ROLE_USER")
@Controller
@AllArgsConstructor
@RequestMapping("/loan")
public class LoanController {


    private final LoanService loanService;
    private final ItemService itemService;

    // TODO
    @GetMapping(value = {"", "/"})
    public ModelAndView form(@AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/loan/loan");
        mav.addObject("search", new Search());
        mav.addObject("loans", loanService.getLoans(activeUser.getUser()));
        return mav;
    }

    @PostMapping(value = {"", "/"})
    public ModelAndView getItemAndSaveLoan(@AuthenticationPrincipal UserImpl activeUser,
                                           @Valid Search search,
                                           BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("/loan/loan");
        Item newItem = itemService.getOneById(search.getCriteria(), bindingResult, activeUser.getUser());
        newItem = loanService.checkLoaned(newItem, bindingResult, activeUser.getUser());
        if (!bindingResult.hasErrors()) {
            loanService.save(activeUser.getUser(), search.getCriteria(), StatusLoan.OUTPUT);
            mav.addObject("search", new Search());
            mav.addObject("itemAdd", newItem);
        }
        mav.addObject("loans", loanService.getLoans(activeUser.getUser()));
        return mav;
    }

}
