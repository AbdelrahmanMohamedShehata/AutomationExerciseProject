package PagesTest;
import Assertions.Validation;
import Utils.Pages.HomePage;
import Utils.Pages.LoginPage;
import Utils.UIActions.ElementActions;
import Utils.Pojo.DataHelperMethods;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;

public class LoginPageTest {

    HomePage home = new HomePage();
    LoginPage loginPage = new LoginPage();
    ElementActions action = new ElementActions() ;
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod
    public void openBrowser(){
        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
        action.Click(home.navigateToLoginPage(getDriver()));
    }

    @Test
    public void LoginWithRegisteredEmail(){
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());
        String expectedResult = "Logout";
        String actualResult = action.getText(loginPage.getLogoutText(getDriver()));
        validation.assertEqualsString(actualResult,expectedResult,"error message : assert 1");

        String expectedOutput = "Delete Account";
        String actualOutput = action.getText(loginPage.getDeleteAccountText(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 2");

        String expectedText = "Logged in as";
        String actualText = action.getText(loginPage.getLoggedInAsText(getDriver()));
        validation.assertTrueString(actualText,expectedText,"error message : assert 3");

        validation.assertAll();
    }

    @Test
    public void LoginWithUnRegisteredEmailAndWrongEmails() {
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(1).getEmail(),
                helperMethod.getLoginData(1).getPassword());
        String expectedResult = "Your email or password is incorrect!";
        String actualResult = action.getText(loginPage.LoginUnsuccessfully(getDriver()));
        validation.assertEqualsString(actualResult, expectedResult, "error message : assert 1");
        loginPage.ClearLoginCredentials(getDriver());
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(2).getWrongEmail(),
                helperMethod.getLoginData(2).getPassword());
        validation.assertEqualsString(actualResult, expectedResult, "error message : assert 2");
        loginPage.ClearLoginCredentials(getDriver());
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(3).getWrongEmail(),
                helperMethod.getLoginData(3).getPassword());
        validation.assertEqualsString(actualResult, expectedResult, "error message : assert 3");
    }

    @Test
    public void loginWithEmailWithMistakes(){
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(4).getEmailWithMistakes(),
                helperMethod.getLoginData(4).getPassword());
        String expectedResult = "Please include an '@' in the email address";
        String actualResult = action.getTextByJSExecutor(getDriver(),loginPage.loginEmail(getDriver())).split("\\.")[0];
        validation.assertEqualsString(actualResult, expectedResult, "error message : assert 1");
        loginPage.ClearLoginCredentials(getDriver());
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(5).getEmailWithMistakes(),
                helperMethod.getLoginData(5).getPassword());
        if (action.getCurrentUrl(getDriver()).equals(helperMethod.getUrlData(0).getHomeUrl())){
        validation.assertFail("The user logged in successfully with invalid email so this is a bug");}
        else {
            System.out.println("The user failed to login successfully");
        }
        validation.assertAll();
    }

        @AfterMethod
    public void quitDriver(){
        tearDown();
    }

}
