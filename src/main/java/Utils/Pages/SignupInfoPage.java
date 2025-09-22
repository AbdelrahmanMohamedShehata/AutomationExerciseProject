package Utils.Pages;

import Utils.UIActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupInfoPage {

    //objects
    ElementActions action = new ElementActions();

    //locators

       By Mr_gender = By.id("id_gender1");
       By Mrs_gender = By.id("id_gender2");
       By password = By.cssSelector("input#password");
       By day = By.id("days");
       By month = By.id("months");
       By year = By.id("years");
       By newsletter =By.id("newsletter");
       By optin = By.id("optin");
       By first_name = By.id("first_name");
       By last_name = By.id("last_name");
       By company = By.id("company");
       By address1 = By.id("address1");
       By address2 = By.id("address2");
       By country = By.id("country");
       By state = By.id("state");
       By city = By.id("city");
       By zipcode = By.id("zipcode");
       By mobile_number = By.id("mobile_number");
       By accountCreatedText = By.cssSelector("h2>b");
       By accountCreatedMsg = By.xpath("//p[contains(text(),'Congratulations!')]");
       By continueButton = By.cssSelector("a[data-qa*=\"continue\"]");
       By deleteAccountButton = By.cssSelector("a[href=\"/delete_account\"]");
       By accountDeletedText = By.cssSelector("h2 b");
       By accountDeletedMsg = By.xpath("//p[contains(text(), 'permanently deleted!')]");


    // WebElement methods

    public WebElement genderMr(WebDriver driver){
            return action.element(driver,Mr_gender);
        }
        public WebElement genderMrs(WebDriver driver){
            return action.element(driver,Mrs_gender);
        }
        public WebElement passwordSignupEle(WebDriver driver){
            return action.explicitWaitWithByClickable(password,driver,5);
        }
        public WebElement daysElement(WebDriver driver){
            return action.element(driver,day);
        }
        public WebElement monthsElement(WebDriver driver){
            return action.element(driver,month);
        }
        public WebElement yearElement (WebDriver driver){
            return action.element(driver,year);
        }
        public WebElement newsLetterEle(WebDriver driver){
            return action.element(driver,newsletter);
        }
        public WebElement getSpecialOfferEle(WebDriver driver){
            return action.element(driver,optin);
        }
        public WebElement firstNameEle(WebDriver driver){
            return action.element(driver,first_name);
        }
        public WebElement lastNameEle(WebDriver driver){
            return action.element(driver,last_name);
        }
        public WebElement companyEle(WebDriver driver){return action.element(driver,company);}
        public WebElement address1ele(WebDriver driver){return action.element(driver,address1);}
        public WebElement address2ele(WebDriver driver){return action.element(driver,address2);}
        public WebElement countryEle(WebDriver driver){
            return action.element(driver,country);
        }
        public WebElement stateEle(WebDriver driver){
            return action.element(driver,state);
        }
        public WebElement cityEle(WebDriver driver){
            return action.element(driver,city);
        }
        public WebElement zipcodeEle(WebDriver driver){
            return action.element(driver,zipcode);
        }
        public WebElement mobileNumberEle(WebDriver driver){
            return action.element(driver,mobile_number);
        }

        public WebElement getAccountCreatedText(WebDriver driver){
            return action.element(driver,accountCreatedText);
        }
        public WebElement getAccountCreatedMsg(WebDriver driver){
        return action.element(driver,accountCreatedMsg);
    }

        public WebElement clickContinueButton(WebDriver driver){
            return action.explicitWaitWithByClickable(continueButton,driver,5);
    }
        public WebElement clickDeleteAccountButton(WebDriver driver){
            return action.explicitWaitWithByClickable(deleteAccountButton,driver,5);
    }
        public WebElement getAccountDeletedText(WebDriver driver){
        return action.element(driver,accountDeletedText);
        }
        public WebElement getAccountDeletedMsg(WebDriver driver){
        return action.element(driver,accountDeletedMsg);
    }


      // Steps Methods

    @Step("send signupInfoData  password:{result.password}, firstName:{result.firstName}," +
            " lastName:{result.lastName}, country:{result.country}," +
            " city:{result.city}, mobileNumber:{result.mobileNumber}")
        public void Mandatorysignupsteps (WebDriver driver,String password,String firstName,String lastName, String address
                ,String country,String state,String city,String zipCode,String mobileNumber)
        {

            action.SendKeys(passwordSignupEle(driver),password);
            action.SendKeys(firstNameEle(driver),firstName);
            action.SendKeys(lastNameEle(driver),lastName);
            action.SendKeys(address1ele(driver),address);
            action.selectByVisibleText(countryEle(driver),country);
            action.SendKeys(stateEle(driver),state);
            action.SendKeys(cityEle(driver),city);
            action.SendKeys(zipcodeEle(driver),zipCode);
            action.SendKeys(mobileNumberEle(driver),mobileNumber);
            action.submit(mobileNumberEle(driver));
        }

        public void AllSignupStepsWithoutGender (WebDriver driver,String password,String day,String month,String year,
                                            String firstname,String lastname,String company,String address1,String address2,
                                            String country, String state,String city,String zipcode,String mobilenumber)
        {
            action.SendKeys(passwordSignupEle(driver),password);
            action.selectByVisibleText(daysElement(driver),day);
            action.selectByVisibleText(monthsElement(driver),month);
            action.selectByVisibleText(yearElement(driver),year);
            action.Click(newsLetterEle(driver));
            action.Click(getSpecialOfferEle(driver));
            action.SendKeys(firstNameEle(driver),firstname);
            action.SendKeys(lastNameEle(driver),lastname);
            action.SendKeys(companyEle(driver),company);
            action.SendKeys(address1ele(driver),address1);
            action.SendKeys(address2ele(driver),address2);
            action.selectByVisibleText(countryEle(driver),country);
            action.SendKeys(stateEle(driver),state);
            action.SendKeys(cityEle(driver),city);
            action.SendKeys(zipcodeEle(driver),zipcode);
            action.SendKeys(mobileNumberEle(driver),mobilenumber);
            action.submit(mobileNumberEle(driver));
        }
    }

