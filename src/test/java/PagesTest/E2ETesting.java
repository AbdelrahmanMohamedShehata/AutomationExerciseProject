package PagesTest;
import Assertions.Validation;
import Utils.Pages.*;
import Utils.Pojo.DataHelperMethods;
import Utils.UIActions.ElementActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static Utils.Driver.DriverManager.*;

public class E2ETesting {

    ElementActions action = new ElementActions();
    Validation validation = new Validation();
    ProductsPage products = new ProductsPage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();
    LoginPage loginPage = new LoginPage();
    CheckoutPage checkout = new CheckoutPage();
    PaymentPage payment = new PaymentPage();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeClass
    public void openBrowser(){

        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
    }


    
    @Test
    public void verifyHomePage (){
        String expectedUrl = "https://www.automationexercise.com/";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");

        String expectedTitle = "Automation Exercise";
        String actualTitle = action.getTitle(getDriver());
        validation.assertEqualsString(actualTitle,expectedTitle,"error message : assert 2");

        validation.assertAll();

    }

    @Test (dependsOnMethods = "verifyHomePage")
    public void validateNavigationToProductsPage() {
        action.Click(products.ProductsButton(getDriver()));
        String expectedUrl = "https://www.automationexercise.com/products";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");

        String expectedOutput = "ALL PRODUCTS";
        String actualOutput = action.getText(products.getAllProductsText(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 2");
        validation.assertAll();
    }

    @Test (dependsOnMethods = "validateNavigationToProductsPage")
    public void navigateToProductPage() {
        action.Click(products.clickViewProductId30(getDriver()));
        String expectedUrl = "https://www.automationexercise.com/product_details/30";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");
    }

    @Test (dependsOnMethods = "navigateToProductPage")
    public void AddedProductToCartSuccessfully (){
        String expectedName = "Your product has been added to cart.";
        product.changeProductQuantity(getDriver(),helperMethod.getProductData(0).getQuantity());
        action.Click(product.addProductToCart(getDriver()));
        String actualName = action.getText(product.addedProductToCartConfirmationMsg(getDriver()));
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1 add product to cart Successfully");
        validation.assertAll();

    }

    @Test (dependsOnMethods = "AddedProductToCartSuccessfully")
    public void navigateTOViewCartPage (){
        String expectedUrl ="https://www.automationexercise.com/view_cart";
        action.Click(product.ViewCartButton(getDriver()));
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");
    }



    @Test (dependsOnMethods = "navigateTOViewCartPage")
    public void validateShoppingCartPage (){
        String expectedName = "Shopping Cart";
        String actualName = action.getText(cart.getShoppingCartText(getDriver()));
        validation.assertTrueString(actualName,expectedName,"error message : assert 1");
    }

    @Test (dependsOnMethods = "validateShoppingCartPage")
    public void getLoginMsgINCartPage (){
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        String expectedMsg = "Register / Login account to proceed on checkout.";
        String actualMsg = action.getText(cart.getLoginMsgToContinuePaymentProcess(getDriver()));
        validation.assertTrueString(actualMsg,expectedMsg,"error message : assert 1");
    }



    @Test (dependsOnMethods = "getLoginMsgINCartPage")
    public void LoginWithRegisteredEmail(){
        action.Click(cart.clickOnLoginRegisterButtonInCartPage(getDriver()));
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());

        String expectedText = "Logged in as";
        String actualText = action.getText(loginPage.getLoggedInAsText(getDriver()));
        validation.assertTrueString(actualText,expectedText,"error message : assert 1");

    }



    @Test (dependsOnMethods = "LoginWithRegisteredEmail")
    public void getCheckoutLink (){
        action.Click(product.clickOnCartButton(getDriver()));
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        String expectedUrl = "https://www.automationexercise.com/checkout";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");

        String expectedName = "Checkout";
        String actualName = action.getText(checkout.getCheckoutText(getDriver()));
        validation.assertTrueString(actualName,expectedName,"error message : assert 2");
        validation.assertAll();

    }



    @Test (dependsOnMethods = "getCheckoutLink")
    public void getPaymentPageLink (){
        action.Click(checkout.clickOnPlaceOrderButton(getDriver()));
        String expectedUrl = "https://www.automationexercise.com/payment";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");
    }

    @Test (dependsOnMethods = "getPaymentPageLink")
    public void getPaymentText (){
        String expectedName = "Payment";
        String actualName = action.getText(payment.getPaymentText(getDriver()));
        validation.assertTrueString(actualName,expectedName,"error message : assert 1");
    }



    @Test (dependsOnMethods = "getPaymentText")
    public void OrderMadeSuccessfully (){

        String expectedMsg = "ORDER PLACED!";
        payment.PaymentSteps(getDriver(),helperMethod.getPaymentData(0).getCardName(),
        helperMethod.getPaymentData(0).getCardNumber(),helperMethod.getPaymentData(0).getCvc(),
        helperMethod.getPaymentData(0).getExpirationMonth(),helperMethod.getPaymentData(0).getExpirationYear());

        String actualMsg = action.getText(payment.orderPlacedMessage(getDriver()));
        validation.assertEqualsString(actualMsg, expectedMsg,"error message : assert 1");

        String expectedMessage = "Congratulations! Your order has been confirmed!";
        String actualMessage = action.getText(payment.orderConfirmation(getDriver()));
        validation.assertTrueString(actualMessage,expectedMessage,"error message : assert 1");

        validation.assertAll();
    }

    @Test (dependsOnMethods = {"OrderMadeSuccessfully"})
    public void clickOnLogoutButton () {

        String expectedUrl = "https://www.automationexercise.com/login";
        action.Click(payment.clickOnLogoutButton(getDriver()));
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl, expectedUrl, "error message : assert 1");

    }

    @AfterClass
    public void quitDriver(){
        tearDown();
    }

}
