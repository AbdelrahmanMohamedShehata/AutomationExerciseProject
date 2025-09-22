package PagesTest;

import Assertions.Validation;
import Utils.UIActions.ElementActions;
import Utils.Pages.HomePage;
import Utils.Pages.LoginPage;
import Utils.Pages.ProductsPage;
import Utils.Pojo.DataHelperMethods;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;

public class SearchForProduct {

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
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        String expectedUrl = "https://www.automationexercise.com/products?search=Tshirt";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error Msg : assert1 ");

        String expectedOutput = "SEARCHED PRODUCTS";
        String actualOutput = action.getText(products.getSearchedProductsText(getDriver()));
        validation.assertEqualsString(actualOutput,expectedOutput,"error message : assert 2");
        validation.assertAll();
    }

    @Test
    public void validateALLSearchedProductsSize() {
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        int expectedOutput = 6;
        int actualOutput = products.getAllSearchedProductsSize(getDriver());
        validation.assertEqualsInt(actualOutput,expectedOutput,"error message : assert 1 ");
    }

    @Test
    public void SearchWithInvalidData(){
        for(int x=6;x<9;x++) {
            action.Clear(products.searchField(getDriver()));
            products.searchForProductSteps(getDriver(), helperMethod.getAllInvalidData().get(x).getDataInput());
            int expectedOutput = 0;
            int actualOutput = products.getAllSearchedProductsSize(getDriver());
            validation.assertEqualsInt(actualOutput, expectedOutput, "error message : assert 2");

        }
        validation.assertAll();
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }
}
