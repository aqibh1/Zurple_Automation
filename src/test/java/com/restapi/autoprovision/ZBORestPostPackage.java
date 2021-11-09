package com.restapi.autoprovision;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.HTTPConstants;
import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import com.restapi.Part;
import com.restapi.Part.PartType;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

/**
 * @author ahabib
 *
 */
public class ZBORestPostPackage extends RestAPITest{
	private JSONObject dataObject;
	String lDataFile ="";
	
	@Test(priority=237)
	@Parameters({"packageDataFile"})
	public void testPostPackage(String pDataFile) throws Exception {
		getDriver();
		lDataFile = pDataFile;
		HashMap<String,String> ua = new HashMap<String, String>();
		//TODO This should be initiliazed in RestApi constants
		ua.put("User-Agent", HTTPConstants.UserAgent);
		JSONObject responseObj = null;
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/createpackage";
		request.setUrl(lUrl);
		request.setRestContent(getContent());
		request.setHeaders(ua);
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		responseObj = response.getJsonResponse();
		assertTrue(validateMapResp(response),"Unable to verify the package response..");
	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		status = httpCallResp.getJsonResponse().optString("message").equalsIgnoreCase("Success");
		String lFileToWrite = "/resources/cache/cache-ap-package-id-data.json";
		String lPFileToWrite = "/resources/cache/permanent-ap-package-admin-data.json";
		JSONObject jObject = httpCallResp.getJsonResponse();	
		//TODO We need to first verify that status code is 200. Than will check status. Line 62 should be after 200 status check.
		if(httpCallResp.getStatus() == Integer.parseInt(dataObject.optString("status_code"))) {
			if(status) {
				String package_id = jObject.optString("package_id").toString();
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAPPackageId, package_id);
				if(!package_id.isEmpty()) {
					AutomationLogger.info("Package id is:"+package_id);
					writeJsonToFile(lFileToWrite,jObject);
					writeJsonToFile(lPFileToWrite,jObject);
				}
			}
		}
		return status;
	}
	
	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		Map<String, Part> multiParts = new HashMap<String, Part>();
		//TODO Pass this path from datafile
		String packageDataFile = "/resources/cache/cache-ap-package-data.json";
		String lName = updateName(dataObject.optString("name"));
		String lPhone = dataObject.optString("phone");
		String lEmail = updateEmail(dataObject.optString("email"));
		String lPath = dataObject.optString("path");
		String lBundle = dataObject.optString("bundle");
		String lMultiBundle = dataObject.optString("multi_bundle");
		String lAdditionalAdmins = dataObject.optString("additional_admins");
		String lPayers = dataObject.optString("payers");
		String lSubsidiary = dataObject.optString("subsidiary");
		//TODO The variable name should be access token since we are already differnt configs files for prod and stage we do not require this check
		//String access_token = getIsProd()?EnvironmentFactory.configReader.getPropertyByName("prod_access_token"):EnvironmentFactory.configReader.getPropertyByName("stage_access_token");
		String access_token = EnvironmentFactory.configReader.getPropertyByName("ap_access_token");
		multiParts.put("name", new Part(lName, PartType.STRING));
		multiParts.put("phone", new Part(lPhone, PartType.STRING));
		multiParts.put("email", new Part(lEmail, PartType.STRING));
		multiParts.put("path", new Part(lPath, PartType.STRING));
		multiParts.put("bundle", new Part(lBundle, PartType.STRING));
		multiParts.put("multi_bundle", new Part(lMultiBundle, PartType.STRING));
		multiParts.put("additional_admins", new Part(lAdditionalAdmins, PartType.STRING));
		multiParts.put("payers", new Part(lPayers, PartType.STRING));
		multiParts.put("subsidiary", new Part(lSubsidiary, PartType.STRING));
		multiParts.put("access_token", new Part(access_token, PartType.STRING));
	
		emptyFile(packageDataFile,"");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(System.getProperty("user.dir")+packageDataFile), multiParts);
		
		restContent.setParts(multiParts);
		restContent.setMultiPart(true);
		AutomationLogger.info(restContent.getBody());
		return restContent;
	}
}
