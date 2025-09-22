package PagesTest;
import Assertions.Validation;
import Utils.UIActions.ElementActions;
import Utils.Pages.HomePage;
import Utils.Pages.SignupInfoPage;
import Utils.Pages.SignupPage;
import Utils.Pojo.DataHelperMethods;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;

public class SignupInfoPageTest {

    HomePage home = new HomePage();
    SignupPage signup = new SignupPage();
    ElementActions action = new ElementActions();
    SignupInfoPage signupInfo = new SignupInfoPage();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod
    public void openBrowser() {
        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
        action.Click(home.navigateToLoginPage(getDriver()));
        signup.signupSteps(getDriver(),helperMethod.getSignupData(1).getName(),
                helperMethod.getSignupData(1).getEmail());

    }

    @Test
    public void signupWithOnlyMandatoryWthDeletingAccount(){

        signupInfo.Mandatorysignupsteps(getDriver(),helperMethod.getSignupInfoData(0).getPassword(),
        helperMethod.getSignupInfoData(0).getFirstName(),helperMethod.getSignupInfoData(0).getLastName(),
        helperMethod.getSignupInfoData(0).getAddress(), helperMethod.getSignupInfoData(0).getCountry(),
        helperMethod.getSignupInfoData(0).getState(),helperMethod.getSignupInfoData(0).getCity(),
        helperMethod.getSignupInfoData(0).getZipCode(),helperMethod.getSignupInfoData(0).getMobileNumber());

        String ExpectedUrl = "https://www.automationexercise.com/account_created";
        String ActualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(ActualUrl, ExpectedUrl,"error Message : Assert 1");

        String expectedResult = "ACCOUNT CREATED!";
        String actualResult = action.getText(signupInfo.getAccountCreatedText(getDriver()));
        validation.assertEqualsString(actualResult,expectedResult,"error Message : Assert 2");

        String expectedOutput = "Congratulations!";
        String actualOutput = action.getText(signupInfo.getAccountCreatedMsg(getDriver()));
        validation.assertTrueString(actualOutput,expectedOutput,"error Message : Assert 3");

        action.Click(signupInfo.clickContinueButton(getDriver()));
        action.Click(signupInfo.clickDeleteAccountButton(getDriver()));

        String expectedUrl = "https://www.automationexercise.com/delete_account";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl, expectedUrl,"error Message : Assert 4");

        String ExpectedResult = "ACCOUNT DELETED!";
        String ActualResult = action.getText(signupInfo.getAccountDeletedText(getDriver()));
        validation.assertEqualsString(ActualResult,ExpectedResult,"error Message : Assert 5");

        String ExpectedOutput = "Your account has been permanently deleted!";
        String ActualOutput = action.getText(signupInfo.getAccountDeletedMsg(getDriver()));
        validation.assertTrueString(ActualOutput, ExpectedOutput,"error Message : Assert 6");

        String Url = helperMethod.getUrlData(0).getHomeUrl();
        action.Click(signupInfo.clickContinueButton(getDriver()));
        String URL = action.getCurrentUrl(getDriver());
        validation.assertTrueString(URL, Url,"error Message: Assert 7");
        validation.assertAll();
    }


    @AfterMethod
    public void quitDriver(){
        tearDown();
    }

}
