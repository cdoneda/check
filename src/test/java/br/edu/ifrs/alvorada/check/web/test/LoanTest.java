package br.edu.ifrs.alvorada.check.web.test;

import br.edu.ifrs.alvorada.check.web.config.MyFluentTest;
import br.edu.ifrs.alvorada.check.web.page.LoanPage;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
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




    // TODO CdT001 (RNG001, RNG003, RNG005) - Cassiano
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

    // TODO CdT001 (RNG004) - Cassiano
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
    }


    // TODO CdT001 (RNG005) - Cassiano
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
    }


    // TODO CdT001 (RNG002) - Cassiano
   /* @Test
    public void aSearchNotFoundResults() {

        loginUser("user");
        //Given
        //dogPage.go(port);
        dogPage.isAtHome();
        // and exit 3 entry dogs
        //When
        dogPage.openSearch();
        dogPage.awaitUntilPageLoaded(); // for pageLoader

        dogPage.typeSearchPhraseIn("wrong search");
        dogPage.submitSearch();
        dogPage.awaitUntilPageLoaded(); // for pageLoader

        //Then
        assertThat(window().title()).isEqualTo("Home");
        assertThat(dogPage.getMessageResults().text()).containsIgnoringCase("A search did not return results.");

    }

    // TODO CdT001 (RNG007) - Cassiano
    @Test
    public void deleteEntryDog() {

        loginUser("user");
        //Given
        //dogPage.go(port);
        dogPage.isAtHome();
        // and exit 3 entry dogs
        //When
        dogPage.openResult().awaitUntilEditButtonAppear().awaitUntilFormDogAppear();
        dogPage.awaitUntilPageLoaded(); // for pageLoader
        dogPage.isAtDetailDog();
        dogPage.awaitUntilPageLoaded(); // for pageLoader
        dogPage.editDetail().awaitUntilButtonSaveAppear().awaitUntilFormDogAppear();
        dogPage.awaitUntilPageLoaded(); // for pageLoader


        dogPage.openAdvancedOptions();
        dogPage.deleteDog();

        //Then
        assertThat(window().title()).isEqualTo("Home");
        assertThat(dogPage.getInfoMessage().text()).containsIgnoringCase("Dog entry was deleted");
        //alterado para 3 denvido ao teste que adiciona mais um cao na base de dados
        assertThat($(".thumbnail")).hasSize(3);

    }

    // TODO CdT001 (RNG007) - Cassiano
    @Test
    public void deleteEntryDogIsNotOwner() {
        //before
        loginUser("fulano");
        //Given
        dogPage.isAtHome();
        // and exit 3 entry dogs

        //When
        dogPage.openFirst().awaitUntilEditButtonAppear().awaitUntilFormDogAppear();
        dogPage.awaitUntilPageLoaded(); // for pageLoader
        dogPage.isAtDetailDog();
        dogPage.awaitUntilPageLoaded(); // for pageLoader
        dogPage.editDetail().awaitUntilButtonSaveAppear().awaitUntilFormDogAppear();
        dogPage.awaitUntilPageLoaded(); // for pageLoader


        //Then
        assertThat((dogPage.getAdvancedOptions()).present()).isFalse();
        assertThat(window().title()).isEqualTo("Record");


    }

    // TODO CdT001 (RNG007) - Cassiano
    @Test
    public void entryNewDog() {

        loginUser("user");
        dogPage.maxWindows();

        //Given
        //dogPage.go(port);
        dogPage.isAtHome();
        // and exit 3 entry dogs
        //When
        dogPage.buttonAddDog().awaitUntilFormDogAppear();
        dogPage.typeName("Spike");
        dogPage.selectGenderMale();
        dogPage.selectConfirmSubmit();
        //Then
        assertThat(window().title()).isEqualTo("Detail");
        //assertThat(dogPage.getInfoMessage().text()).containsIgnoringCase("Dog entry was deleted");
        //assertThat($(".thumbnail")).hasSize(2);

    }



*/
    /*

    // TODO CdT001 (RNG001) - Cassiano
    @Test
    public void lastProfessorEvaluateTermPaper() {

        //Given
        dogPage.go(port);
        dogPage.isAt();
        dogPage.selectTermPaperForEvaluationLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        dogPage.isAtTermPaperEvaluation();
        // e o professor for o último professor associado a avaliar este trabalho
        // e o outro avaliador informou os dados da avaliação escrita com 9, 6, 7, 7, 5 e 8
        // e a apresentação oral com os valores 7, 8 , 9 , 10, 8  e 7

        //When
        dogPage.fillTextAreaForm("Aprovado, revise o documento anexado");
        dogPage.fillAndSubmitForm("","9", "6", "7", "7", "5", "8","7", "8" , "9" , "10", "8", "7", dogPage.getFileAbsolutePath())
                .awaitUntilConfirmationModal();
        dogPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
       // assertThat(dogPage.getGradeFinalLastProfessor().text()).containsIgnoringCase("7,6");
       // assertThat(dogPage.getGradeStatusLastProfessor().text()).containsIgnoringCase("APROVADO");

    }
    // TODO CdT004 (RNG006, RNG008, RNG009, RNG012, RNG013) - Cassiano
    @Test
    public void notLastProfessorEvaluateTermPaper() {

        //Given
        dogPage.go(port);
        dogPage.isAt();
        dogPage.selectTermPaperForEvaluationNotLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        dogPage.isAtTermPaperEvaluation();

        //When
        dogPage.fillTextAreaForm("Aprovado, revise o documento anexado");
        dogPage.fillAndSubmitForm("","9", "6", "7", "7", "5", "8","7", "8" , "9" , "10", "8", "7", dogPage.getFileAbsolutePath())
                .awaitUntilConfirmationModal();
        dogPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
       // assertThat(dogPage.getGradeFinalNotLastProfessor().text()).containsIgnoringCase("");
       // assertThat(dogPage.getGradeStatusNotLastProfessor().text()).containsIgnoringCase("EM PROGRESSO");

    }



    // TODO CdT004 (RNG006, RNG008, RNG009, RNG010, RNG013, RNG014, RNG053, RNG054) - Cassiano
    @Test
    public void studentNotPresentation() {

        //Given
        dogPage.go(port);
        dogPage.isAt();
        dogPage.selectTermPaperForEvaluationLastProfessorTwo()
                .awaitUntilFormEvaluationTermPaperAppear();
        dogPage.isAtTermPaperEvaluation();
        dogPage.awaitTwoSeconds();
        // e o professor for o último professor associado a avaliar este trabalho
        // e o outro avaliador informou os dados da avaliação escrita com 9, 6, 7, 7, 5 e 8
        // e a apresentação oral com os valores 7, 8 , 9 , 10, 8  e 7

        //When
        dogPage.fillTextAreaForm("Não apresentou o trabalho");
        dogPage.fillAndSubmitForm("","0", "0", "0", "0", "0", "0","0", "0" , "0" , "0", "0", "0", dogPage.getFileAbsolutePath())
                .awaitUntilConfirmationModal();
        dogPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
        //assertThat(dogPage.getGradeFinalLastProfessorTwo().text()).containsIgnoringCase("0,0");
        //assertThat(dogPage.getGradeStatusLastProfessorTwo().text()).containsIgnoringCase("REPROVADO");
    }

    // TODO CdT004 (RNG009, RNG012, RNG013, RNG014, RNG054) - Cassiano
    @Test
    public void proposalEvaluationRedo() {

        //Given
        dogPage.go(port);
        dogPage.isAt();
        dogPage.selectProposalForEvaluationLastProfessor()
                .awaitUntilFormEvaluationAdviceAppear();
        dogPage.isAtProposalEvaluation();
        // e o professor for o último professor associado a avaliar este trabalho
        // e o outro avaliador aprovou a proposta

        //When
        dogPage.fillTextAreaForm("Não apresentou o trabalho");
        dogPage.selectRadioEvaluateRedoAndSubmit()
                .awaitUntilConfirmationModal();
        dogPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
        //assertThat(dogPage.getProposalStatusLastProfessor().text()).containsIgnoringCase("REFAZER");
    }*/

}