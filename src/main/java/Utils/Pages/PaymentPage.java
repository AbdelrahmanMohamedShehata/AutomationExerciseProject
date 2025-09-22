package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
 // OBJECTS
    ElementActions action = new ElementActions();
    ProductsPage products = new ProductsPage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();
    CheckoutPage checkout = new CheckoutPage();

    // LOCATORS

    By PaymentText = By.xpath("//li[@class=\"active\"]");
    By nameOnCard = By.cssSelector("input[data-qa=\"name-on-card\"]");
    By cardNumber = By.cssSelector("input[data-qa=\"card-number\"]");
    By CVC = By.cssSelector("input[data-qa=\"cvc\"]");
    By ExpirationMonth = By.cssSelector("input[data-qa=\"expiry-month\"]");
    By ExpirationYear = By.cssSelector("input[data-qa=\"expiry-year\"]");
    By payAndConfirmOrder = By.cssSelector("#submit");
    By successfulOrder = By.xpath("//div[@id=\"success_message\"]//div");
    By orderPlacedMessage = By.xpath("//h2[@data-qa=\"order-placed\"]//b");
    By orderConfirmation = By.xpath("//p[text()=\"Congratulations! Your order has been confirmed!\"]"); // //p[contains(text(),"Congratulations!")]
    By downloadFile = By.cssSelector("div.col-sm-9 a[href*=\"invoice\"]");
    By LogoutButton = By.cssSelector("a[href=\"/logout\"]");

    // WebElement methods

    public WebElement getPaymentText(WebDriver driver) {
        return action.explicitWaitWithByClickable(PaymentText,driver,7);
    }

    public WebElement nameOnCardField(WebDriver driver) {
        return action.explicitWaitWithByClickable(nameOnCard,driver,7);
    }

    public WebElement cardNumberField(WebDriver driver) {
        return action.explicitWaitWithByClickable(cardNumber,driver,7);
    }

    public WebElement CVCField(WebDriver driver) {
        return action.explicitWaitWithByClickable(CVC,driver,7);
    }

    public WebElement ExpirationMonthField(WebDriver driver) {
        return action.explicitWaitWithByClickable(ExpirationMonth,driver,7);

    }

    public WebElement ExpirationYearField(WebDriver driver) {
        return action.explicitWaitWithByClickable(ExpirationYear,driver,5);
    }

    public WebElement payAndConfirmOrderButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(payAndConfirmOrder,driver,5);
    }


    public WebElement successfulOrderMsg(WebDriver driver) {
        return action.explicitWaitWithByVisibility(successfulOrder,driver,5);
    }

    public WebElement orderPlacedMessage(WebDriver driver) {
        return action.explicitWaitWithByVisibility(orderPlacedMessage,driver,5);

    }

    public WebElement orderConfirmation(WebDriver driver) {
        return action.explicitWaitWithByVisibility(orderConfirmation,driver,5);
    }

    public WebElement clickOnLogoutButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(LogoutButton,driver,5);
    }

    public WebElement downloadInvoice(WebDriver driver) {
        return action.explicitWaitWithByClickable(downloadFile,driver,5);
    }

    //Steps Methods

    @Step("send paymentData cardName:{nameOnCard}, cardNumber:{cardNumber}," +
            " cvc:{CVC}, ExpirationMonth:{ExpirationMonth},ExpirationYear:{ExpirationYear}")
    public void PaymentSteps(WebDriver driver, String nameOnCard, String cardNumber,
                             String CVC, String ExpirationMonth, String ExpirationYear) {

        action.ScrollingByElement(driver,nameOnCardField(driver));
        action.SendKeys(nameOnCardField(driver), nameOnCard);
        action.SendKeys(cardNumberField(driver), cardNumber);
        action.SendKeys(CVCField(driver), CVC);
        action.SendKeys(ExpirationMonthField(driver), ExpirationMonth);
        action.SendKeys(ExpirationYearField(driver), ExpirationYear);
        action.ScrollingByElement(driver,payAndConfirmOrderButton(driver));
        action.Click(payAndConfirmOrderButton(driver));
    }

    @Step("searched by {searchKey}, changed product Quantity from 1 to : {quantity}")
    public void fromSearchToPayment(WebDriver driver,String searchKey,String quantity) {
        action.ScrollingByElement(driver,products.searchButton(driver));
        products.searchForProductSteps(driver,searchKey);
        action.ScrollingByElement(driver,products.clickViewProductId30(driver));
        action.Click(products.clickViewProductId30(driver));
        product.changeProductQuantity(driver,quantity);
        action.Click(product.addProductToCart(driver));
        action.Click(product.ViewCartButton(driver));
        action.Click(cart.clickOnCheckoutButton(driver));
        action.ScrollingByElement(driver,checkout.clickOnPlaceOrderButton(driver));
        action.Click(checkout.clickOnPlaceOrderButton(driver));
    }


}




















//        Actions actions = new Actions(driver);
//        actions.moveToElement(nameOnCardField(driver)).click().sendKeys(nameOnCard).
//                moveToElement(cardNumberField(driver)).click().sendKeys(nameOnCard).
//                moveToElement(CVCField(driver)).click().sendKeys(CVC).
//                moveToElement(ExpirationMonthField(driver)).click().sendKeys(ExpirationMonth).
//                moveToElement(ExpirationYearField(driver)).click().sendKeys(ExpirationYear).
//                build().perform();