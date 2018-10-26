package resources.ZurpleReporter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

public class ScreenshotTaker  extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result)
    {
        Object currentClass = result.getInstance();
        WebDriver webDriver = ((resources.AbstractPageTest) currentClass).getDriver();

        if (webDriver != null)
        {

            File f = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

            //etc.
        }
    }

}
