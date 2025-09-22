package PagesTest;
import Assertions.Validation;
import Utils.Data.Signup;
import Utils.Pages.HomePage;
import Utils.Pages.SignupPage;
import Utils.Pojo.DataHelperMethods;
import Utils.Pojo.JsonReader;
import Utils.UIActions.ElementActions;
import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static Utils.Driver.DriverManager.*;

public class SignupPageTest {

    HomePage home = new HomePage();
    SignupPage signup = new SignupPage();
    ElementActions action = new ElementActions();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod
    public void openBrowser() {
        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(), helperMethod.getUrlData(0).getHomeUrl());
        action.Click(home.navigateToLoginPage(getDriver()));
    }


    @Test
    public void visibleSignup() {
        String ExpectedResult = "New User Signup!";
        String ActualResult = action.getText(signup.getSignupText(getDriver()));
        validation.assertEqualsString(ActualResult, ExpectedResult, "error message : verify signup Assert 1");
    }

    @Test
    public void signupWithUnRegisteredUser() {
        signup.signupSteps(getDriver(), helperMethod.getSignupData(1).getName(),
                helperMethod.getSignupData(1).getEmail());
        String ExpectedUrl = helperMethod.getUrlData(1).getSignupInfoUrl();
        String ActualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(ActualUrl, ExpectedUrl, "error : assert 1");
        String expectedResult = "ENTER ACCOUNT INFORMATION";
        String actualResult = action.getText(signup.getAccountInformationText(getDriver()));
        validation.assertEqualsString(actualResult, expectedResult, "error : assert 2");
        // validation.assertAll();
    }

    @Test
    public void SignupWithRegisteredUser() {
        signup.signupSteps(getDriver(), helperMethod.getSignupData(0).getName(),
                helperMethod.getSignupData(0).getEmail());
        String ExpectedResult = "Email Address already exist!";
        String ActualResult = action.getText(signup.getEmailExistedMsg(getDriver()));
        validation.assertEqualsString(ActualResult, ExpectedResult, "error message : assert 1");
    }


    @Test
    public void SignupWithWrongEmail() {

        for (int x = 2; x < 4; x++) {
            signup.signupSteps(getDriver(), helperMethod.getAllSignupData().get(x).getName(),
                    helperMethod.getAllSignupData().get(x).getWrongEmail());
            if (action.getCurrentUrl(getDriver()).equals(helperMethod.getUrlData(1).getSignupInfoUrl())) {
                validation.assertFail("The user signed up successfully with invalid email so this is a bug, data in index: "+ x);
            } else {
                System.out.println("the user failed to sign up with this email");
            }
            action.returnBack(getDriver());
            signup.clearSignupData(getDriver());
        }
        validation.assertAll();
    }

    @Test
    public void SignupWithEmailWithMistakes() {
        signup.signupSteps(getDriver(), helperMethod.getSignupData(4).getName(),
                helperMethod.getSignupData(4).getEmailWithMistakes());
        String expectedResult = "Please include an '@' in the email address";
        String actualResult = action.getTextByJSExecutor(getDriver(),signup.EmailField(getDriver())).split("\\.")[0];
        validation.assertEqualsString(actualResult, expectedResult, "error Message : assert 1");
        signup.clearSignupData(getDriver());
        signup.signupSteps(getDriver(),helperMethod.getSignupData(5).getName(),
                helperMethod.getSignupData(5).getEmailWithMistakes());
        if (action.getCurrentUrl(getDriver()).equals(helperMethod.getUrlData(1).getSignupInfoUrl())) {
            validation.assertFail("The user signed up successfully with invalid email so this is a bug");
        } else {
            System.out.println("the user failed to sign up with this email");
        }
      validation.assertAll();

    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }
}
