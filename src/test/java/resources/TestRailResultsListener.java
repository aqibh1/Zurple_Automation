/**
 * 
 */
package resources;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class TestRailResultsListener implements ITestListener{

	String l_testRail_Url = "";
	String l_testRail_username = "";
	String l_testRail_password = "";
	int l_testcase_id = 0;
	String l_testrun_id = "";
	String l_scenario_name = "";
	
	@Override
	public void onTestStart(ITestResult result) {
		l_testRail_Url = EnvironmentFactory.configReader.getPropertyByName("testrail_url");
		l_testRail_username = EnvironmentFactory.configReader.getPropertyByName("testrail_username");
		l_testRail_password = EnvironmentFactory.configReader.getPropertyByName("testrail_password");
		String className[] = result.getTestClass().toString().split("\\.");
		String mapKey = className[className.length-1].replace("]", ".")+result.getName();
		l_testcase_id =Integer.parseInt(EnvironmentFactory.configReader.getTestRailMapping(mapKey));
		l_testrun_id = System.getProperty("testrail_testrun_id");
		l_scenario_name = result.getTestContext().getCurrentXmlTest().getName();;
		AutomationLogger.info("Initializing TestRail URL :: "+l_testRail_Url);
		AutomationLogger.info("SCENARIO NAME :: "+l_scenario_name);
		AutomationLogger.info("TEST NAME :: "+result.getName());
		AutomationLogger.info("TESTCASE ID :: "+l_testcase_id);
		AutomationLogger.info("TEST RUN ID :: "+l_testrun_id);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		updateResultsOnTestRails(1, l_scenario_name, "");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		List<String> repoterMessageList = Reporter.getOutput(result);
		String errorMessage = result.getThrowable().getLocalizedMessage();
		if(errorMessage.length()>230) {
			errorMessage = result.getThrowable().getLocalizedMessage().substring(0, 230);
		}
		updateResultsOnTestRails(5, l_scenario_name,errorMessage);
	        
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		updateResultsOnTestRails(3, l_scenario_name, "");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
	
		
	}

	@Override
	public void onFinish(ITestContext context) {
	
	}

	private boolean updateResultsOnTestRails(int pStatus, String pComments, String pDefects) {
		boolean isUpdatedSuccessfully = true;
		APIClient client = new APIClient(l_testRail_Url);
		client.setUser(l_testRail_username);
		client.setPassword(l_testRail_password);
		
		JSONObject resultObject = new JSONObject();
		JSONArray jResultsArray = new JSONArray();
		JSONObject resultDetails = new JSONObject();
		resultDetails.put("status_id", pStatus);
		resultDetails.put("comment", pComments);
		resultDetails.put("defects",pDefects);
		
		resultDetails.put("case_id", l_testcase_id);
		jResultsArray.put(0, resultDetails);
		resultObject.put("results", jResultsArray);
		try {
			client.sendPost("/add_results_for_cases/"+l_testrun_id, resultObject);
		} catch (IOException | APIException e) {
			// TODO Auto-generated catch block
			AutomationLogger.error(e.getMessage());
			isUpdatedSuccessfully = false;
		} 
		return isUpdatedSuccessfully;
	}

}
