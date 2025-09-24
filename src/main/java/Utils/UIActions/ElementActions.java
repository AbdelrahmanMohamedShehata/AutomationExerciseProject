package Utils.UIActions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Utils.Logs.log4j.error;
import static Utils.Logs.log4j.info;

public class ElementActions {

    public ElementActions(){};

    @Step("click element for {element}")
    public void Click(WebElement element) {
        element.click();
        info("clicked on element :",element.toString());
    }

    @Step("SendKeys With data : {data} for element: {element} ")
    public void SendKeys(WebElement element,String data) {
        element.sendKeys(data);
        info("sent data", data," in element :",element.toString());
    }
    @Step("click using submit for element : {element}")
    public void submit(WebElement element) {

        element.submit();
        info("submitted on element :",element.toString());
    }

    @Step("clear data using clear for element : {element}")
    public void Clear(WebElement element) {

        element.clear();
        info("cleared the data from element :",element.toString());
    }

    @Step(" get title method ")
    public String getTitle(WebDriver driver){

        String title = driver.getTitle();
        info("got page title:",title);
        return title;
    }

    @Step("get text using getText for element : {element}")
    public String getText(WebElement element) {

        String text = element.getText();
        info("got text",text," from element :",element.toString());
        return text;
    }

    @Step("getAttribute text for element: {element} using attributeKey : {data}")
    public String getAttribute(WebElement element,String data) {

        String text =  element.getAttribute(data);
        info("got attribute used key:", data," from element :",element.toString(),"the output text:",text);
        return text;
    }

    @Step("check displayed using isDisplayed for element : {element}")
    public boolean isDisplayed(WebElement element) {

        boolean displayed = element.isDisplayed();
        info("element is displayed on:",element.toString());
        return displayed;
    }

    @Step("check Enabled using isEnabled for element : {element}")
    public boolean isEnabled(WebElement element) {

        boolean enabled = element.isEnabled();
        info("element is displayed on:",element.toString());
        return enabled;

    }

    @Step("get current url ")
    public String getCurrentUrl(WebDriver driver){
        String url = driver.getCurrentUrl();
        info("got the current URL:",url);
        return  url;
    }


    @Step(" open automation exercise site home page url : {url} ")
    public void navigation(WebDriver driver,String Url){
        driver.navigate().to(Url);
        info("navigated to the URL :", Url);
    }

    //javascript Executor

    // click
    @Step("click by javascript executor for element : {element}")
    public void clickByJE(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        info("clicked on the element by javascript executor:", element.toString());
    }


    /*
    How arguments[0] Works:

arguments is a built-in object in JavaScript that holds all the values passed to the function.

arguments[0] refers to the first parameter passed from Java (in this case, pixels).

If you passed more values, you'd access them as arguments[1], arguments[2], etc.
     */

    // scroll by element
    @Step("scroll by javascript executor for element : {element}")
    public void ScrollingByElement(WebDriver driver, WebElement element){
        JavascriptExecutor jsE = (JavascriptExecutor)driver;
        jsE.executeScript("arguments[0].scrollIntoView();",element);
        info("scrolled to the element:", element.toString());

    }

    @Step("scroll by javascript executor by pixels : {pixel}")
    public void ScrollingByPixelsXYAxes(WebDriver driver, int XPixels, int YPixels){
        JavascriptExecutor jsE = (JavascriptExecutor)driver;
        jsE.executeScript("window.scrollBy(arguments[0],arguments[1])",XPixels,YPixels);
        info("Scrolled to specific area in x axis:"+ XPixels+ ", y axis :" + YPixels);
     //   System.out.println(jsE.executeScript("return window.pageYOffset;"));
    }

     @Step("get message by Javascript Executor of element: {element}")
    public String getTextByJSExecutor(WebDriver driver,WebElement element){
        JavascriptExecutor jsE = (JavascriptExecutor) driver;
      String text= (String) jsE.executeScript("return arguments[0].validationMessage",element);
         info("got text:", text,"from the element by javascript executor:", element.toString());
         return text;
    }  // (String) to cast the object to string because it returns object

    // Select By Visible Text
    @Step("select by select class for element : {element} by value : {value}")
    public void selectByVisibleText(WebElement element ,String value){
        Select select = new Select(element);
        select.selectByVisibleText(value);
        info("select text by value :",value," on element:", element.toString());
    }

