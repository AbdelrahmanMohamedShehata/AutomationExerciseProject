package PagesTest;
import Assertions.Validation;
import Utils.Pages.*;
import Utils.Pojo.DataHelperMethods;
import Utils.UIActions.ElementActions;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;

public class ShoppingCartPage {

    HomePage home = new HomePage();
    LoginPage loginPage = new LoginPage();
    ProductsPage products = new ProductsPage();
    ElementActions action = new ElementActions() ;
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod
    public void openBrowser(){

        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
        action.Click(home.navigateToLoginPage(getDriver()));
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());
        action.Click(products.ProductsButton(getDriver()));
        products.clickAddToCartId30(getDriver());
        action.Click(products.clickViewCart(getDriver()));
        action.Click(cart.removeProduct30FromCart(getDriver()));
        action.Click(cart.clickHere(getDriver()));
        action.ScrollingByElement(getDriver(),products.searchButton(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId30(getDriver()));
        action.Click(products.clickViewProductId30(getDriver()));
        product.changeProductQuantity(getDriver(),helperMethod.getProductData(0).getQuantity());
        action.Click(product.addProductToCart(getDriver()));
        action.Click(product.ViewCartButton(getDriver()));

    }


    @Test
    public void validateShoppingCartPage (){
        String expectedName = "Shopping Cart";
        String actualName = action.getText(cart.getShoppingCartText(getDriver()));
        validation.assertTrueString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void checkProductInformationInCart (){
        String expectedName = "Premium Polo";
        String actualName = action.getText(cart.getCartProductName(getDriver()));
        validation.assertTrueString(actualName,expectedName,"error message : assert 1");

        String expectedPrice = helperMethod.getProductData(0).getPrice();
        String actualPrice = action.getText(cart.getCartProductPrice(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualPrice,expectedPrice,"error message : assert 2");

        String expectedTotalPrice = helperMethod.getProductData(0).getTotalPrice();
        String actualTotalPrice = action.getText(cart.getCartProductTotalPrice(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualTotalPrice, expectedTotalPrice,"error message : assert 3");

        String expectedQuantity = helperMethod.getProductData(0).getQuantity();
        String actualQuantity = action.getText(cart.getCartProductQuantity(getDriver()));
        validation.assertEqualsString(actualQuantity, expectedQuantity,"error message : assert 4");
        validation.assertAll();
    }

    @Test
    public void navigateTOCheckoutPage(){
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        String expectedUrl ="https://www.automationexercise.com/checkout";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");
    }

    @Test
    public void checkEmptyCart(){
        action.Click(cart.removeProduct30FromCart(getDriver()));
        String expectedResult = "Cart is empty!";
        String actualResult = action.getText(cart.cartIsEmptyMsg(getDriver()));
        validation.assertEqualsString(actualResult,expectedResult,"error message : assert 1");
        validation.assertAll();
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }

}
