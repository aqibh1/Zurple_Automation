/**
 * 
 */
package resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class TestRailResultsListener implements ITestListener{

	String l_testRail_Url = "";
	String l_testRail_username = "";
	String l_testRail_password = "";
	String l_testcase_id = "";
	String l_testrun_id = "";
	String l_scenario_name = "";
	private String l_map_key = "";
	
	private static HashMap<Long, HashMap<String, String>> thread_hash = new HashMap<Long,HashMap<String,String>>();
	private static HashMap<String,String> test_ids_hash = new HashMap<String,String>(); 
	private static HashMap<String,String> tests_executed = new HashMap<String,String>(); 

	
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
//		String className[] = result.getTestClass().toString().split("\\.");
//		String mapKey = className[className.length-1].replace("]", ".")+result.getName();
		String mapKey = getMapKey(result);
		l_testcase_id =getTestCaseId(mapKey,result);
		populateTestHash(getMapKey(), l_testcase_id);
		populateThreadHash(Thread.currentThread().getId(), test_ids_hash);
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
//		l_testrun_id ="156";
		AutomationLogger.info("--PASS--");
		AutomationLogger.info("Initializing TestRail URL :: "+l_testRail_Url);
		AutomationLogger.info("SCENARIO NAME :: "+l_scenario_name);
		AutomationLogger.info("TEST NAME :: "+result.getName());
		AutomationLogger.info("TESTCASE ID :: "+l_testcase_id);
		AutomationLogger.info("TEST RUN ID :: "+l_testrun_id);
		
		AutomationLogger.info("Success Test :: "+getMapKey()+" Thread ID ::"+Thread.currentThread().getId());
		String testcase_id_final = getTestCaseId(Thread.currentThread().getId(),getMapKey());
		if(l_testrun_id!=null && !l_testrun_id.isEmpty() && testcase_id_final!=null && !testcase_id_final.isEmpty()) {
			JSONObject resultObj = composeResults(1, l_scenario_name, "",testcase_id_final);
			if(resultObj!=null) {
				if(getTestExecuted(getMapKey())==null) {
					setTestsExecuted(getMapKey());
					postResults(resultObj);
				}	
			}else {
				AutomationLogger.error("Unable to compose Test Rail result object");
			}
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		AutomationLogger.info("--FAIL--");
		AutomationLogger.info("Initializing TestRail URL :: "+l_testRail_Url);
		AutomationLogger.info("SCENARIO NAME :: "+l_scenario_name);
		AutomationLogger.info("TEST NAME :: "+result.getName());
		AutomationLogger.info("TESTCASE ID :: "+l_testcase_id);
		AutomationLogger.info("TEST RUN ID :: "+l_testrun_id);
		
		AutomationLogger.info("FAILED Test :: "+getMapKey()+" Thread ID ::"+Thread.currentThread().getId());
		String testcase_id_final = getTestCaseId(Thread.currentThread().getId(),getMapKey());
		String errorMessage = result.getThrowable().getLocalizedMessage();
		if(errorMessage.length()>230) {
			errorMessage = result.getThrowable().getLocalizedMessage().substring(0, 230);
		}if(l_testrun_id!=null && !l_testrun_id.isEmpty() && testcase_id_final!=null && !testcase_id_final.isEmpty()) {
			JSONObject resultObj = composeResults(5, l_scenario_name,errorMessage,testcase_id_final);
			if(resultObj!=null) {
				if(getTestExecuted(getMapKey()).isEmpty()) {
					setTestsExecuted(getMapKey());
					postResults(resultObj);
				}			
			}else {
				AutomationLogger.error("Unable to compose Test Rail result object");
			}
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		AutomationLogger.info("--SKIPPED--");
		AutomationLogger.info("Initializing TestRail URL :: "+l_testRail_Url);
		AutomationLogger.info("SCENARIO NAME :: "+l_scenario_name);
		AutomationLogger.info("TEST NAME :: "+result.getName());
		AutomationLogger.info("TESTCASE ID :: "+l_testcase_id);
		AutomationLogger.info("TEST RUN ID :: "+l_testrun_id);
		
		AutomationLogger.info("SKIPPED Test :: "+getMapKey()+" Thread ID ::"+Thread.currentThread().getId());
		String testcase_id_final = getTestCaseId(Thread.currentThread().getId(),getMapKey());
		if(l_testrun_id!=null && !l_testrun_id.isEmpty()) {
			if(testcase_id_final!=null && !testcase_id_final.isEmpty()) {
				JSONObject resultObj = composeResults(4, l_scenario_name, "",testcase_id_final);
				if(resultObj!=null) {
					if(getTestExecuted(getMapKey()).isEmpty()) {
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


	}
	@Override
	public void onFinish(ITestContext context) {

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
//				writeJsonToFile("\\resources\\testrail_mapping\\results.json",resultDetails);
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
//		writeJsonToFile("\\resources\\testrail_mapping\\results.json",pResultObj);
		boolean isUpdatedSuccessfully = true;
		APIClient client = new APIClient(l_testRail_Url);
		client.setUser(l_testRail_username);
		client.setPassword(l_testRail_password);
		try {
			AutomationLogger.info("Result Composed ::"+pResultObj.toString());
//			Thread.sleep(3000);
			Object post_results = client.sendPost("/add_results_for_cases/"+l_testrun_id, pResultObj);	
			if(post_results.toString().contains("status_id")) {
				AutomationLogger.info("Call is Successful");
				AutomationLogger.info("API RESPONSE ---- "+post_results.toString());
			}else {
				AutomationLogger.fatal(post_results.toString());
			}
			AutomationLogger.info(post_results.toString());
//			isUpdatedSuccessfully = verifyStatusIsUpdated(pResultObj);
		} catch (IOException | APIException /* | InterruptedException */ e) {
			AutomationLogger.fatal("API EXCEPTION");
			AutomationLogger.fatal(e.getMessage());
			isUpdatedSuccessfully = false;
		} 
		return isUpdatedSuccessfully;
	}

	private void populateThreadHash(long pThreadId, HashMap<String,String> pTestIdsMap) {
		thread_hash.put(pThreadId, pTestIdsMap);	
	}
	private void populateTestHash(String pTestName, String pTestId) {
		AutomationLogger.info("Populating Test Case Ids Map"+"\n Test Name::"+pTestName+"\n Test ID::"+pTestId);
		if(pTestId!=null && !pTestId.isEmpty()) {
			test_ids_hash.put(pTestName, pTestId);
		}else {
			writeStringToFile("\\resources\\testrail_mapping\\missing_tests.txt",pTestName);
			AutomationLogger.fatal("Unable to populate Hash Map");
		}
	}
	private String getTestCaseId(long pThreadId, String pTestName) {
		HashMap<String, String> testIdsHash = thread_hash.get(pThreadId);
		return testIdsHash.get(pTestName);
	}
	private String getMapKey(ITestResult result) {
		String className[] = result.getTestClass().toString().split("\\.");
		String mapKey = className[className.length-1].replace("]", ".")+result.getName();
		return mapKey;
	}
	private void writeStringToFile(String pFileToWrite, String pObjectToWrite) {
		File lFilePath = new File(System.getProperty("user.dir")+pFileToWrite);
		try (FileWriter file = new FileWriter(lFilePath,true)) {
			AutomationLogger.info("Writing json to file "+pFileToWrite);
			file.write(pObjectToWrite+"\n");
			file.flush();

		} catch (IOException e) {
			AutomationLogger.fatal("Unable to write file "+pFileToWrite);
		}
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
	protected void writeJsonToFile(String pFileToWrite, JSONObject pObjectToWrite) {
    	File lFilePath = new File(System.getProperty("user.dir")+pFileToWrite);
    	boolean isEmptyFile = lFilePath.length()==0?true:false;
    	try (FileWriter file = new FileWriter(lFilePath,true)) {
    		AutomationLogger.info("Writing json to file "+pFileToWrite);
    		if(!isEmptyFile) {
    			file.write(",");
    		}
    		file.write(toPrettyFormat(pObjectToWrite.toString()));
    		file.flush();

    	} catch (IOException e) {
    		AutomationLogger.fatal("Unable to write file "+pFileToWrite);
    	}
    }
	 private String toPrettyFormat(String pJsonString) 
	    {
	        JsonParser parser = new JsonParser();
	        JsonObject json = parser.parse(pJsonString).getAsJsonObject();
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String prettyJson = gson.toJson(json);

	        return prettyJson;
	    }
}
