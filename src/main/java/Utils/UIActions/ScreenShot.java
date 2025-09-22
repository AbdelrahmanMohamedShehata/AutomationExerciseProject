package Utils.UIActions;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import static Utils.Logs.log4j.error;
import static Utils.Logs.log4j.info;

public class ScreenShot {

    @Step("take screen shot for a file name: {fileName}")
    public static void takeScreenShotAsFile(WebDriver driver, String fileName)
    {
         File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
         String filePath = "test-results/Screenshots-results/" + fileName +"("+timestamp+")" +".png";
        try {
               FileUtils.copyFile(src, new File(filePath));
               info("screenshot saved in: ", filePath);
            }
        catch(Exception e)
        {
            error(" failed to save screenshot in:",filePath,"exception message:",e.getMessage());
        }
    }

    @Step("attach a screenshot to allure report ")
    public static void takeScreenShotAsString(WebDriver driver)
    {
        String src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        try {
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(Base64.getDecoder().decode(src)), ".png");
            info("screenshot saved in allure report");
        }
        catch(Exception e)
        {
            error("failed to attach the screenshot in allure report","exception message:",e.getMessage());
        }
    }
}
