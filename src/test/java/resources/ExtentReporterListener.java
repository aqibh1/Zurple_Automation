package resources;


import java.io.IOException;
import java.lang.reflect.Method;

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
//import com.relevantcodes.extentreports.LogStatus;
import resources.extentreports.ExtentManager;
import resources.utility.AutomationLogger;

public class ExtentReporterListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentReportResults.html");
	private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
	
    @Override
	public synchronized void onStart(ITestContext context) {
    	ExtentTest parent = extent.createTest(context.getSuite().getName());
    	parentTest.set(parent);
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
        test.set(child);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		((ExtentTest) test.get()).pass("Test passed");
		((ExtentTest) test.get()).pass(result.getTestContext().getCurrentXmlTest().getName());
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		String errorMessage = result.getThrowable().getLocalizedMessage();
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
	        String base64ScreenshotEmail = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
	        if(errorMessage==null) {
	        	errorMessage = result.getThrowable().toString();
	        }
			try {
				((ExtentTest) test.get()).fail(result.getName()).addScreenCaptureFromPath(base64ScreenshotEmail);
				((ExtentTest) test.get()).fail(result.getTestContext().getCurrentXmlTest().getName()+" Error Message: "+errorMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		((ExtentTest) test.get()).skip(result.getThrowable());
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
