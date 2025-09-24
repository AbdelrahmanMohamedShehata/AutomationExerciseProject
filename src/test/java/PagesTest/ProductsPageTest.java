package PagesTest;

import Assertions.Validation;
import Utils.UIActions.ElementActions;
import Utils.Pages.HomePage;
import Utils.Pages.LoginPage;
import Utils.Pages.ProductsPage;
import Utils.Pojo.DataHelperMethods;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static Utils.Driver.DriverManager.*;

public class ProductsPageTest {
    HomePage home = new HomePage();
    LoginPage loginPage = new LoginPage();
    ProductsPage products = new ProductsPage();
    ElementActions action = new ElementActions();
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
    }


    @Test
    public void validateNavigationToProductsPage() {

        String expectedUrl = "https://www.automationexercise.com/products";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error Msg : assert1 ");

        String expectedOutput = "ALL PRODUCTS";
        action.ScrollingByElement(getDriver(),products.getAllProductsText(getDriver()));
        String actualOutput = action.getText(products.getAllProductsText(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 2");

    }

    @Test
    public void checkTextsAndImages() {

        String expectedOutput = "CATEGORY";
        action.ScrollingByElement(getDriver(),products.getCategoryText(getDriver()));
        String actualOutput = action.getText(products.getCategoryText(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 3");

        String expectedResult = "BRANDS";
        action.ScrollingByElement(getDriver(),products.getBrandText(getDriver()));
        String actualResult = action.getText(products.getBrandText(getDriver()));
        validation.assertEqualsString(actualResult, expectedResult,"error message : assert 4");

        String expectedLogo = "/static/images/home/logo.png";
        action.ScrollingByElement(getDriver(),products.isAutomationExerciseImgDisplayed(getDriver()));
        String actualLogo = action.getAttribute(products.isAutomationExerciseImgDisplayed(getDriver()),"src");
        validation.assertTrueString(actualLogo, expectedLogo,"error message : assert 5");

        action.ScrollingByElement(getDriver(),products.isSpecialOfferImgDisplayed(getDriver()));
        boolean display = action.isDisplayed(products.isSpecialOfferImgDisplayed(getDriver()));
        validation.assertTrueBoolean(display,"error message : assert 6");
        String expectedImage = "/static/images/shop/sale.jpg";
        String actualImage = action.getAttribute(products.isSpecialOfferImgDisplayed(getDriver()),"src");
        validation.assertTrueString(actualImage,expectedImage,"error message : assert 7");
        validation.assertAll();
    }

    @Test
    public void validateALLProductsSize() {

        int expectedOutput = 34;
        int actualOutput = products.getAllProductsSize(getDriver());
        validation.assertEqualsInt(actualOutput,expectedOutput,"error message : assert 8");
    }

    @Test
    public void checkProductId30Information(){

        String expectedResult = helperMethod.getProductData(0).getPrice();
        action.ScrollingByElement(getDriver(),products.priceOfProductId30(getDriver()));
        String actualResult = action.getText(products.priceOfProductId30(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualResult,expectedResult,"error message : assert 9");

        String expectedOutput =helperMethod.getProductData(0).getProductName();
        action.ScrollingByElement(getDriver(),products.nameOfProductId30(getDriver()));
        String actualOutput = action.getText(products.nameOfProductId30(getDriver())).split(" ")[0];
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 10");

        String expectedBrand =helperMethod.getProductData(0).getBrand();
        action.ScrollingByElement(getDriver(),products.nameOfProductId30(getDriver()));
        String actualBrand = action.getText(products.nameOfProductId30(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualBrand,expectedBrand,"error message : assert 11");

        String expectedCategory =helperMethod.getProductData(0).getSubCategoryName();
        action.ScrollingByElement(getDriver(),products.nameOfProductId30(getDriver()));
        String actualCategory = action.getText(products.nameOfProductId30(getDriver())).split(" ")[2];
        validation.assertEqualsString(actualCategory, expectedCategory,"error message : assert 12");


    }

        @Test
        public void checkProductId29Information(){

            String expectedResult = helperMethod.getProductData(1).getPrice();
            action.ScrollingByElement(getDriver(),products.priceOfProductId29(getDriver()));
            String actualResult = action.getText(products.priceOfProductId29(getDriver())).split(" ")[1];
            validation.assertEqualsString(actualResult,expectedResult,"error message : assert 9");

            String expectedOutput =helperMethod.getProductData(1).getProductName();
            action.ScrollingByElement(getDriver(),products.nameOfProductId29(getDriver()));
            String actualOutput = action.getText(products.nameOfProductId29(getDriver())).split("T")[0].trim();
            validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 10");

            String expectedCategory =helperMethod.getProductData(1).getSubCategoryName();
            action.ScrollingByElement(getDriver(),products.nameOfProductId29(getDriver()));
            String actualCategory = action.getText(products.nameOfProductId29(getDriver())).split(" ")[4];
            validation.assertEqualsString(actualCategory, expectedCategory,"error message : assert 12");


        }

    @Test
    public void checkProductImagesUrls(){
        int actualUrl = action.getBrokenUrl(getDriver(),products.getImgUrls(),"src");
        validation.assertEqualsInt(actualUrl,0,"error message : assert 13");

    }

    @Test
    public void checkAllProductsId(){
       List<String> actualId = products.getAllProductsId(getDriver());
        for (int x=0;x<34;x++){
           String expectedId = helperMethod.getAllProductsId().get(x).getProductId();
           String ActualId = actualId.get(x);
           validation.assertEqualsString(ActualId,expectedId,"error message : assert x: "+ x);
        }
          validation.assertAll();
    }

    @Test
    public void addProductToCart() {
        action.ScrollingByElement(getDriver(),products.searchField(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        products.clickAddToCartId30(getDriver());
        String expectedOutput = "Your product has been added to cart.";
        String actualOutput = action.getText(products.getProductAddedToCartMsg(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 14");
        action.ScrollingByElement(getDriver(),products.clickViewCart(getDriver()));
        action.Click(products.clickViewCart(getDriver()));
        String expectedUrl ="https://www.automationexercise.com/view_cart";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 15");
    }

    @Test
    public void navigateToProductPage() {

        action.ScrollingByElement(getDriver(),products.clickViewProductId30(getDriver()));
        action.Click(products.clickViewProductId30(getDriver()));
        String expectedUrl = "https://www.automationexercise.com/product_details/30";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 16");
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }


}
