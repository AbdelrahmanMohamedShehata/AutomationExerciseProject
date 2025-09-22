package Utils.Driver;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static Utils.Driver.BrowserManager.getBrowser;
import static Utils.Logs.log4j.info;


public class DriverManager {
    private static final ThreadLocal<WebDriver> threadLocal =  new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver ()
    {
        return threadLocal.get();
    }

    public static void setDriver(WebDriver driver)
    {
        threadLocal.set(driver);
    }

    public static void removeDriver()
    {
        threadLocal.remove();
    }

    @Step("create driver instance : {browser}")
    public static void createInstance(String browserName)
    {
       WebDriver driver = getBrowser(browserName);
       info("Driver initialized on the browser: "+ browserName);
       setDriver(driver);

    }

    @Step("TearDown driver")
    public static void tearDown()
    {
            getDriver().quit();
            removeDriver();
            info("closed the driver successfully");
    }
}
