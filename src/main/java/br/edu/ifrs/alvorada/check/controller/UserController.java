package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.config.Messages;
import br.edu.ifrs.alvorada.check.config.auth.UserImpl;
import br.edu.ifrs.alvorada.check.domain.User;
import br.edu.ifrs.alvorada.check.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	private final Messages messages;

	@GetMapping("/profile")
    public ModelAndView viewProfile(@AuthenticationPrincipal UserImpl activeUser){
        ModelAndView mav = new ModelAndView("/user/profile");
        mav.addObject("user", userService.getOne(activeUser.getUser()));
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid User user, BindingResult bindingResult,
                             RedirectAttributes redirectAttr){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/profile");
        }
        userService.save(user);
        ModelAndView mav = new ModelAndView("redirect:/user/profile");
        redirectAttr.addFlashAttribute("message", messages.get("field.saved"));
        return mav;
    }
}
