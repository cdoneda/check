package br.edu.ifrs.alvorada.check.service;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDetailsImplServiceTest {

    @Autowired
    UserDetailsImplService service;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final String USERNAME = "cassiano.doneda";

    @Test
    public void shouldReturnUserDetails() {
        // given
        // having existing user

        // when
        UserDetails userDetails = service.loadUserByUsername(USERNAME);

        // then
        assertThat(userDetails).extracting("user.name").contains("Cassiano Doneda");
        assertThat(userDetails.getAuthorities()).hasSize(1);
    }

    @Test
    public void shouldThrowsExceptionWhenUsernameIsNull() {
        // given
        boolean thrown = false;
        String username = null;
        // when
        try {
            service.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            thrown = true;
        }
        // then
        assertThat(thrown).isTrue();
    }

    @Test
    public void shouldThrowsExceptionWhenNotFoundUsername() {
        // given
        boolean thrown = false;
        String username = "User that not exist";
        // when
        try {
            service.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            thrown = true;
        }
        // then
        assertThat(thrown).isTrue();
    }
}