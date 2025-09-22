package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductReview {

    //OBJECTS
    ElementActions action = new ElementActions();

    // LOCATORS

    By reviewName = By.id("name");
    By reviewEmail = By.id("email");
    By addReview = By.id("review");
    By submitReviewButton = By.id("button-review");
    By ReviewSuccessMsg= By.xpath("//span[text()=\"Thank you for your review.\"]");
    By WriteReviewTXT = By.cssSelector("a[href*=\"reviews\"]");

    // WebElement methods

    public WebElement addReviewName(WebDriver driver) {
        return action.element(driver,reviewName);
    }

    public WebElement addReviewEmail(WebDriver driver) {
        return action.element(driver,reviewEmail);
    }

    public WebElement addReview(WebDriver driver) {
        return action.element(driver,addReview);
    }

    public WebElement submitReviewButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(submitReviewButton,driver,5);
    }

    public WebElement getReviewSuccessMsg(WebDriver driver) {
        return action.element(driver,ReviewSuccessMsg);
    }

    public WebElement getWriteYourReviewText(WebDriver driver){return action.element(driver,WriteReviewTXT);}


    //Steps Methods

    @Step("send reviewData name:{reviewName}, email:{reviewEmail}," +
            " addReview :{reviewMsg}")
    public void SendReviewSteps(WebDriver driver,String reviewName,String reviewEmail,String reviewMsg){
        action.SendKeys(addReviewName(driver),reviewName);
        action.SendKeys(addReviewEmail(driver),reviewEmail);
        action.SendKeys(addReview(driver),reviewMsg);
        action.Click(submitReviewButton(driver));
    }





}
