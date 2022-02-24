/**
 * 
 */
package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import resources.extentreports.ExtentManager;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
/**
 * @author darrraqi
 *
 */
public class TestRailAndExtentReportListener implements ITestListener{

	String l_testRail_Url = "";
	String l_testRail_username = "";
	String l_testRail_password = "";
	String l_testcase_id = "";
	String l_testrun_id = "";
//	String l_scenario_name = "";
	private String l_map_key = "";

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentReportResults.html");
	private static ExtentReports emailExtent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentEmailReportResults.html");
	private static ThreadLocal<ExtentTest> emailTest = new ThreadLocal();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();
	private static HashMap<String,String> tests_executed = new HashMap<String,String>(); 
	private static List<String> failed_test_list = new ArrayList<String>();
	
    ExtentTest testlog;

	
	private String getTestCaseId(String pMapKey, ITestResult pResult) {
		String l_scenario_name = pResult.getTestContext().getCurrentXmlTest().getName();
		String l_testcase_id_ = "";
		try {
			l_testcase_id_ = EnvironmentFactory.configReader.getTestRailMapping(pMapKey).trim();
			if(l_testcase_id_!=null && !l_testcase_id_.isEmpty()) {
				AutomationLogger.info("Fetching test case ID "+l_testcase_id_);
				setMapKey(pMapKey);
			}else {
				AutomationLogger.info("Fetching test case ID by Scenario name");
				l_scenario_name = l_scenario_name.replace(" ", "_");
				String scenario_mapped_key = l_scenario_name+"_"+pMapKey;
				l_testcase_id_ = EnvironmentFactory.configReader.getTestRailMapping(scenario_mapped_key).trim();	
				setMapKey(scenario_mapped_key);
			}
		}catch(Exception ex) {
			AutomationLogger.error("The test case is not mapped");
			l_testcase_id_ = "";
		}
		return l_testcase_id_;
	}

