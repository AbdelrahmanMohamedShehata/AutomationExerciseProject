package PagesTest;

import Assertions.Validation;
import Utils.Pages.*;
import Utils.Pojo.DataHelperMethods;
import Utils.UIActions.ElementActions;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;
import static Utils.UIActions.ScreenShot.takeScreenShotAsFile;

public class ProductPageTest {
    HomePage home = new HomePage();
    LoginPage loginPage = new LoginPage();
    ProductsPage products = new ProductsPage();
    ElementActions action = new ElementActions();
    ProductPage product = new ProductPage();
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
        action.ScrollingByElement(getDriver(),products.searchField(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId30(getDriver()));
        action.Click(products.clickViewProductId30(getDriver()));
    }


    @Test
    public void checkProductName(){
        String expectedName = helperMethod.getProductData(0).getProductName();
        String actualName = action.getText(product.ProductName(getDriver())).split(" ")[0];
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void checkSubCategoryName(){
        String expectedName = helperMethod.getProductData(0).getSubCategoryName();
        action.ScrollingByElement(getDriver(),product.ProductName(getDriver()));
        String actualName = action.getText(product.ProductName(getDriver())).split("o")[2].trim();   // Actual   :Premium  Polo  T-Shirts
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");

    }

    @Test
    public void isProductImgDisplayed () {
        validation.assertTrueBoolean(action.isDisplayed(product.ProductImg(getDriver())),"error message : assert 1");
        String expectedOutput = "/get_product_picture/30";
        String actualOutput = action.getAttribute(product.ProductImg(getDriver()),"src");
        validation.assertTrueString(actualOutput,expectedOutput,"error message : assert 2");
    }

    @Test
    public void isRatingImgDisplayed () {
        validation.assertTrueBoolean(action.isDisplayed(product.RatingImg(getDriver())),"error message : assert 1");
        String expectedOutput = "/static/images/product-details/rating.png";
        String actualOutput = action.getAttribute(product.RatingImg(getDriver()),"src");
        validation.assertTrueString(actualOutput,expectedOutput,"error message : assert 2");
    }

    @Test
    public void getCategoryName () {
        String expectedName = helperMethod.getProductData(0).getCategoryName();
        String actualName = action.getText(product.getCategoryName(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void checkProductPrice (){
        String expectedPrice = helperMethod.getProductData(0).getPrice();
        String actualPrice = action.getText(product.ProductPrice(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualPrice,expectedPrice,"error message : assert 1");
    }

    @Test
    public void selectProductQuantity (){
        String expectedQuantity = helperMethod.getProductData(0).getQuantity();
        product.changeProductQuantity(getDriver(),helperMethod.getProductData(0).getQuantity());
        String actualQuantity = action.getText(product.ProductQuantity(getDriver()));
        validation.assertEqualsString(actualQuantity, expectedQuantity,"error message : assert 1");
    }

    @Test
    public void checkProductAvailability (){
        String expectedName = helperMethod.getProductData(0).getAvailability();
        String actualName = action.getText(product.ProductAvailability(getDriver())).split(":")[1].trim();
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void checkProductCondition (){

        String expectedName = helperMethod.getProductData(0).getCondition();
        String actualName = action.getText(product.ProductCondition(getDriver())).split(":")[1];
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void checkProductBrandName (){
        String expectedName = helperMethod.getProductData(0).getBrand();
        String actualName = action.getText(product.ProductBrandName(getDriver())).split(":")[1].trim();
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void AddedProductToCartSuccessfully (){
        action.Click(product.addProductToCart(getDriver()));
        String expectedName = "Your product has been added to cart.";
        String actualName = action.getText(product.addedProductToCartConfirmationMsg(getDriver()));
        validation.assertEqualsString(actualName,expectedName,"error message : assert 1");
    }

    @Test
    public void navigateTOViewCartPage (){
        String expectedUrl ="https://www.automationexercise.com/view_cart";
        action.Click(product.addProductToCart(getDriver()));
        action.Click(product.ViewCartButton(getDriver()));
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");
    }

    @Test
    public void addInvalidSpecialCharactersOrCharactersInQuantity(){
        for(int x=3;x<7;x++){
        product.changeProductQuantity(getDriver(),helperMethod.getAllInvalidData().get(x).getDataInput());
        String value = action.getAttribute(product.ProductQuantity(getDriver()),"value");
        action.Click(product.addProductToCart(getDriver()));
        takeScreenShotAsFile(getDriver(),"quantityField_"+x);
        validation.assertTrueBoolean(value.isEmpty(),"error message : assert 1");
        }
        validation.assertAll();
    }

    @Test
    public void addProductToCartWithDecimalNumbersInQuantity(){
        for(int x=10;x<12;x++){
            product.changeProductQuantity(getDriver(),helperMethod.getAllInvalidData().get(x).getDataInput());
            String value = action.getAttribute(product.ProductQuantity(getDriver()),"value");
            String expectedValue = helperMethod.getAllInvalidData().get(x).getDataInput();
            action.Click(product.addProductToCart(getDriver()));
            takeScreenShotAsFile(getDriver(),"quantityField_"+x);
            validation.assertEqualsString(value,expectedValue,"error message : assert 1");
        }
        validation.assertAll();
    }

    @Test
    public void addProductToCartWithNegativeNumberInQuantity(){
        product.changeProductQuantity(getDriver(),helperMethod.getInvalidData(9).getDataInput());
        action.Click(product.addProductToCart(getDriver()));
        try {
            if(product.ViewCartButton(getDriver()).isDisplayed()){
                validation.assertFail("the user added product to cart with Negative number in quantity so this is a bug");
            }
            else {
                System.out.println("the user can't add product to cart with negative number in quantity");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        validation.assertAll();
    }

    @Test
    public void addProductToCartWithZeroQuantity(){
        product.changeProductQuantity(getDriver(),helperMethod.getInvalidData(8).getDataInput());
        action.Click(product.addProductToCart(getDriver()));
        try {
            if(product.ViewCartButton(getDriver()).isDisplayed()){
                validation.assertFail("the user added product to cart with zero quantity so this is a bug");
            }
            else {
                System.out.println("the user can't add product to cart with zero quantity");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        validation.assertAll();
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }
}
