package resources;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

//import com.relevantcodes.extentreports.LogStatus;
import resources.extentreports.ExtentManager;
import resources.utility.AutomationLogger;

public class ExtentReporterListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentReportResults.html");
	private static ExtentReports emailExtent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentEmailReportResults.html");
	private static ThreadLocal<ExtentTest> emailTest = new ThreadLocal();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();
    ExtentTest testlog;

    @Override
	public synchronized void onStart(ITestContext context) {
    	ExtentTest parent = extent.createTest(context.getCurrentXmlTest().getName(),ExtentManager.getDetails());
    	test.set(parent);
    	ExtentTest emailParent = emailExtent.createTest(context.getCurrentXmlTest().getName());
    	emailTest.set(emailParent);
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
		emailExtent.flush();
		
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		AutomationLogger.startTestCase(result.getName());
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test.get().pass(result.getName());
		emailTest.get().pass(result.getName());
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		String errorMessage = "";
		errorMessage = result.getThrowable().getLocalizedMessage();
		AutomationLogger.onTestPass(result.getName());
		AutomationLogger.error(errorMessage);
		Object currentClass = result.getInstance();
		WebDriver webDriver = ((AbstractPageTest) currentClass).getDriver();
		  //Take base64Screenshot screenshot.
		 String base64Screenshot="";
		try {
			base64Screenshot = ExtentManager.getScreenshot(webDriver, result.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       // String base64ScreenshotEmail = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
	        if(errorMessage==null) {
	        	errorMessage = result.getThrowable().toString();
	        }
			try {
				test.get().fail(result.getName()).info(MarkupHelper.createLabel(errorMessage, ExtentColor.RED)).addScreenCaptureFromPath(base64Screenshot);
				emailTest.get().fail(result.getName()).info(MarkupHelper.createLabel(errorMessage, ExtentColor.RED));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
			if(result.getThrowable().toString().contains("depends on")) {
				test.get().skip(result.getName());
				emailTest.get().skip(result.getName());
			}
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
}

//public class ExtentReporterListener implements ITestListener{
//	
//	@Override
//	public void onTestStart(ITestResult result) {
//		AutomationLogger.startTestCase(result.getName());	
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		 ExtentTestManager.getTest().log(LogStatus.PASS,result.getName(),"");
//		 ExtentTestManager.getTestEmail().log(LogStatus.PASS,result.getName(),"");
//		 AutomationLogger.onTestPass(result.getName());
//	}
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//		String errorMessage = result.getThrowable().getLocalizedMessage();
//		AutomationLogger.onTestPass(result.getName());
//		AutomationLogger.error(errorMessage);
//		Object currentClass = result.getInstance();
//		 WebDriver webDriver = ((AbstractPageTest) currentClass).getDriver();
//	        //Take base64Screenshot screenshot.
//		 String base64Screenshot="";
//		try {
//			base64Screenshot = ExtentTestManager.getScreenshot(webDriver, result.getName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	        String base64ScreenshotEmail = "data:image/png;base64,"+((TakesScreenshot)webDriver).
//	                getScreenshotAs(OutputType.BASE64);
//	        if(errorMessage==null) {
//	        	errorMessage = result.getThrowable().toString();
//	        }
////		   ExtentTestManager.getTestEmail().log(LogStatus.FAIL,result.getName(),errorMessage+ExtentTestManager.getTestEmail().addBase64ScreenShot(base64ScreenshotEmail));
//		   ExtentTestManager.getTestEmail().log(LogStatus.FAIL,result.getName(),errorMessage);
//
//	        ExtentTestManager.getTest().log(LogStatus.FAIL,result.getName(),errorMessage+ExtentTestManager.getTest().addScreenCapture(base64ScreenshotEmail));		
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		ExtentTestManager.getTest().log(LogStatus.SKIP,result.getName()+ "-= Skipped =-");
//		ExtentTestManager.getTestEmail().log(LogStatus.SKIP,result.getName()+ "-= Skipped =-");
//		
//	}
//
//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onStart(ITestContext context) {
//		ExtentTestManager.startTest(context.getName(),"");
//		ExtentTestManager.startTestEmail(context.getName(),"");
//		
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		 ExtentTestManager.endTestEmail();
//	      ExtentManager.getEmailReporter().flush();
//		 ExtentTestManager.endTest();
//	      ExtentManager.getReporter().flush();	
//	}
//}
