package Utils.Pages;
import Utils.UIActions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    // objects

    ElementActions action = new ElementActions();

    // locators

    By loginSignupButton = By.cssSelector("ul.nav.navbar-nav a[href=\"/login\"]"); // this locator //a[contains(@href,"login")]
    By HomeText = By.xpath("//a[contains(text(),\"Home\")]");
    By AutomationPracticeText = By.cssSelector("img[src*=\"logo.png\"]");
    By AutomationExerciseText = By.cssSelector("div.item.active"); // //h1[contains(text(),"Exercise")]
    By subscribeEmail = By.id("susbscribe_email");
    By subscribeButton = By.id("subscribe");
    By successfulSubscriptionMsg = By.cssSelector("div[class*=\"success alert\"]");
    By automationExerciseImage = By.cssSelector("img[src*=\"logo.png\"]");
    By contactUsButton = By.cssSelector("ul.nav.navbar-nav a[href=\"/contact_us\"]");
    By recommendedItems = By.xpath("//h2[contains(text(),\"recommended\")]");
    By pagesURLS = By.cssSelector("ul[class*=\"navbar\"] a");

    // WebElement methods

    public WebElement navigateToContactUsPage(WebDriver driver) {

        return action.explicitWaitWithByClickable(contactUsButton,driver,5);
    }

    public WebElement isAutomationExerciseImgDisplayed(WebDriver driver) {
        return action.element(driver,automationExerciseImage);
    }

    public WebElement getHomeText(WebDriver driver){
        return action.element(driver,HomeText);
    }

    public WebElement getAutomationPracticeText(WebDriver driver){
        return action.element(driver,AutomationPracticeText);
    }


    public String getAutomationExerciseText(WebDriver driver){
        WebElement element;
          element = action.getElementFromList(driver,AutomationExerciseText);
        return action.getText(element);
    }

    public WebElement navigateToLoginPage(WebDriver driver){
        return action.explicitWaitWithByClickable(loginSignupButton,driver,7);
    }

    public WebElement subscribeEmailHome(WebDriver driver){
        return action.explicitWaitWithByClickable(subscribeEmail,driver,7);
    }

    public WebElement subscribeButtonHome(WebDriver driver){
        return action.explicitWaitWithByClickable(subscribeButton,driver,7);
    }

    public WebElement subscribeSuccessfully(WebDriver driver){
        return action.explicitWaitWithByVisibility(successfulSubscriptionMsg,driver,5);
    }

    public WebElement getRecommendedItemsText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(recommendedItems,driver,7);
    }

    // By methods

    public By getUrls(){
        return pagesURLS;
    }

}
