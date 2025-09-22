package Utils.Pages;

import Utils.UIActions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    //OBJECTS

    ElementActions action = new ElementActions();

    // LOCATORS
    By cartButton = By.cssSelector("a[href=\"/view_cart\"]");
    By shoppingCartText = By.cssSelector("li[class=\"active\"]");
    By productName = By.cssSelector("a[href=\"/product_details/30\"]");
    By cartQuantity = By.cssSelector("tr#product-30 button");  // //tr[@id="product-30"]//button
    By cartPrice = By.cssSelector("tr#product-30 td.cart_price"); // //tr[@id="product-30"]//td[@class="cart_price"]
    By cartTotalPrice = By.cssSelector("tr#product-30 p.cart_total_price"); // //tr[@id="product-30"]//p[@class="cart_total_price"]
    By proceedToCheckoutButton = By.cssSelector("div.col-sm-6 a.btn"); //   a[class*="check_out"]
    By deleteItemFromCartGenerally = By.cssSelector("a.cart_quantity_delete");// delete cart generally or   //a[contains(@class,"delete")]
    By deleteProduct30FromCart=By.cssSelector("tr#product-30 a[data-product-id=\"30\"]");
    By deleteProduct29FromCart=By.cssSelector("tr#product-29 a[data-product-id=\"29\"]");
    By cartIsEmptyMsg = By.xpath("//b[contains(text(),\"empty\")]");
    By LoginRegisterButton = By.cssSelector("P.text-center a[href=\"/login\"]");
    By LoginMsgToContinueYouPaymentProcess = By.xpath("//p[contains(text(),\"Register / Login\")]"); // //p[text()= "Register / Login account to proceed on checkout."]
    By hereButton = By.cssSelector("span#empty_cart a[href=\"/products\"]");

    // WebElement methods

    public WebElement clickCartButton(WebDriver driver){
        return action.explicitWaitWithByClickable(cartButton,driver,5);
    }

    public WebElement getShoppingCartText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(shoppingCartText,driver,7);
    }

    public WebElement getCartProductName(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productName,driver,7);
    }

    public WebElement getCartProductQuantity(WebDriver driver) {
        return action.explicitWaitWithByVisibility(cartQuantity,driver,7);
    }

    public WebElement getCartProductPrice(WebDriver driver) {
        return action.explicitWaitWithByVisibility(cartPrice,driver,7);
    }

    public WebElement getCartProductTotalPrice(WebDriver driver) {
        return action.explicitWaitWithByVisibility(cartTotalPrice,driver,7);
    }

    public WebElement clickOnCheckoutButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(proceedToCheckoutButton,driver,7);
    }

    public WebElement removeProductFromCart(WebDriver driver) {
        return action.explicitWaitWithByClickable(deleteItemFromCartGenerally,driver,7);
    }

    public WebElement removeProduct30FromCart(WebDriver driver) {
        return action.explicitWaitWithByClickable(deleteProduct30FromCart,driver,7);
    }

    public WebElement removeProduct29FromCart(WebDriver driver) {
        return action.explicitWaitWithByClickable(deleteProduct29FromCart,driver,7);
    }

    public WebElement clickHere(WebDriver driver) {
        return action.explicitWaitWithByClickable(hereButton,driver,7);
    }

    public WebElement cartIsEmptyMsg (WebDriver driver) {
        return action.explicitWaitWithByVisibility(cartIsEmptyMsg,driver,7);
    }

    public WebElement getLoginMsgToContinuePaymentProcess (WebDriver driver) {
        return action.explicitWaitWithByVisibility(LoginMsgToContinueYouPaymentProcess,driver,7);
    }

    public WebElement clickOnLoginRegisterButtonInCartPage(WebDriver driver) {
      return action.explicitWaitWithByClickable(LoginRegisterButton,driver,5);
    }

    }
