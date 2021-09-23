package com.restapi.autoprovision;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import com.restapi.Part;
import com.restapi.Part.PartType;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.RestValidationAction;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.ZurpleListingConstants;

/**
 * @author ahabib
 *
 */
public class ZBORestPostAdmin extends RestAPITest{
	private JSONObject dataObject;
	String lDataFile ="";

	@Test(priority=238)
	@Parameters({"adminDataFile"})
	public void testPostAdmin(String pDataFile) throws Exception {
		getDriver();
		lDataFile = pDataFile;
		JSONObject responseObj = null;
		HashMap<String,String> ua = new HashMap<String, String>();
		ua.put("User-Agent", "aaqib-useragent");
		String lc_cookie = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/createadmin";
		request.setUrl(lUrl);
		request.setRestContent(getContent());
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lc_cookie));
		request.setHeaders(ua);
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		responseObj = response.getJsonResponse();
		assertTrue(validateMapResp(response),"Unable to verify the admin response..");
	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		status = httpCallResp.getJsonResponse().optString("message").equalsIgnoreCase("Success");
		String lFileToWrite = "/resources/cache/cache-ap-admin-id-data.json";
		String lPFileToWrite = "/resources/cache/permanent-ap-package-admin-data.json";
		emptyFile(lFileToWrite,"");
		JSONObject jObject = httpCallResp.getJsonResponse();		
		if(status) {
				String admin_id = jObject.optString("admin_id").toString();
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAPAdminId, admin_id);
				if(!admin_id.isEmpty()) {
					AutomationLogger.info("Admin id is:"+admin_id);
					writeJsonToFile(lFileToWrite,jObject);
					writeJsonToFile(lPFileToWrite,jObject);
				}
			}
		return status;
	}
	
	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		String adminDataFile = "/resources/cache/cache-ap-admin-data.json";
		Map<String, Part> multiParts = new HashMap<String, Part>();
		String lFname = updateName(dataObject.optString("first_name"));
		String lLname = updateName(dataObject.optString("last_name"));
		String lPhone = dataObject.optString("phone");
		String lFeed = dataObject.optString("feed_id");
		String lAgentId = dataObject.optString("agent_id");
		String lForward = updateEmail(dataObject.optString("forward1"));
		String lLoginEmail = updateEmail(dataObject.optString("email"));
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAPEmail, lLoginEmail);
		String lAltEmail = updateEmail(dataObject.optString("alt_email"));
		String lTimeZone = dataObject.optString("time_zone");
		String lOfficeName = dataObject.optString("office_name");
		String lOfficePhone = dataObject.optString("office_phone");
		String lOfficeAddress = dataObject.optString("office_address");
		String lDRE = dataObject.optString("dre");
		String lPackageId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAPPackageId);
		String access_token = getIsProd()?EnvironmentFactory.configReader.getPropertyByName("prod_access_token"):EnvironmentFactory.configReader.getPropertyByName("stage_access_token");

		multiParts.put("first_name", new Part(lFname, PartType.STRING));
		multiParts.put("last_name", new Part(lLname, PartType.STRING));
		multiParts.put("phone", new Part(lPhone, PartType.STRING));
		multiParts.put("feed_id", new Part(lFeed, PartType.STRING));
		multiParts.put("agent_id", new Part(lAgentId, PartType.STRING));
		multiParts.put("email", new Part(lLoginEmail, PartType.STRING));
		multiParts.put("forward1", new Part(lForward, PartType.STRING));
		multiParts.put("alt_email", new Part(lAltEmail, PartType.STRING));
		multiParts.put("time_zone", new Part(lTimeZone, PartType.STRING));
		multiParts.put("office_name", new Part(lOfficeName, PartType.STRING));
		multiParts.put("office_phone", new Part(lOfficePhone, PartType.STRING));
		multiParts.put("office_address", new Part(lOfficeAddress, PartType.STRING));
		multiParts.put("dre", new Part(lDRE, PartType.STRING));
		multiParts.put("package_id", new Part(lPackageId, PartType.STRING));
		multiParts.put("access_token", new Part(access_token, PartType.STRING));	
		
		emptyFile(adminDataFile,"");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(System.getProperty("user.dir")+adminDataFile), multiParts);
		
		restContent.setParts(multiParts);
		restContent.setMultiPart(true);
		AutomationLogger.info(restContent.getBody());
		return restContent;
	}
}