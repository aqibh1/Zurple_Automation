//package resources.extentreports;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//
//import com.aventstack.extentreports.markuputils.MarkupHelper;
////import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//public class ExtentTestManager {
//	
//	static Map extentTestMap = new HashMap();
//	static Map extentTestEmailMap = new HashMap();
//    static ExtentReports  extent = ExtentManager.getReporter();
//    static ExtentReports extentEmailReporter = ExtentManager.getEmailReporter();
// 
//    public static synchronized ExtentTest getTest() {
//        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
//    }
// 
//    public static synchronized void endTest() {
//        extent.endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
//    }
// 
//    public static synchronized ExtentTest startTest(String testName, String desc) {
//        ExtentTest test = extent.startTest(testName, desc);
//        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
//        return test;
//    }
//    
//    public static synchronized ExtentTest getTestEmail() {
//        return (ExtentTest)extentTestEmailMap.get((int) (long) (Thread.currentThread().getId()));
//    }
// 
//    public static synchronized void endTestEmail() {
//    	extentEmailReporter.endTest((ExtentTest)extentTestEmailMap.get((int) (long) (Thread.currentThread().getId())));
//    }
// 
//    public static synchronized ExtentTest startTestEmail(String testName, String desc) {
//        ExtentTest test = extentEmailReporter.startTest(testName, desc);
//        extentTestEmailMap.put((int) (long) (Thread.currentThread().getId()), test);
//        return test;
//    }
//    
//  //driver and screenshotName
//    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
//                    //below line is just to append the date format with the screenshot name to avoid duplicate names 
//                    String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//     TakesScreenshot ts = (TakesScreenshot) driver;
//     File source = ts.getScreenshotAs(OutputType.FILE);
//     String relitive_path = "\\target\\surefire-reports\\"+screenshotName+dateName+".png";
//                    //after execution, you could see a folder "FailedTestsScreenshots" under src folder
//     String destination = System.getProperty("user.dir") + relitive_path;
//     File finalDestination = new File(destination);
//     FileUtils.copyFile(source, finalDestination);
//                    //Returns the captured file path
//     
//     
//     return screenshotName+dateName+".png";
//    }
//   
//}
