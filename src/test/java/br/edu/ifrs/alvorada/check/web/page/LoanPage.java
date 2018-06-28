package br.edu.ifrs.alvorada.check.web.page;

import lombok.Data;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@PageUrl("http://localhost:{port}/loan/")
@Data
public class LoanPage extends FluentPage {

    @FindBy(css = "#btn-loan")
    private FluentWebElement btnLoan;

    @FindBy(css = "#form-search")
    private FluentWebElement formSearch;

    @FindBy(css = "#criteria")
    private FluentWebElement criteriaSearch;

    @FindBy(css = "#search-button")
    private FluentWebElement submitSearch;

    @FindBy(css = "#table-list")
    private FluentWebElement tableList;

    @FindBy(css = "#result-102")
    private FluentWebElement result102;




    public LoanPage buttonLoan() {
        btnLoan.click();
        return this;
    }
    public LoanPage awaitUntilFormAppear() {
        await().atMost(10, TimeUnit.SECONDS).until(formSearch).present();
        return this;
    }

    public void isAtHome() {
        assertThat(window().title()).isEqualTo("Home");
    }


    public LoanPage inputItem(String searchPhrase) {
        criteriaSearch.write(searchPhrase);
        return this;
    }

    public LoanPage submitLoan() {
        submitSearch.submit();
        return this;
    }

    public LoanPage awaitUntilResult102Appear() {
        await().atMost(5, TimeUnit.SECONDS).until(result102).present();
        return this;
    }


    /*
    public DogPage typeName(String name) {
        nameInput.write(name);
        return this;
    }

    public DogPage submitSearchForm() {
        submitSearch.submit();
        awaitUntilSearchFormDisappear();
        return this;
    }



    public void assertIsPhrasePresentInTheResults() {
        assertThat(window().title()).contains("Home");
    }

    private DogPage awaitUntilSearchFormDisappear() {
        await().atMost(5, TimeUnit.SECONDS).until(submitSearch).not().present();
        return this;
    }


    public void isAtHome() {
        assertThat(window().title()).isEqualTo("Home");
    }

    public DogPage openFirst() {
        firstId.click();
        return this;
    }

    public DogPage openResult() {
        resultoIdTwo.click();
        return this;
    }

    public DogPage editDetail() {
        editDetail.click();
        return this;
    }

    public DogPage openAdvancedOptions() {
        advancedOptions.click();
        return this;
    }

    public DogPage deleteDog() {
        btnDelete.click();
        return this;
    }

    public DogPage openSearch() {
        btnSearch.click();
        return this;
    }

    public DogPage submitSearch() {
        submitSearch.click();
        return this;
    }

    public DogPage buttonAddDog() {
        btnAddDog.click();
        return this;
    }

    public DogPage selectGenderMale() {
        inputGenderMale.click();
        return this;
    }

    public DogPage awaitUntilFormDogAppear() {
        await().atMost(10, TimeUnit.SECONDS).until(formDetail).present();
        return this;
    }

    public DogPage awaitUntilButtonSaveAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(btnSave).present();
        return this;
    }

    public DogPage awaitUntilPageLoaded() {
        await().untilPage().isLoaded();
        return this;
    }

    public DogPage awaitUntilEditButtonAppear() {
        await().atMost(10, TimeUnit.SECONDS).until(editDetail).present();
        return this;
    }

    public DogPage selectConfirmSubmit() {
        btnSave.click();
        await().atMost(5, TimeUnit.SECONDS);//.until(editDetail).present();
        return this;

    }

    public void maxWindows(){
        getDriver().manage().window().maximize();
    }

  *//*  public DogPage awaitDogTwoIsNotPresent() {
        await().atMost(10, TimeUnit.SECONDS).until(resultoIdTwo).not().present();
        return this;
    }*//*


    public void isAtDetailDog() {
        assertThat(window().title()).isEqualTo("Detail");
    }


    public DogPage selectTermPaperForEvaluationLastProfessor() {
        //buttonTermPaperAvailableForEvaluationLastProfessor.click();
        return this;

    }

    public DogPage selectTermPaperForEvaluationLastProfessorTwo() {
        //buttonTermPaperAvailableForEvaluationNotLastProfessorTwo.click();
        return this;

    }

    public DogPage selectTermPaperForEvaluationNotLastProfessor() {
        //buttonTermPaperAvailableForEvaluationNotLastProfessor.click();
        return this;

    }

    public DogPage selectRadioEvaluateRedoAndSubmit() {
       // buttonRadioRedo.click();
        //buttonSubmitEvaluation.click();
        return this;

    }

    public DogPage selectProposalForEvaluationLastProfessor() {
        //buttonProposal.click();
        return this;

    }
    public DogPage awaitUntilTermPaperAppear() {
        //await().atMost(5, TimeUnit.SECONDS).until(termPaperDetail).present();
        return this;
    }



    public DogPage awaitUntilFormEvaluationAdviceAppear() {
        //await().atMost(5, TimeUnit.SECONDS).until(formEvaluationAdvice).present();
        return this;
    }
    public DogPage awaitUntilTableListEvaluateAppear() {
        //await().atMost(5, TimeUnit.SECONDS).until(tableListEvaluation).present();
        return this;
    }


    public DogPage awaitUntilAppraiserNameAppear() {
        //await().atMost(5, TimeUnit.SECONDS).until(appraiserName).text().startsWith("orientador");
        return this;
    }


    public DogPage awaitTwoSeconds() {
        //await().atMost(2, TimeUnit.SECONDS).until(considerations).present();
        //await().atMost(5, TimeUnit.SECONDS).until($("textarea")).size(1);
        return this;
    }

    public DogPage fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        //buttonSubmitEvaluation.click();
        return this;
    }

    public DogPage fillAndSubmitAdviceForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        //buttonSubmitEvaluation.click();
        return this;
    }

    public DogPage fillAndSubmitDraftForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        //buttonDraftSubmitEvaluation.click();
        return this;
    }

    public DogPage awaitUntilConfirmationModal() {
        //await().atMost(5, TimeUnit.SECONDS).until(evaluationSubmitConfirmationModal).present();
        return this;
    }

    public String getFileAbsolutePath(){
        File file = Paths.get(".", "src","main","resources","static","photos","face.jpg").normalize().toFile();

        return file.getAbsolutePath();
    }



    public void fillTextAreaForm(String... paramsOrdered) {
        $("textarea").fill().with(paramsOrdered);
    }

    */
    /*


    public DogPage openResult() {
        resultoIdTwo.click();
        return this;
    }

    public DogPage downloadFile() {
        downloadFile.click();
        return this;
    }



    public DogPage awaitUntilResultsOpen() {
        await().atMost(5, TimeUnit.SECONDS).until(firstResultDetails).present();
        return this;
    }*/

}