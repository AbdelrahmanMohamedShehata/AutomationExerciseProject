package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

    //OBJECTS
    ElementActions action = new ElementActions();

    // LOCATORS

    By productName = By.cssSelector("div[class*=\"product-information\"] h2");
    By productPrice = By.xpath("//span[text()='Rs. 1500']");
    By ProductImg = By.xpath("//div[@class=\"view-product\"]//img[@alt=\"ecommerce website products\"]");
    By ratingImg =By.cssSelector("img[src=\"/static/images/product-details/rating.png\"]");
    By CategoryName = By.xpath("//div[@class=\"product-information\"]//p[1]");
    By quantity = By.id("quantity");
    By availability = By.xpath("//p[text()=' In Stock']");
    By condition = By.xpath("//p[text()=' New']");
    By brandName = By.xpath("//p[text()=' Polo']");
    By addToCartButton = By.cssSelector("button[type=\"button\"]");
    By addedToCartMessage = By.xpath("//p[@class=\"text-center\"][1]");
    By viewCartButtonHidden = By.cssSelector("div.modal-body a[href*=\"cart\"]");
    By CartButton = By.cssSelector("ul.nav a[href*=\"cart\"]"); //  //ul//a[@href="/view_cart"]
    By continueShopping =By.cssSelector("button[data-dismiss=\"modal\"]");  // div.modal-footer button[data-dismiss="modal"]
    // WebElement methods

    public WebElement ProductName(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productName,driver,7);
    }

    public WebElement ProductPrice(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productPrice,driver,7);
    }

    public WebElement ProductQuantity(WebDriver driver) {
        return action.explicitWaitWithByVisibility(quantity,driver,7);
    }

    public WebElement ProductAvailability(WebDriver driver) {
        return action.explicitWaitWithByVisibility(availability,driver,7);
    }

    public WebElement ProductCondition(WebDriver driver) {
        return action.explicitWaitWithByVisibility(condition,driver,7);
    }

    public WebElement ProductImg(WebDriver driver) {
        return action.element(driver,ProductImg);
    }

    public WebElement RatingImg(WebDriver driver) {
        return action.element(driver,ratingImg);
    }

    public WebElement getCategoryName(WebDriver driver) {
        return action.element(driver,CategoryName);
    }

    public WebElement ProductBrandName(WebDriver driver) {
        return action.element(driver,brandName);
    }

    public WebElement addProductToCart(WebDriver driver) {
        return action.explicitWaitWithByClickable(addToCartButton,driver,5);
    }

    public WebElement addedProductToCartConfirmationMsg(WebDriver driver) {
        return action.explicitWaitWithByVisibility(addedToCartMessage,driver,7);
    }

    public WebElement ViewCartButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(viewCartButtonHidden,driver,5);
    }

    public WebElement continueShoppingButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(continueShopping,driver,5);
    }


    public WebElement clickOnCartButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(CartButton,driver,5);
    }

    // steps methods

    @Step("Change product Quantity from 1 to {quantity}")
    public void changeProductQuantity(WebDriver driver ,String quantity){
        action.Clear(ProductQuantity(driver));
        action.SendKeys(ProductQuantity(driver),quantity);
    }


}
