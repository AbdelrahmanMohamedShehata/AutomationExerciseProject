package PagesTest;
import Assertions.Validation;
import Utils.Pages.ContactUsPage;
import Utils.UIActions.ElementActions;
import Utils.Pages.HomePage;
import Utils.Pojo.DataHelperMethods;
import org.testng.annotations.*;

import static Utils.Driver.DriverManager.*;

public class ContactUsPageTest {

    HomePage home = new HomePage();
    ContactUsPage contactUsPage = new ContactUsPage();
    ElementActions action = new ElementActions();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod
    public void openBrowser(){
        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
        action.Click(home.navigateToContactUsPage(getDriver()));
    }


    @Test
    public void validateNavigationToContactusPage() {

        String expectedUrl = "https://www.automationexercise.com/contact_us";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");

        String expectedOutput = "CONTACT US";
        String actualOutput = action.getText(contactUsPage.getContactUsText(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 1");
        validation.assertAll();
    }

    @Test
    public void sendContactUsDetailsSuccessfully() {
       String path = helperMethod.getContactusData(0).getFilePath().toString();

        contactUsPage.contactUsSteps(getDriver(),helperMethod.getContactusData(0).getName(),helperMethod.getContactusData(0).getEmail(),
                helperMethod.getContactusData(0).getSubject(),helperMethod.getContactusData(0).getMessage(),
                helperMethod.getAbsolutePathFromJson(path));
        getDriver().switchTo().alert().accept();
        boolean enable = action.isEnabled(contactUsPage.successHomeButton(getDriver()));
        validation.assertTrueBoolean(enable,"error message : assert 1");
        String expectedOutput = "Your details have been submitted";
        String actualOutput = action.getText(contactUsPage.getContactUsSuccessMsg(getDriver()));
        validation.assertTrueString(actualOutput,expectedOutput,"error message : assert 1");
        validation.assertAll();
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }

}
