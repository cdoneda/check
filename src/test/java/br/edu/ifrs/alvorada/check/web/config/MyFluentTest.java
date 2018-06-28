package br.edu.ifrs.alvorada.check.web.config;

import br.edu.ifrs.alvorada.check.web.page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Wait
@FluentConfiguration(webDriver = "chrome")
public abstract class MyFluentTest extends FluentTest {

	@LocalServerPort
	protected int port;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Page
	LoginPage loginPage;

	public void loginUser() {
		loginPage.go(port);
		loginPage.fillAndSubmitForm("cassiano.doneda", "user").awaitUntilFormDisappear();
		assertThat(window().title()).isEqualTo("Home");
	}

	public void loginAdmin() {
		loginPage.go(port);
		loginPage.fillAndSubmitForm("dti", "user").awaitUntilFormDisappear();
		assertThat(window().title()).isEqualTo("Home");
	}
}
