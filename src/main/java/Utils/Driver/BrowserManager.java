package Utils.Driver;
import org.openqa.selenium.WebDriver;
import static Utils.Driver.BrowserOptions.getChromeOptions;
import static Utils.Driver.BrowserOptions.getEdgeOptions;

public class BrowserManager {

    public static WebDriver  getBrowser (String browserName){
        if (browserName.equalsIgnoreCase("chrome"))
           {
            return getChromeOptions();
           }
        else
           {
            return getEdgeOptions();
           }
    }

}


