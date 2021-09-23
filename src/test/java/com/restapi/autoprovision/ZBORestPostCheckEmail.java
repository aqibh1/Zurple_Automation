package com.restapi.autoprovision;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
public class ZBORestPostCheckEmail extends RestAPITest{
	
	@Test(priority=239)
	public void testPostCheckEmail() throws Exception {
		getDriver();
		JSONObject responseObj = null;
		HashMap<String,String> ua = new HashMap<String, String>();
		ua.put("User-Agent", "aaqib-useragent");
		String lc_cookie = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/checkemail";
		request.setUrl(lUrl);
		request.setRestContent(getContent());
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lc_cookie));
		request.setHeaders(ua);
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		responseObj = response.getJsonResponse();
		assertTrue(validateMapResp(response),"Unable to verify check email response..");
	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		status = httpCallResp.getJsonResponse().optString("message").equalsIgnoreCase("Failed");
		String lFileToWrite = getIsProd()?"/resources/cache/cache-ap-package-admin-data-prod.json":"/resources/cache/cache-ap-package-admin-data.json";
		JSONObject jObject = httpCallResp.getJsonResponse();		
		if(status) {
				String emailAlreadyAssigned = jObject.optString("admin_id").toString();
				if(!emailAlreadyAssigned.isEmpty()) {
					AutomationLogger.info("This email is already assigned to:"+emailAlreadyAssigned);
				}
			}
		return status;
	}
	
	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		Map<String, Part> multiParts = new HashMap<String, Part>();
		String lEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAPEmail);
		String access_token = getIsProd()?EnvironmentFactory.configReader.getPropertyByName("prod_access_token"):EnvironmentFactory.configReader.getPropertyByName("stage_access_token");
		
		multiParts.put("name", new Part(lEmail, PartType.STRING));
		multiParts.put("access_token", new Part(access_token, PartType.STRING));
	
		restContent.setParts(multiParts);
		restContent.setMultiPart(true);
		AutomationLogger.info(restContent.getBody());
		return restContent;
	}
}
