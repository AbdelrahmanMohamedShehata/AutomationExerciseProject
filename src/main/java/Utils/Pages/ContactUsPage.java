package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage {

    // OBJECTS
    ElementActions action = new ElementActions();

    // LOCATORS

    By contactUsText = By.xpath("//h2[text()=\"Contact \"]");
    By contactUsName = By.cssSelector("input[data-qa=\"name\"]");
    By contactUsEmail = By.cssSelector("input[data-qa=\"email\"]");
    By contactUsSubject = By.cssSelector("input[data-qa=\"subject\"]");
    By contactUsMessage = By.id("message");
    By uploadFile = By.name("upload_file");
    By submitButton = By.name("submit");
    By successMsg = By.cssSelector("div[class=\"status alert alert-success\"]");
    By successHomeButton = By.cssSelector("a[class=\"btn btn-success\"]");

    // WebElement methods

    public WebElement getContactUsText(WebDriver driver) {
        return action.element(driver,contactUsText);
    }

    public WebElement contactUsNameField(WebDriver driver) {
        return action.element(driver,contactUsName);
    }

    public WebElement contactUsSubjectField(WebDriver driver) {
        return action.element(driver,contactUsSubject);
    }

    public WebElement contactUsEmailField(WebDriver driver) {
        return action.explicitWaitWithByClickable(contactUsEmail,driver,5);
    }

    public WebElement contactUsMessageField(WebDriver driver) {
        return action.element(driver,contactUsMessage);
    }

    public WebElement uploadFileField(WebDriver driver) {
        return action.element(driver,uploadFile);
    }

    public WebElement clickOnSubmitButton(WebDriver driver) {
        return action.element(driver,submitButton);
    }

    public WebElement getContactUsSuccessMsg(WebDriver driver) {
        return action.explicitWaitWithByVisibility(successMsg,driver,7);
    }

    public WebElement successHomeButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(successHomeButton,driver,5);

    }

    // Steps Methods

    @Step("send contactUsData >> Email:{email}, Name:{Name}," +
            " subject :{subject}, message:{message},filePath:{path}")
    public void contactUsSteps(WebDriver driver,String Name,String email,String subject,String message,String path){
        action.SendKeys(contactUsNameField(driver),Name);
        action.SendKeys(contactUsEmailField(driver),email);
        action.SendKeys(contactUsSubjectField(driver),subject);
        action.SendKeys(contactUsMessageField(driver),message);
        action.SendKeys(uploadFileField(driver),path);
        action.Click(clickOnSubmitButton(driver));
    }



}
