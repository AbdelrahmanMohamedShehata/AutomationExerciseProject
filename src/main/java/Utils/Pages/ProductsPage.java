package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

    // objects
    ElementActions action = new ElementActions();

    // locators
    By productsButton = By.cssSelector("ul.nav a[href=\"/products\"]");  // this one //a[contains(@href,"/products")]
    By allProductsText = By.cssSelector("h2.title");
    By searchedProductsTXt = By.cssSelector("h2.title");
    By categoryText = By.xpath("//h2[text()=\"Category\"]");
    By brandText = By.xpath("//h2[text()=\"Brands\"]");
    By allProductsItems = By.cssSelector("div.features_items div.col-sm-4"); // div[class*="features"] div[class*="col"]
   // By allSearchedProductsItems = By.xpath("div[class*="features"] div[class*="col"]");
    By searchField = By.cssSelector("#search_product");
    By searchButton = By.cssSelector("#submit_search");
    By productPrice30 = By.xpath("//img[contains(@src,\"30\")]//following::h2[1]");
    By productName30 = By.xpath("//img[contains(@src,\"30\")]//following::p[1]");
    By productPrice29 = By.xpath("//img[contains(@src,\"29\")]//following::h2[1]");
    By productName29 = By.xpath("//img[contains(@src,\"29\")]//following::p[1]");
    By viewProductButton30 = By.cssSelector("div.choose a[href*=\"30\"]"); // this
    By viewProductButton29 = By.cssSelector("div.choose a[href*=\"29\"]");
    By automationExerciseImage = By.cssSelector("img[src=\"/static/images/home/logo.png\"]");
    By specialOfferImage = By.id("sale_image");
    By addToCart29 = By.cssSelector("a[data-product-id=\"29\"]");
    By addToCart30 = By.cssSelector("a[data-product-id=\"30\"]"); // //img[contains(@src,"30")]
    By productAddedToCartMsg = By.xpath("//p[contains(text(),\"Your product\")]");
    By viewCart = By.cssSelector("p a[href=\"/view_cart\"]");
    By continueShopping = By.cssSelector("button[data-dismiss=\"modal\"]");
    By ProductsImgUrl= By.cssSelector("div[class*=\"productinfo\"] img");


    // WebElement methods

    public WebElement ProductsButton(WebDriver driver) {
        return action.explicitWaitWithByClickable(productsButton,driver,5);
    }

    public WebElement getAllProductsText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(allProductsText,driver,7);
    }

    public WebElement getSearchedProductsText(WebDriver driver) {
        return action.explicitWaitWithByVisibility(searchedProductsTXt,driver,7);
    }

    public WebElement getCategoryText(WebDriver driver) {
        return action.element(driver,categoryText);
    }

    public WebElement getBrandText(WebDriver driver) {
        return action.element(driver,brandText);
    }

    public WebElement isAutomationExerciseImgDisplayed(WebDriver driver) {
        return action.element(driver,automationExerciseImage);
    }

    public WebElement isSpecialOfferImgDisplayed(WebDriver driver) {
        return action.element(driver,specialOfferImage);
    }


    public WebElement searchField(WebDriver driver) {
        return action.explicitWaitWithByClickable(searchField,driver,7);
    }

    public WebElement searchButton(WebDriver driver) {
        return action.element(driver,searchButton);
    }

    public int getAllSearchedProductsSize(WebDriver driver) {
       return action.getElementsSize(driver,allProductsItems);
    }

    public int getAllProductsSize(WebDriver driver) {
        return action.getElementsSize(driver,allProductsItems);
    }

    public WebElement priceOfProductId30(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productPrice30,driver,7);
    }

    public WebElement nameOfProductId30(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productName30,driver,7);
    }

    public WebElement priceOfProductId29(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productPrice29,driver,7);
    }

    public WebElement nameOfProductId29(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productName29,driver,7);
    }


    public WebElement clickViewProductId30(WebDriver driver) {
        return action.explicitWaitWithByClickable(viewProductButton30,driver,5);
    }

    public WebElement clickViewProductId29(WebDriver driver) {
        return action.explicitWaitWithByClickable(viewProductButton29,driver,5);
    }


    public void clickAddToCartId30(WebDriver driver) {
        WebElement element = action.explicitWaitWithByClickable(addToCart30,driver,5);
        action.clickByJE(driver, element);
    }

    public void clickAddToCart29(WebDriver driver) {

        WebElement element = action.explicitWaitWithByClickable(addToCart29,driver,5);
        action.clickByJE(driver, element);
    }

    public WebElement clickViewCart(WebDriver driver){
        return action.explicitWaitWithByClickable(viewCart,driver,5);
    }

    public WebElement clickContinueShopping(WebDriver driver){
        return action.explicitWaitWithByClickable(viewCart,driver,5);
    }

    public WebElement getProductAddedToCartMsg(WebDriver driver) {
        return action.explicitWaitWithByVisibility(productAddedToCartMsg,driver,7);
    }

    // By methods

    public By getImgUrls(){
        return ProductsImgUrl;
    }

    // steps methods

    @Step("search in search field by search key :{search key}")
    public void searchForProductSteps(WebDriver driver,String searchKey){
        action.SendKeys(searchField(driver),searchKey);
        action.Click(searchButton(driver));
    }

}
