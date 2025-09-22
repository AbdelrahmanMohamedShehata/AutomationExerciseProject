package PagesTest;

import Assertions.Validation;
import Utils.Pages.*;
import Utils.Pojo.DataHelperMethods;
import Utils.UIActions.ElementActions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.List;

import static Utils.Driver.DriverManager.*;
import static Utils.Logs.log4j.info;

public class PaymentPageTest {
    HomePage home = new HomePage();
    LoginPage loginPage = new LoginPage();
    ElementActions action = new ElementActions();
    CartPage cart = new CartPage();
    CheckoutPage checkout = new CheckoutPage();
    PaymentPage paymentPage = new PaymentPage();
    ProductsPage products = new ProductsPage();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod()
    public void openBrowser(){

        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
        action.Click(home.navigateToLoginPage(getDriver()));
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());
        action.Click(products.ProductsButton(getDriver()));
        action.ScrollingByElement(getDriver(),products.searchButton(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByPixelsXYAxes(getDriver(),0,1250);
        products.clickAddToCartId30(getDriver());
        action.Click(products.clickViewCart(getDriver()));
        action.Click(cart.removeProduct30FromCart(getDriver()));
        action.Click(cart.clickHere(getDriver()));
        checkout.fromSearchToCheckout(getDriver(),helperMethod.getSearchKeys(0).getSearchKey(),
                helperMethod.getProductData(0).getQuantity());
        action.ScrollingByElement(getDriver(),checkout.clickOnPlaceOrderButton(getDriver()));
        action.Click(checkout.clickOnPlaceOrderButton(getDriver()));
    }


    @Test
    public void validateNavigatingToPaymentPage (){

        String expectedUrl = "https://www.automationexercise.com/payment";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");

        String expectedName = "Payment";
        String actualName = action.getText(paymentPage.getPaymentText(getDriver()));
        validation.assertTrueString(actualName,expectedName,"error message : assert 2");
        validation.assertAll();
    }

    @Test
    public void ValidateOrderMadeSuccessfully(){
        paymentPage.PaymentSteps(getDriver(),helperMethod.getPaymentData(0).getCardName(),
                helperMethod.getPaymentData(0).getCardNumber(),helperMethod.getPaymentData(0).getCvc(),
                helperMethod.getPaymentData(0).getExpirationMonth(),helperMethod.getPaymentData(0).getExpirationYear());

        String expectedMsg = "ORDER PLACED!";
        String actualMsg = action.getText(paymentPage.orderPlacedMessage(getDriver()));
        validation.assertEqualsString(actualMsg, expectedMsg,"error message : assert 1");

        String expectedMessage = "Congratulations! Your order has been confirmed!";
        String actualMessage = action.getText(paymentPage.orderConfirmation(getDriver()));
        validation.assertEqualsString(actualMessage, expectedMessage,"error message : assert 1");

        validation.assertAll();
    }

    @Test
    public void validateClickingOnLogoutButton () {

        paymentPage.PaymentSteps(getDriver(),helperMethod.getPaymentData(0).getCardName(),
                helperMethod.getPaymentData(0).getCardNumber(),helperMethod.getPaymentData(0).getCvc(),
                helperMethod.getPaymentData(0).getExpirationMonth(),helperMethod.getPaymentData(0).getExpirationYear());

        String expectedUrl = "https://www.automationexercise.com/login";
        action.Click(paymentPage.clickOnLogoutButton(getDriver()));
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl, expectedUrl, "error message : assert 1");

    }

    @Test
    public void checkDownloadedFile(){
        paymentPage.PaymentSteps(getDriver(),helperMethod.getPaymentData(0).getCardName(),
                helperMethod.getPaymentData(0).getCardNumber(),helperMethod.getPaymentData(0).getCvc(),
                helperMethod.getPaymentData(0).getExpirationMonth(),helperMethod.getPaymentData(0).getExpirationYear());

        action.Click(paymentPage.downloadInvoice(getDriver()));
        String filename = "invoice.txt";
        File folder = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Downloads");
        File targetFile = new File(folder,filename);

       int actualFilesNumber = action.waitAndCheckFilesNumber(targetFile,folder,20,2,1);
        validation.assertEqualsInt(actualFilesNumber,1,"error message: assert 1");
        List<String> FilesName=action.waitAndCheckFilesName(targetFile,folder,20,2);
        String actualFileName = FilesName.getFirst();
        validation.assertEqualsString(actualFileName,filename,"error message: assert 2");

        validation.assertAll();
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }

    }
