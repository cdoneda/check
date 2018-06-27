package br.edu.ifrs.alvorada.check.web.test;

import br.edu.ifrs.alvorada.check.web.config.MyFluentTest;

import br.edu.ifrs.alvorada.check.web.page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

import org.fluentlenium.core.annotation.Page;
import org.junit.Test;



public class LoginTest extends MyFluentTest {

    @Page
    LoginPage loginPage;

    @Test
    public void checkLoginSucceed() {
        //Given
        loginPage.go(port);
        //When
        loginPage.fillAndSubmitForm("cassiano.doneda", "user")
                .awaitUntilFormDisappear();
        //Then
        assertThat(window().title()).isEqualTo("Home");
    }

    @Test
    public void checkLoginFailed() {
        //Given
        loginPage.go(port);
        //When
        loginPage.fillAndSubmitForm("wrongUser", "wrongPass");
        //Then
        assertThat($(".alert")).hasSize(1);
        loginPage.isAt();
    }


}