package resources.extentreports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {
	
	static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();
 
    public static synchronized ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
 
    public static synchronized void endTest() {
        extent.endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }
 
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
    
  //driver and screenshotName
    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
                    //below line is just to append the date format with the screenshot name to avoid duplicate names 
                    String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
     TakesScreenshot ts = (TakesScreenshot) driver;
     File source = ts.getScreenshotAs(OutputType.FILE);
                    //after execution, you could see a folder "FailedTestsScreenshots" under src folder
     String destination = System.getProperty("user.dir") + "/target/surefire-reports/screenshots/"+screenshotName+dateName+".png";
     File finalDestination = new File(destination);
     FileUtils.copyFile(source, finalDestination);
                    //Returns the captured file path
     return destination;
    }
   
}
