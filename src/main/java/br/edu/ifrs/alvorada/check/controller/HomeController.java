package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.config.auth.UserImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("redirect:/home/");
        return mav;
    }


    // TODO cassiano private landing page
    @GetMapping("/home")
    public ModelAndView home(@AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/home");
        //mav.addObject("basicProjectsCounters", Item.findItemCounters());
        return mav;
    }
}
