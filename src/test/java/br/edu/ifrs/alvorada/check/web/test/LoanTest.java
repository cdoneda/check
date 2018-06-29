package br.edu.ifrs.alvorada.check.web.test;

import br.edu.ifrs.alvorada.check.web.config.MyFluentTest;
import br.edu.ifrs.alvorada.check.web.page.LoanPage;

import org.assertj.core.api.Assertions;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.openqa.selenium.By;


import java.util.List;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;


public class LoanTest extends MyFluentTest {

    @Page
    LoanPage loanPage;


    public void loginUser(){
        super.loginUser();
    }




    // TODO CdT001 (RNG001, RNG003, RNG005) - Cenário 1
    @Test
    public void loanItemSuccess() {
        loginUser();
        //Given
        loanPage.isAtHome();
        // usuário já possui o item 100 emprestado

        //When
        loanPage.buttonLoan();
        loanPage.awaitUntilFormAppear();
        loanPage.inputItem("102");
        loanPage.submitLoan();

        //Then
        loanPage.awaitUntilResult102Appear();
        loanPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Empréstimo");
        assertThat($(".collapsed")).hasSize(1);
        List<FluentWebElement> trs = loanPage.getTableList().find(By.xpath("//tbody//tr"));
        Assertions.assertThat(trs.size()).isEqualTo(2);

    }

    // TODO CdT001 (RNG004) - Cenário 2
    @Test
    public void loanTwiceSameItem() {
        loginUser();
        //Given
        loanPage.isAtHome();
        // usuário já possui um item emprestado

        //When
        loanPage.buttonLoan();
        loanPage.awaitUntilFormAppear();
        loanPage.inputItem("100");
        loanPage.submitLoan();

        //Then
        loanPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Empréstimo");
        List<FluentWebElement> trs = loanPage.getTableList().find(By.xpath("//tbody//tr"));
        Assertions.assertThat(trs.size()).isEqualTo(1);
        assertThat($(".help-block")).hasSize(1);
        String text = loanPage.getErrorMessage().getElement().getText();
        assertThat(text).isEqualTo("Você já retirou este item.");
    }


    // TODO CdT001 (RNG005) - Cenário 3
    @Test
    public void loanNoPermissionItem() {
        loginUser();
        //Given
        loanPage.isAtHome();
        // usuário já possui um item emprestado

        //When
        loanPage.buttonLoan();
        loanPage.awaitUntilFormAppear();
        loanPage.inputItem("107");
        loanPage.submitLoan();

        //Then
        loanPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Empréstimo");
        List<FluentWebElement> trs = loanPage.getTableList().find(By.xpath("//tbody//tr"));
        Assertions.assertThat(trs.size()).isEqualTo(1);
        assertThat($(".help-block")).hasSize(1);
        String text = loanPage.getErrorMessage().getElement().getText();
        assertThat(text).isEqualTo("O Item não está disponível para empréstimo");
    }


    // TODO CdT001 (RNG005) - Cenário 4
    @Test
    public void loanItemAlreadyLoaned() {
        loginUser();
        //Given
        loanPage.isAtHome();
        // usuário já possui um item emprestado

        //When
        loanPage.buttonLoan();
        loanPage.awaitUntilFormAppear();
        loanPage.inputItem("101");
        loanPage.submitLoan();

        //Then
        loanPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Empréstimo");
        List<FluentWebElement> trs = loanPage.getTableList().find(By.xpath("//tbody//tr"));
        Assertions.assertThat(trs.size()).isEqualTo(1);
        assertThat($(".help-block")).hasSize(1);
        String text = loanPage.getErrorMessage().getElement().getText();
        assertThat(text).isEqualTo("O item já está emprestado.");
    }


    // TODO CdT001 (RNG001) - Cenário 5
    @Test
    public void loanItemNotExist() {
        loginUser();
        //Given
        loanPage.isAtHome();
        // usuário já possui um item emprestado

        //When
        loanPage.buttonLoan();
        loanPage.awaitUntilFormAppear();
        loanPage.inputItem("33");
        loanPage.submitLoan();

        //Then
        loanPage.awaitUntilFormAppear();
        assertThat(window().title()).isEqualTo("Empréstimo");
        List<FluentWebElement> trs = loanPage.getTableList().find(By.xpath("//tbody//tr"));
        Assertions.assertThat(trs.size()).isEqualTo(1);
        assertThat($(".help-block")).hasSize(1);
        String text = loanPage.getErrorMessage().getElement().getText();
        assertThat(text).isEqualTo("A pesquisa não retornou resultados.");
    }

}