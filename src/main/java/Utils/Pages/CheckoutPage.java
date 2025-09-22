package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Utils.Driver.DriverManager.getDriver;
import static Utils.UIActions.ScreenShot.takeScreenShotAsFile;

public class CheckoutPage {

    //OBJECTS

    ElementActions action = new ElementActions();
    ProductsPage products = new ProductsPage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();

// LOCATORS
    By CheckOutText = By.xpath("//li[text()= \"Checkout\"]");
    By productName = By.cssSelector("a[href=\"/product_details/30\"]");
    By ProductQuantity = By.cssSelector("tr[id=\"product-30\"] button");
    By ProductPrice = By.cssSelector("tr[id=\"product-30\"] td:nth-child(3)");
    By ProductTotalPrice = By.cssSelector("tr[id=\"product-30\"] p[class=\"cart_total_price\"]");
    By ProductsTotalPrice =By.cssSelector("P[class=\"cart_total_price\"]");
    By placeOrderButton = By.cssSelector("a[href=\"/payment\"]");
    // Delivery Address
    By AddressFirstLastNameD = By.xpath("//ul[@id=\"address_delivery\"]//li[2]");
    By Address1Address2D = By.xpath("//ul[@id=\"address_delivery\"]//li[4]");
    By AddressCityStatePostcodeD = By.xpath("//ul[@id=\"address_delivery\"]//li[6]");
    By AddressCountryNameD = By.xpath("//ul[@id=\"address_delivery\"]//li[7]");
    By AddressPhoneNumberD = By.xpath("//ul[@id=\"address_delivery\"]//li[8]");
    // Billing Address
    By AddressFirstLastNameB = By.xpath("//ul[@id=\"address_invoice\"]//li[2]");
    By Address1Address2B = By.xpath("//ul[@id=\"address_invoice\"]//li[4]");
    By AddressCityStatePostcodeB = By.xpath("//ul[@id=\"address_invoice\"]//li[6]");
    By AddressCountryNameB = By.xpath("//ul[@id=\"address_invoice\"]//li[7]");
    By AddressPhoneNumberB = By.xpath("//ul[@id=\"address_invoice\"]//li[8]");

    By ReviewYourOrderTexT = By.xpath("//h2[contains(text(), \"Review\")]");
    // WebElement methods

    public WebElement getProductsTotalAmount(WebDriver driver){
       return action.getLastElement(driver,ProductsTotalPrice);
    }

    public WebElement getCheckoutText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(CheckOutText,driver,7);
    }

    public WebElement getReviewYourOrderTexT(WebDriver driver) {
        return action.explicitWaitWithByVisibility(ReviewYourOrderTexT,driver,7);
    }

    public WebElement getDeliveryAddressFirstLastNameText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressFirstLastNameD,driver,7);
    }

    public WebElement getDeliveryAddress1Address2Text(WebDriver driver) {
        return action.explicitWaitWithByVisibility(Address1Address2D,driver,7);
    }

    public WebElement getDeliveryAddressCityStatePostcodeText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressCityStatePostcodeD,driver,7);
    }

    public WebElement getDeliveryAddressCountryNameText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressCountryNameD,driver,7);
    }

    public WebElement getDeliveryAddressPhoneNumberText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressPhoneNumberD,driver,7);
    }

    public WebElement getBillingAddressFirstLastNameText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressFirstLastNameB,driver,7);
    }

    public WebElement getBillingAddress1Address2Text(WebDriver driver) {
        return action.explicitWaitWithByVisibility(Address1Address2B,driver,7);
    }

    public WebElement getBillingAddressCityStatePostcodeText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressCityStatePostcodeB,driver,7);
    }

    public WebElement getBillingAddressCountryNameText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressCountryNameB,driver,7);
    }

    public WebElement getBillingAddressPhoneNumberText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(AddressPhoneNumberB,driver,7);
    }

    public WebElement getCheckoutProductName(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productName,driver,7);
    }

    public WebElement getCheckoutProductQuantity(WebDriver driver) {
        return action.explicitWaitWithByVisibility(ProductQuantity,driver,7);
    }

    public WebElement getCheckoutProductPrice(WebDriver driver) {
        return action.explicitWaitWithByVisibility(ProductPrice,driver,7);
    }

    public WebElement getCheckoutProductTotalPrice(WebDriver driver) {
        return action.explicitWaitWithByVisibility(ProductTotalPrice,driver,7);
    }

    public WebElement getCheckoutProductsTotalPrice(WebDriver driver) {
      return action.explicitWaitWithWebElementVisibility(getProductsTotalAmount(driver),driver,7);
    }

    public WebElement clickOnPlaceOrderButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(placeOrderButton,driver,7);
    }

   // Steps Methods

    @Step("searched by {searchKey}, changed Quantity of product id 30 from 1 to : {quantity}")
    public void fromSearchToCheckout(WebDriver driver, String searchKey, String quantity) {
        action.ScrollingByElement(driver,products.searchButton(driver));
        products.searchForProductSteps(driver,searchKey);
        action.ScrollingByElement(driver,products.clickViewProductId30(driver));
        action.Click(products.clickViewProductId30(driver));
        product.changeProductQuantity(driver,quantity);
        takeScreenShotAsFile(getDriver(),"QuantityInProductPage");
        action.Click(product.addProductToCart(driver));
        action.Click(product.ViewCartButton(driver));
        takeScreenShotAsFile(getDriver(),"QuantityInCartPage");
        action.Click(cart.clickOnCheckoutButton(getDriver()));
    }


}
