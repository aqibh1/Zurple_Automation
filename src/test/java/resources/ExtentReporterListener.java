package resources;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;
import resources.extentreports.ExtentManager;
import resources.extentreports.ExtentTestManager;
import resources.orm.hibernate.HibernateUtil;
import resources.utility.AutomationLogger;

public class ExtentReporterListener implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
		AutomationLogger.info("Refreshing Hibernate Database Connection");
		HibernateUtil.setSessionFactoryEmpty();
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 ExtentTestManager.getTest().log(LogStatus.PASS,result.getName(),"");

		 ExtentTestManager.getTestEmail().log(LogStatus.PASS,result.getName(),"");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String errorMessage = result.getThrowable().getLocalizedMessage();
		Object currentClass = result.getInstance();
		 WebDriver webDriver = ((AbstractPageTest) currentClass).getDriver();
	        //Take base64Screenshot screenshot.
		 String base64Screenshot="";
		try {
			base64Screenshot = ExtentTestManager.getScreenshot(webDriver, result.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        String base64ScreenshotEmail = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	                getScreenshotAs(OutputType.BASE64);
	        if(errorMessage==null) {
	        	errorMessage = result.getThrowable().toString();
	        }
//		   ExtentTestManager.getTestEmail().log(LogStatus.FAIL,result.getName(),errorMessage+ExtentTestManager.getTestEmail().addBase64ScreenShot(base64ScreenshotEmail));
		   ExtentTestManager.getTestEmail().log(LogStatus.FAIL,result.getName(),errorMessage);

	        ExtentTestManager.getTest().log(LogStatus.FAIL,result.getName(),errorMessage+ExtentTestManager.getTest().addScreenCapture(base64Screenshot));		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.SKIP,result.getName()+ "-= Skipped =-");
		ExtentTestManager.getTestEmail().log(LogStatus.SKIP,result.getName()+ "-= Skipped =-");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentTestManager.startTest(context.getName(),"");
		ExtentTestManager.startTestEmail(context.getName(),"");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		 ExtentTestManager.endTestEmail();
	      ExtentManager.getEmailReporter().flush();
		 ExtentTestManager.endTest();
	      ExtentManager.getReporter().flush();	
	}
}
