package PagesTest;
import Assertions.Validation;
import Utils.Pages.*;
import Utils.Pojo.DataHelperMethods;
import Utils.UIActions.ElementActions;
import org.testng.annotations.*;
import static Utils.Driver.DriverManager.*;
import static Utils.UIActions.ScreenShot.takeScreenShotAsFile;

public class CheckoutPageTest {

    ProductPage product = new ProductPage();
    LoginPage loginPage = new LoginPage();
    ProductsPage products = new ProductsPage();
    ElementActions action = new ElementActions() ;
    CartPage cart = new CartPage();
    CheckoutPage checkout = new CheckoutPage();
    Validation validation = new Validation();
    DataHelperMethods helperMethod = new DataHelperMethods();

    @BeforeMethod
    public void openBrowser(){
        createInstance(helperMethod.getBrowserNames(0).getBrowser());
        action.navigation(getDriver(),helperMethod.getUrlData(0).getHomeUrl());
        action.Click(products.ProductsButton(getDriver()));
        checkout.fromSearchToCheckout(getDriver(),helperMethod.getSearchKeys(0).getSearchKey(),
                helperMethod.getProductData(0).getQuantity());
        action.Click(cart.clickOnLoginRegisterButtonInCartPage(getDriver()));

    }

    @Test
    public void validateNavigatingToCheckoutPage (){
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());
        action.Click(product.clickOnCartButton(getDriver()));
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        String expectedUrl = "https://www.automationexercise.com/checkout";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 1");
        String expectedName = "Checkout";
        String actualName = action.getText(checkout.getCheckoutText(getDriver()));
        validation.assertEqualsString(actualName,expectedName,"error message : assert 2");
        action.returnBack(getDriver());
        action.Click(cart.removeProduct30FromCart(getDriver()));
        validation.assertAll();
    }

    @Test
        public void ValidateAddressInformation (){
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());
        action.Click(product.clickOnCartButton(getDriver()));
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        String DeliveryAddressFirstLastName = action.getText(checkout.getDeliveryAddressFirstLastNameText(getDriver()));
        String BillingAddressFirstLastName = action.getText(checkout.getBillingAddressFirstLastNameText(getDriver()));
        validation.assertEqualsString(BillingAddressFirstLastName,DeliveryAddressFirstLastName,"error message : assert 3");

        String DeliveryAddress1Address2Text = action.getText(checkout.getDeliveryAddress1Address2Text(getDriver()));
        String BillingAddress1Address2Text = action.getText(checkout.getBillingAddress1Address2Text(getDriver()));
        validation.assertEqualsString(BillingAddress1Address2Text,DeliveryAddress1Address2Text,"error message : assert 4");

        action.ScrollingByElement(getDriver(),checkout.getDeliveryAddressCityStatePostcodeText(getDriver()));

        String DeliveryAddressCityStatePostcodeText = action.getText(checkout.getDeliveryAddressCityStatePostcodeText(getDriver()));
        String BillingAddressCityStatePostcodeText = action.getText(checkout.getBillingAddressCityStatePostcodeText(getDriver()));
        validation.assertEqualsString(BillingAddressCityStatePostcodeText,DeliveryAddressCityStatePostcodeText,"error message : assert 5");

        action.ScrollingByElement(getDriver(),checkout.getDeliveryAddressCountryNameText(getDriver()));

        String DeliveryAddressCountryNameText = action.getText(checkout.getDeliveryAddressCountryNameText(getDriver()));
        String BillingAddressCountryNameText = action.getText(checkout.getBillingAddressCountryNameText(getDriver()));
        validation.assertEqualsString(BillingAddressCountryNameText,DeliveryAddressCountryNameText,"error message : assert 6");

        action.ScrollingByElement(getDriver(),checkout.getDeliveryAddressPhoneNumberText(getDriver()));

        String DeliveryAddressPhoneNumberText = action.getText(checkout.getDeliveryAddressPhoneNumberText(getDriver()));
        String BillingAddressPhoneNumberText = action.getText(checkout.getBillingAddressPhoneNumberText(getDriver()));
        validation.assertEqualsString(BillingAddressPhoneNumberText,DeliveryAddressPhoneNumberText,"error message : assert 7");
        action.returnBack(getDriver());
        action.Click(cart.removeProduct30FromCart(getDriver()));
        validation.assertAll();
    }

    @Test
    public void checkProductInformationInCheckout (){
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(6).getEmail(),
                helperMethod.getLoginData(6).getPassword());
        action.Click(product.clickOnCartButton(getDriver()));
        action.Click(cart.removeProduct30FromCart(getDriver()));
        action.Click(cart.clickHere(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId30(getDriver()));
        action.Click(products.clickViewProductId30(getDriver()));
        product.changeProductQuantity(getDriver(),helperMethod.getProductData(0).getQuantity());
        action.Click(product.addProductToCart(getDriver()));
        action.Click(product.ViewCartButton(getDriver()));
        takeScreenShotAsFile(getDriver(),"QuantityInCartPage");
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        action.ScrollingByElement(getDriver(),checkout.getCheckoutProductTotalPrice(getDriver()));
        takeScreenShotAsFile(getDriver(),"QuantityInCheckoutPage");
        String expectedPrice = helperMethod.getProductData(0).getTotalPrice();
        String actualPrice = action.getText(checkout.getCheckoutProductTotalPrice(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualPrice,expectedPrice,"error message : assert 8");

        String expectedQuantity = helperMethod.getProductData(0).getQuantity() ;
        String actualQuantity = action.getText(checkout.getCheckoutProductQuantity(getDriver()));
        validation.assertEqualsString(actualQuantity,expectedQuantity,"error message : assert 9");

        action.ScrollingByElement(getDriver(),checkout.getCheckoutProductsTotalPrice(getDriver()));

        String actualTotalPrice = action.getText(checkout.getCheckoutProductsTotalPrice(getDriver())).split(" ")[1];
        validation.assertEqualsString(actualTotalPrice,expectedPrice,"error message : assert 10");

        validation.assertAll();

    }

    @Test
    public void checkTwoProductsInformationInCheckout(){
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(7).getEmail(),
                helperMethod.getLoginData(7).getPassword());
        action.Click(products.ProductsButton(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId29(getDriver()));
        products.clickAddToCart29(getDriver());
        action.Click(products.clickViewCart(getDriver()));
        action.Click(cart.removeProduct30FromCart(getDriver()));
        action.Click(cart.removeProduct29FromCart(getDriver()));
        action.Click(cart.clickHere(getDriver()));
        action.ScrollingByElement(getDriver(),products.searchButton(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId30(getDriver()));
        action.Click(products.clickViewProductId30(getDriver()));
        product.changeProductQuantity(getDriver(),helperMethod.getProductData(0).getQuantity());
        action.Click(product.addProductToCart(getDriver()));
        action.Click(product.continueShoppingButton(getDriver()));
        action.returnBack(getDriver());
        action.ScrollingByElement(getDriver(),products.searchButton(getDriver()));
        action.Clear(products.searchField(getDriver()));
        products.searchForProductSteps(getDriver(),helperMethod.getSearchKeys(0).getSearchKey());
        action.ScrollingByElement(getDriver(),products.clickViewProductId29(getDriver()));
        action.Click(products.clickViewProductId29(getDriver()));
        product.changeProductQuantity(getDriver(),helperMethod.getProductData(1).getQuantity());
        action.Click(product.addProductToCart(getDriver()));
        action.Click(product.ViewCartButton(getDriver()));
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        action.ScrollingByElement(getDriver(),checkout.getReviewYourOrderTexT(getDriver()));
        takeScreenShotAsFile(getDriver(),"review_order_info_checkout");
        String expectedPrice = helperMethod.getProductData(2).getTotalPrice();
        String TotalProductsPrice = action.getText(checkout.getProductsTotalAmount(getDriver())).split(" ")[1];
        validation.assertEqualsString(TotalProductsPrice,expectedPrice,"error message : assert 1");
        validation.assertAll();
    }

    @Test
    public void navigateTOPaymentPage() {
        loginPage.loginSteps(getDriver(),helperMethod.getLoginData(0).getEmail(),
                helperMethod.getLoginData(0).getPassword());
        action.Click(product.clickOnCartButton(getDriver()));
        action.Click(cart.clickOnCheckoutButton(getDriver()));
        action.ScrollingByElement(getDriver(),checkout.clickOnPlaceOrderButton(getDriver()));
        action.Click(checkout.clickOnPlaceOrderButton(getDriver()));

        String expectedUrl ="https://www.automationexercise.com/payment";
        String actualUrl = action.getCurrentUrl(getDriver());
        validation.assertEqualsString(actualUrl,expectedUrl,"error message : assert 11");
        action.returnBack(getDriver());
        action.returnBack(getDriver());
        action.Click(cart.removeProduct30FromCart(getDriver()));
    }

    @AfterMethod
    public void quitDriver(){
        tearDown();
    }

}
