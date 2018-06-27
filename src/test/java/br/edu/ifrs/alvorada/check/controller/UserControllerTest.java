package br.edu.ifrs.alvorada.check.controller;

import br.edu.ifrs.alvorada.check.config.Messages;
import br.edu.ifrs.alvorada.check.service.UserService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest {

    @MockBean
    private Messages messages;
    @MockBean
    private UserService userService;

    @Test
    public void view_user_profile() throws Exception{
        given(this.userService.getOne(user)).willReturn(user);
        this.mvc.perform(get("/user/profile")
                .with(user(userDetails))
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("/user/profile"))
                .andExpect(model().attribute("user"
                        ,allOf(
                                hasProperty("id", is(USER_ID))
                                ,hasProperty("name", is(USER_NAME)))));
    }

    @Test
    public void save_user_profile() throws Exception{
        given(this.messages.get("field.saved")).willReturn(FIELD_SAVED);
        given(this.userService.save(user)).willReturn(user);
        this.mvc.perform(post("/user/save")
                .with(user(userDetails))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", USER_ID.toString())
                .param("name", USER_NAME)
                .sessionAttr("user", user))
                .andExpect(view().name("redirect:/user/profile"))
                .andExpect(flash().attributeExists("message"))
                .andExpect(flash().attribute("message", FIELD_SAVED));
    }

    @Test
    public void save_user_profile_errors() throws Exception{
    	given(this.userService.save(user)).willReturn(user);
    	this.mvc.perform(post("/user/save")
    			.with(user(userDetails))
    			.with(csrf())
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.param("id", USER_ID.toString())
    			.sessionAttr("user", user))
    	.andExpect(view().name("/user/profile"));
    }
}