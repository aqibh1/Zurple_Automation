package resources.extentreports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentReports extentEmail;
	 
    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"\\target\\surefire-reports\\ExtentReportResults.html", true);
        }
        return extent;
    }
    public synchronized static ExtentReports getEmailReporter(){
        if(extentEmail == null){
            //Set HTML email reporting file location
            String workingDir = System.getProperty("user.dir");
            extentEmail = new ExtentReports(workingDir+"\\target\\surefire-reports\\ExtentEmailReportResults.html", true);
        }
        return extentEmail;
    }
}
