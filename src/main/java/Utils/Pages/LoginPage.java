package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    // Objects
    ElementActions action = new ElementActions();

    // locators
     By loginEmail = By.cssSelector("div.login-form input[data-qa=\"login-email\"]"); // this locator  //input[contains(@data-qa,"login-email")]
     By loginPassword = By.name("password");
     By loginButton = By.cssSelector("div.login-form button[data-qa=\"login-button\"]");
     By loggedInAs = By.xpath("//a[text()=\" Logged in as \"]");
     By logoutButton = By.cssSelector("a[href=\"/logout\"]");
     By deleteAccountButton = By.cssSelector("a[href=\"/delete_account\"]");
     By LoginErrorMsg = By.cssSelector("p[style=\"color: red;\"]");

    // WebElement methods

    public WebElement getLoggedInAsText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(loggedInAs,driver,7);
    }

    public WebElement loginEmail(WebDriver driver) {
        return action.explicitWaitWithByClickable(loginEmail,driver,7);
    }

    public WebElement loginPassword(WebDriver driver) {
        return action.element(driver,loginPassword);
    }

    public WebElement loginButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(loginButton,driver,5);
    }

    public WebElement LoginUnsuccessfully(WebDriver driver) {
        return action.element(driver,LoginErrorMsg);
    }

  public WebElement getLogoutText (WebDriver driver){
        return action.element(driver,logoutButton);
  }

    public WebElement getDeleteAccountText (WebDriver driver){
        return action.element(driver,deleteAccountButton);
    }

    //Steps methods

    @Step("used email : {email}, password :{password} , to login ")
    public void loginSteps(WebDriver driver,String email,String password){
        action.SendKeys(loginEmail(driver),email);
        action.SendKeys(loginPassword(driver),password);
        action.Click(loginButton(driver));

    }

    @Step("clear login Credentials (email , password)")
    public void ClearLoginCredentials(WebDriver driver){
        action.Clear(loginEmail(driver));
        action.Clear(loginPassword(driver));
    }

}
