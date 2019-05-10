package resources.ZurpleReporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import resources.ConfigReader;

import java.io.File;
import java.io.IOException;

public class ScreenshotTaker  extends TestListenerAdapter {

    static ConfigReader configReader = ConfigReader.load();

    public static String getScreenshotName(ITestResult result){

//        String screenshotName = configReader.getPropertyByName("zurple_reporter_screenshot_dir") + configReader.getPropertyByName("base_browser") + "_" + result.getName() + "_" + result.hashCode() + ".png";
        String screenshotName = configReader.getPropertyByName("reporter_screenshot_dir") + configReader.getPropertyByName("base_browser") + "_" + result.getName() + "_" + result.hashCode() + ".png";

    	return  screenshotName;
        
    }

    @Override
    public void onTestFailure(ITestResult result)
    {

        Object currentClass = result.getInstance();

        if ( !(currentClass instanceof resources.AbstractPageTest) ){
            return;
        }

        WebDriver webDriver = ((resources.AbstractPageTest) currentClass).getDriver();

        if (webDriver != null)
        {
            TakesScreenshot ts = (TakesScreenshot) webDriver;
            File source = ts.getScreenshotAs(OutputType.FILE);
//            String dest = configReader.getPropertyByName("zurple_reporter_output_dir") + getScreenshotName(result);
            String dest = configReader.getPropertyByName("reporter_output_dir") + getScreenshotName(result);
            File destination = new File(dest);
            
            String base64ScreenshotEmail = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	                getScreenshotAs(OutputType.BASE64);
            try {
                FileUtils.copyFile(source, destination);
//                Reporter.log("<a href='"+ destination.getAbsolutePath() + "'> <img src='"+ destination.getAbsolutePath() + "' height='100' width='100'/> </a>");
                Reporter.log("<a href='"+ base64ScreenshotEmail + "'> <img src='"+ base64ScreenshotEmail + "' height='100' width='100'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