    //  connection for Broken Links
    @Step("check connection for Url : {Url}")
    public int checkConnection(String Url){
        int code = -1;
      try {URL url = new URL(Url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
       try{
           connection.connect();
          }
       catch (Exception e){
           error("connection times out error happens:",Url,"exception message:",e.getMessage());
       }
       code = connection.getResponseCode();
       return code;
      }
      catch (Exception e) {
          error("the code response of connection for brokenLinks is invalid","exception Message:",e.getMessage());
      }
        System.out.println("connection response code: "+ code);
        return code;
    }

    // check broken links

    @Step("check broken links for elements with locator: {locator} ,with attribute key:{attributeKey}")
    public int getBrokenUrl(WebDriver driver,By locator,String attributeKey){
        try{
           WebElement element = getElementFromList(driver, locator);
            String url = getAttribute(element, attributeKey);
            int responseCode = checkConnection(url);
            if (responseCode >= 400)
                error("this element is broken with text:", getText(element), "response code:" + responseCode, "Url:", url);
             } catch (Exception e) {
            error("the broken links are invalid","exception Message:",e.getMessage());
        }
        return 0;
    }

    //WebElement
    @Step("use webElement by locator {locator}")
    public WebElement element(WebDriver driver,By locator){
        WebElement element = driver.findElement(locator);
        info("create Web element from locator:",locator.toString());
        return element;
    }

    //WebElements

    List <WebElement> elements = new ArrayList<>();

    // get Visible Element From List
    @Step("use webElements by locator : {locator}")
    public WebElement getElementFromList(WebDriver driver, By locator)
    {
         elements = driver.findElements(locator);
         for (WebElement element : elements) {
             try{ WebElement Element= explicitWaitWithWebElementVisibility(element, driver, 5);
          info("got element:",element.toString(),"from elements:",elements.toString());
          return Element;
         }
         catch (Exception e) {
            error("element not visible within timeout so try the next element. ","exception message:",e.getMessage());
        }
             }
        error("there is no elements in the list:",elements.toString());
        return null;
    }

    // get Elements Size
    @Step("use webElements to get elementsSize by locator: {locator}")
    public int getElementsSize(WebDriver driver,By locator){
        elements = driver.findElements(locator);
         int size = elements.size();
        info("got elements:",elements.toString(),"and elements size are : "+ size);
        return size;
    }

    // get Last Element In The List
    @Step("use webElements to get last element by locator: {locator}")
    public WebElement getLastElement(WebDriver driver,By locator){
        elements = driver.findElements(locator);
        WebElement element = elements.getLast();
        info("got last element:",element.toString(),"from elements:",elements.toString());
        return element;
    }

    @Step("use webElements to get first element by locator: {locator}")
    public WebElement getFirstElement(WebDriver driver,By locator){
        elements = driver.findElements(locator);
        WebElement element = elements.getFirst();
        info("got last element:",element.toString(),"from elements:",elements.toString());
        return element;
    }

    //Explicit wait
    @Step("use explicitWaitWithByClickable by locator: {locator} for {seconds} seconds")
    public WebElement explicitWaitWithByClickable (By locator , WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element= wait.until(ExpectedConditions.elementToBeClickable(locator));
        info("waited for:"+ seconds,"seconds for element:",element.toString(),"to be clickable");
        return element;
    }

    @Step("use explicitWaitWithWebElementClickable by element: {element} for {seconds} seconds")
    public WebElement explicitWaitWithWebElementClickable (WebElement element , WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement Element = wait.until(ExpectedConditions.elementToBeClickable(element));
        info("waited for:"+ seconds,"seconds for element:",element.toString(),"to be clickable");
        return Element;
    }

    @Step("use explicitWaitWithByVisibility by locator: {locator} for {seconds} seconds")
    public WebElement explicitWaitWithByVisibility (By locator , WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        info("waited for:"+ seconds,"seconds for element:",element.toString(),"to be visible");
        return element;
    }

    @Step("use explicitWaitWithWebElementVisibility by element: {element} for {seconds} seconds")
    public WebElement explicitWaitWithWebElementVisibility (WebElement element , WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement Element = wait.until(ExpectedConditions.visibilityOf(element));
        info("waited for:"+ seconds,"seconds for element:",element.toString(),"to be visible by element:",element.toString());
        return Element;
    }

    @Step("use explicitWaitWithByClickable by locator: {locator} for {seconds} seconds")
    public List<WebElement> explicitWaitWithByPresence (By locator , WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        List<WebElement> elements= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        info("waited for:"+ seconds,"seconds for element:",elements.toString(),"to be presence");
        return elements;
    }


    @Step("return back to the previous page")
    public void returnBack(WebDriver driver){
        driver.navigate().back();
        info("returned back to the previous page");
    }

    public void hover(WebDriver driver,WebElement element){
        new Actions(driver).moveToElement(element).perform();
        info("hovered over an element:", element.toString());
    }

    // targetFile = the file you’re specifically waiting for
    //folder = the directory you want to list files from
    public int waitAndCheckFilesNumber(File file,File folder,int totalWaitSeconds,int pollingSeconds,int filesNumber){
        FluentWait<File> wait = new  FluentWait<File>(file)
                .withTimeout(Duration.ofSeconds(totalWaitSeconds)).pollingEvery(Duration.ofSeconds(pollingSeconds))
                .withMessage("the file is not downloaded");
        boolean isDownloaded = wait.until(F -> F.exists()&& F.canRead());
        if(isDownloaded){
            info("the file is completely downloaded");
        }else {
            info("the file is not completely downloaded yet");
        }
            File [] files =folder.listFiles();
            if(files.length==filesNumber){
              return files.length;
            }
        error("the file is not downloaded successfully");
        return 0;
    }

    public List<String> waitAndCheckFilesName(File targetFile,File folder,int totalWaitSeconds,int pollingSeconds){
        FluentWait<File> wait = new FluentWait<File>(targetFile)
                .withTimeout(Duration.ofSeconds(totalWaitSeconds)).pollingEvery(Duration.ofSeconds(pollingSeconds))
                .ignoring(Exception.class).withMessage("the file is not downloaded");
        boolean isDownloaded = wait.until(F -> F.exists() && F.canRead());
        if(isDownloaded){
            info("the file is completely downloaded");
        }else {
            error("the file is not completely downloaded yet");
        }
           File [] files = folder.listFiles();
            List<String> filesName = new ArrayList<>();
            for(File file:files){
                filesName.add(file.getName());
            }
            info("the file is downloaded successfully");
        return filesName;
    }

    public List<WebElement> getWebElements(WebDriver driver, By locator,int seconds){
         elements = explicitWaitWithByPresence(locator,driver,seconds);
         List<WebElement> Items = new ArrayList<>();
         for (WebElement element:elements){
             Items.add(element);
         }
        return Items;
    }
}
