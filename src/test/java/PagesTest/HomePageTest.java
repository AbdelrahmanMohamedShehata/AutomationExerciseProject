package PagesTest;
import Assertions.Validation;
import Utils.UIActions.ElementActions;
import Utils.Pages.HomePage;
import Utils.Pojo.DataHelperMethods;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;

public class HomePageTest {

    HomePage home = new HomePage();
    ElementActions action = new ElementActions();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

     @BeforeMethod
    public void openBrowser(){
         createInstance(helperMethod.getBrowserNames(0).getBrowser());
         action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
     }

    @Test
    public void isAutomationExerciseDisplayed () {
    boolean display = action.isDisplayed(home.isAutomationExerciseImgDisplayed(getDriver()));
    validation.assertTrueBoolean(display,"error Msg : assert 1");
    }

    @Test
    public void verifyHomePage (){
        String expectedUrl = "https://www.automationexercise.com/";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error Msg : assert 1");

            String expectedResult = "Home";
            String actualResult = action.getText(home.getHomeText(getDriver()));
            validation.assertEqualsString(actualResult,expectedResult,"error Msg : assert 3");

            String expectedTitle = "Automation Exercise";
            String actualTitle = action.getTitle(getDriver());
            validation.assertEqualsString(actualTitle, expectedTitle,"error Msg : assert 4");

            validation.assertAll();
    }

    @Test
    public void checkHomeSubscribeEmail () {
        action.SendKeys(home.subscribeEmailHome(getDriver()), helperMethod.getLoginData(0).getEmail());
        action.Click(home.subscribeButtonHome(getDriver()));

        String expectedMessage = "You have been successfully subscribed!";
        String actualMessage = action.getText(home.subscribeSuccessfully(getDriver()));
        validation.assertEqualsString(actualMessage, expectedMessage,"error message : assert 1");

    }

    @Test
    public void checkBrokenLinks (){
        int ActualLinks = action.getBrokenUrl(getDriver(),home.getUrls(),"href");
        validation.assertEqualsInt(ActualLinks,0,"error message");
        validation.assertAll();
    }

    @Test
    public void getDynamicAutomationText ()  {
        String ExpectedText = "AutomationExercise";
        String ActualText = home.getAutomationExerciseText(getDriver()).split("Fu")[0].trim();
        validation.assertEqualsString(ActualText,ExpectedText,"error message");
        validation.assertAll();
    }

    @Test
    public void getRecommendedItemsText(){
        action.ScrollingByElement(getDriver(),home.getRecommendedItemsText(getDriver()));
        String expectedText = "RECOMMENDED ITEMS";
        String actualText= action.getText(home.getRecommendedItemsText(getDriver()));
        validation.assertEqualsString(actualText,expectedText,"error message : assert1");
        validation.assertAll();
    }
    @AfterMethod
    public void quitDriver(){
        tearDown();
    }

    }