	@Override
	public void onTestStart(ITestResult result) {
    
		AutomationLogger.info("Thread ID::"+Thread.currentThread().getId());
		l_testRail_Url = EnvironmentFactory.configReader.getPropertyByName("testrail_url");
		l_testRail_username = EnvironmentFactory.configReader.getPropertyByName("testrail_username");
		l_testRail_password = EnvironmentFactory.configReader.getPropertyByName("testrail_password");
		String mapKey = getMapKey(result);
		l_testcase_id =getTestCaseId(mapKey,result);
		l_testrun_id = System.getProperty("testrail_testrun_id");
		String l_scenario_name = result.getTestContext().getCurrentXmlTest().getName();;
		AutomationLogger.info("Initializing TestRail URL :: "+l_testRail_Url);
		AutomationLogger.info("SCENARIO NAME :: "+l_scenario_name);
		AutomationLogger.info("TEST NAME :: "+result.getName());
		AutomationLogger.info("TESTCASE ID :: "+l_testcase_id);
		AutomationLogger.info("TEST RUN ID :: "+l_testrun_id);
 	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if(!result.getName().equalsIgnoreCase("testBackOfficeLogin") && !result.getName().equalsIgnoreCase("closeBrowser")) {

			test.get().pass(result.getName());
			emailTest.get().pass(result.getName());

			String mapKey = getMapKey(result);
			String success_map_id =getTestCaseId(mapKey,result);
			AutomationLogger.info("--PASS-- "+result.getName()+" Thread ID::"+Thread.currentThread().getId()+" TEST ID ::"+success_map_id);
			AutomationLogger.info("Success Test :: "+getMapKey()+" Thread ID ::"+Thread.currentThread().getId());
			String l_scenario_name = result.getTestContext().getCurrentXmlTest().getName();
			if(l_testrun_id!=null && !l_testrun_id.isEmpty() && success_map_id!=null && !success_map_id.isEmpty()) {
				JSONObject resultObj = composeResults(1, l_scenario_name, "",success_map_id);
				if(resultObj!=null) {
					AutomationLogger.info("JSON To Post :: "+resultObj.toString());
					if(getTestExecuted(getMapKey())==null) {
						setTestsExecuted(getMapKey());
						postResults(resultObj);
					}	
				}else {
					AutomationLogger.error("Unable to compose Test Rail result object");
				}
			}
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {	
		String mapKey = getMapKey(result);
		String success_map_id =getTestCaseId(mapKey,result);
		AutomationLogger.info("--FAIL-- "+result.getName()+" Thread ID::"+Thread.currentThread().getId()+" TEST ID ::"+success_map_id);
		AutomationLogger.info("FAIL Test :: "+getMapKey()+" Thread ID ::"+Thread.currentThread().getId());
		String l_scenario_name = result.getTestContext().getCurrentXmlTest().getName();
		setFailedTestList(l_scenario_name);
		String errorMessage = result.getThrowable().getLocalizedMessage();
		if(errorMessage.length()>230) {
			errorMessage = result.getThrowable().getLocalizedMessage().substring(0, 230);
		}if(l_testrun_id!=null && !l_testrun_id.isEmpty() && success_map_id!=null && !success_map_id.isEmpty()) {
			JSONObject resultObj = composeResults(5, l_scenario_name,errorMessage,success_map_id);
			if(resultObj!=null) {
				AutomationLogger.info("JSON To Post :: "+resultObj.toString());
				if(getTestExecuted(getMapKey())==null) {
					setTestsExecuted(getMapKey());
					postResults(resultObj);
				}			
			}else {
				AutomationLogger.error("Unable to compose Test Rail result object");
			}
		}
		
		errorMessage = result.getThrowable().getLocalizedMessage();
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
//		String errorMessage = "";
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if(result.getThrowable().toString().contains("depends on")) {
			test.get().skip(result.getName());
			emailTest.get().skip(result.getName());
		}
		
		String mapKey = getMapKey(result);
		String success_map_id =getTestCaseId(mapKey,result);
		AutomationLogger.info("--SKIP-- "+result.getName()+" Thread ID::"+Thread.currentThread().getId()+" TEST ID ::"+success_map_id);
		AutomationLogger.info("SKIP Test :: "+getMapKey()+" Thread ID ::"+Thread.currentThread().getId());
		String l_scenario_name = result.getTestContext().getCurrentXmlTest().getName();
		if(l_testrun_id!=null && !l_testrun_id.isEmpty()) {
			if(success_map_id!=null && !success_map_id.isEmpty()) {
				JSONObject resultObj = composeResults(4, l_scenario_name, "",success_map_id);
				if(resultObj!=null) {
					AutomationLogger.info("JSON To Post :: "+resultObj.toString());
					if(getTestExecuted(getMapKey())==null) {
						setTestsExecuted(getMapKey());
						postResults(resultObj);
					}	
				}else {
					AutomationLogger.error("Unable to compose Test Rail result object");
				}
			}else {
				AutomationLogger.error("Test case id is null for scenario :: "+l_scenario_name);
			}
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onStart(ITestContext context) {
		ExtentTest parent = extent.createTest(context.getCurrentXmlTest().getName(),ExtentManager.getDetails());
    	test.set(parent);
    	ExtentTest emailParent = emailExtent.createTest(context.getCurrentXmlTest().getName());
    	emailTest.set(emailParent);

	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		emailExtent.flush();
	}
	
	private JSONObject composeResults(int pStatus, String pComments, String pDefects, String pTestCaseIds) {
		JSONObject resultObject = new JSONObject();
		try {
			String testcase_ids[] = pTestCaseIds.split(",");
			JSONArray jResultsArray = new JSONArray();
			for(int i=0;i<testcase_ids.length;i++) {
				JSONObject resultDetails = new JSONObject();
				resultDetails.put("status_id", pStatus);
				resultDetails.put("comment", pComments);
				resultDetails.put("defects",pDefects);
				resultDetails.put("case_id", Integer.parseInt(testcase_ids[i].trim()));
				jResultsArray.put(i, resultDetails);
			}
			resultObject.put("results", jResultsArray);
		}catch (Exception ex) {
			AutomationLogger.error(ex.getLocalizedMessage());
			resultObject = null;
		}
		return resultObject;
	}
	private boolean postResults(JSONObject pResultObj) {
		boolean isUpdatedSuccessfully = true;
		APIClient client = new APIClient(l_testRail_Url);
		client.setUser(l_testRail_username);
		client.setPassword(l_testRail_password);
		try {
			AutomationLogger.info("Result Composed ::"+pResultObj.toString());
			Object post_results = client.sendPost("/add_results_for_cases/"+l_testrun_id, pResultObj);	
			if(post_results.toString().contains("status_id")) {
				AutomationLogger.info("Call is Successful");
				AutomationLogger.info("API RESPONSE ---- "+post_results.toString());
			}else {
				AutomationLogger.fatal(post_results.toString());
			}
			AutomationLogger.info(post_results.toString());
		} catch (IOException | APIException /* | InterruptedException */ e) {
			AutomationLogger.fatal("API EXCEPTION");
			AutomationLogger.fatal(e.getMessage());
			isUpdatedSuccessfully = false;
		} 
		return isUpdatedSuccessfully;
	}

	private String getMapKey(ITestResult result) {
		String className[] = result.getTestClass().toString().split("\\.");
		String mapKey = className[className.length-1].replace("]", ".")+result.getName();
		return mapKey;
	}

	public String getMapKey() {
		return l_map_key;
	}public void setMapKey(String pMapKey) {
		l_map_key = pMapKey;
	}

	public static String getTestExecuted(String pTestName) {
		return tests_executed.get(pTestName);
	}
	public static void setTestsExecuted(String pTestName) {
		tests_executed.put(pTestName, "Executed");
	}

	public static List<String> getFailedTestList() {
		return failed_test_list;
	}

	private static void setFailedTestList(String pScenarioName) {
		if(failed_test_list.size()>0) {
			if(!failed_test_list.contains(pScenarioName)) {
				TestRailAndExtentReportListener.failed_test_list.add(pScenarioName);
			}
		}else {
			TestRailAndExtentReportListener.failed_test_list.add(pScenarioName);
		}	
	}
	 
}
