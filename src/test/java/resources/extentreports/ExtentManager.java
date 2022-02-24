package resources.extentreports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import resources.AbstractPageTest;

public class ExtentManager {
    
    private static ExtentReports extent;
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance("test-output/extent.html");
    	
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }
    
    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names 
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String relitive_path = "\\target\\surefire-reports\\"+screenshotName+dateName+".png";
		        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + relitive_path;
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
		return screenshotName+dateName+".png";
}
    
    public static String getDetails() {
        String nameOS = "os.name";
        String versionOS = "os.version";
        String architectureOS = "os.arch";

        nameOS = System.getProperty(nameOS);
        versionOS = System.getProperty(versionOS);
        architectureOS = System.getProperty(architectureOS);
        
        return nameOS+" OS Version: "+versionOS+" OS Architecture: "+architectureOS;
       }
  
}
//	private static ExtentAventReporter extent;
//	 
//    public synchronized static ExtentAventReporter getReporter(){
//    	ExtentAventReporter extentEmail = null;
//        if(extent == null){
//            //Set HTML reporting file location
//            String workingDir = System.getProperty("user.dir");
//            //ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(workingDir+"\\target\\surefire-reports\\ExtentReportResults.html");
//            extent = new ExtentAventReporter(workingDir+"\\target\\surefire-reports\\ExtentReportResults.html");
//            //extent.attachReporter(htmlReporter);
//            //htmlReporter.config().setTheme(Theme.STANDARD);
//        }
//        return extent;
//    }
//    public synchronized static ExtentEmailReporter getEmailReporter(){
//    	ExtentEmailReporter extentEmail = null;
//        if(extentEmail == null){
//            //Set HTML email reporting file location
//            String workingDir = System.getProperty("user.dir");
//            //ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(workingDir+"\\target\\surefire-reports\\ExtentEmailReportResults.html");
//            //extent.attachReporter(htmlReporter);
//            extentEmail = new ExtentEmailReporter(workingDir+"\\target\\surefire-reports\\ExtentEmailReportResults.html");
//        }
//        return extentEmail;
//    }

