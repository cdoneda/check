package br.edu.ifrs.alvorada.check.web.page;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@PageUrl("http://localhost:{port}/home")
public class LoginPage extends FluentPage {

    @FindBy(css = "#submit-button")
    private FluentWebElement submitButton;

    public void isAt() {
        assertThat(window().title()).isEqualTo("Login");
    }

    public LoginPage fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        submitButton.submit();
        return this;
    }

    public LoginPage awaitUntilFormDisappear() {
        await().atMost(500, TimeUnit.MILLISECONDS).until(el(".sidebar")).present();
        return this;
    }

}