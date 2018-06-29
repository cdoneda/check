package br.edu.ifrs.alvorada.check.web.test;

import br.edu.ifrs.alvorada.check.web.config.MyFluentTest;
import br.edu.ifrs.alvorada.check.web.page.LoanPage;
import br.edu.ifrs.alvorada.check.web.page.ReturnPage;
import org.assertj.core.api.Assertions;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;


public class ReturnTest extends MyFluentTest {

    @Page
    ReturnPage returnPage;


    public void loginUser(){
        super.loginUser();
    }




    // TODO CdT001 (RNG001, RNG003, RNG005) - Cenário 1
    @Test
    public void returnItemSuccess() {
        loginAdmin();
        //Given
        returnPage.isAtHome();
        // usuário já possui o item 100 emprestado

        //When
        returnPage.buttonReturn();
        returnPage.awaitUntilFormAppear();
        returnPage.isAtReturn();
        returnPage.inputItem("100");
        returnPage.submitLoan();

        //Then
        returnPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Devolução");
        Assertions.assertThat(returnPage.getInfoMessage().text()).containsIgnoringCase("Item foi devolvido: #100");

    }

    // TODO CdT001 (RNG001) - Cenário 5
    @Test
    public void returnItemNoPermissionForUser() {
        loginUser();
        //Given
        returnPage.isAtHome();
        // usuário já possui um item emprestado

        //When
        returnPage.buttonReturn();
        returnPage.awaitUntilFormAppear();
        returnPage.inputItem("101");
        returnPage.submitLoan();

        //Then
        returnPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Devolução");
        String text = returnPage.getErrorMessage().getElement().getText();
        assertThat(text).isEqualTo("Você não tem permissão para devolver este item. Solicite para um administrador.");
    }

}