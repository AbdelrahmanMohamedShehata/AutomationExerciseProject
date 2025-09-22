package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

    public class SignupPage {

        //Objects

        ElementActions action = new ElementActions();

        // Locators

        By newUserSignupText = By.xpath("//div[@class=\"signup-form\"]//h2");
        By signupButton = By.cssSelector("div.signup-form button[data-qa*=\"signup\"]");
        By SignupName = By.cssSelector("input[placeholder=\"Name\"]");
        By signupEmail = By.cssSelector("div.signup-form input[data-qa=\"signup-email\"]");
        By EmailExistedMsg = By.xpath("//p[contains(@style,\"red\")]");
        By accountInformationText = By.xpath("//b[text()=\"Enter Account Information\"]");

        // WebElement methods

        public WebElement getSignupText(WebDriver driver){
            return action.element(driver,newUserSignupText);
        }

        public WebElement clickSignupButton(WebDriver driver){
            return action.explicitWaitWithByClickable(signupButton,driver,5);
        }

        public WebElement NameField(WebDriver driver){
            return action.explicitWaitWithByClickable(SignupName,driver,7);
        }

        public WebElement EmailField(WebDriver driver){
            return action.explicitWaitWithByClickable(signupEmail,driver,7);
        }


        public WebElement getEmailExistedMsg(WebDriver driver){
            return action.element(driver,EmailExistedMsg);
        }

        public WebElement getAccountInformationText(WebDriver driver){
            return action.explicitWaitWithByVisibility(accountInformationText,driver,7);
        }

        // Steps methods
        @Step("used name : {name}, email :{email}  , to signup ")
        public void signupSteps(WebDriver driver, String name , String email){
            action.SendKeys(NameField(driver),name);
            action.SendKeys(EmailField(driver),email);
            action.Click(clickSignupButton(driver));
        }

        public void clearSignupData(WebDriver driver){
            action.Clear(NameField(driver));
            action.Clear(EmailField(driver));
        }


    }

