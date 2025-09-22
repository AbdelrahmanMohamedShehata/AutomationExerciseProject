package PagesTest;

import Assertions.Validation;
import Utils.Pages.*;
import Utils.Pojo.DataHelperMethods;
import Utils.UIActions.ElementActions;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;

public class ProductReviewTest {
    HomePage home = new HomePage();
    LoginPage loginPage = new LoginPage();
    ProductsPage products = new ProductsPage();
    ElementActions action = new ElementActions();
    ProductReview review = new ProductReview();
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
        action.ScrollingByElement(getDriver(),products.searchButton(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId30(getDriver()));
        action.Click(products.clickViewProductId30(getDriver()));
    }


    @Test()
    public void sendReviewSuccessfully(){
        review.SendReviewSteps(getDriver(),helperMethod.getReviewData(0).getName(),
         helperMethod.getReviewData(0).getEmail(),helperMethod.getReviewData(0).getAddReview());
            String expectedText ="Thank you for your review.";
            String actualText = action.getText(review.getReviewSuccessMsg(getDriver()));
        validation.assertEqualsString(actualText, expectedText,"error message : assert 1");
    }

    @Test()
    public void getWriteReviewText(){
        String expectedText ="WRITE YOUR REVIEW";
        String actualText = action.getText(review.getWriteYourReviewText(getDriver()));
        validation.assertEqualsString(actualText,expectedText,"error message : assert 1");
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }


}
