package com.zurple.rest;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.api.client.http.HttpStatusCodes;
import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import com.restapi.Part;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.Part.PartType;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

public class ZBORestPostSMSMessageTest extends RestAPITest {
	private JSONObject dataObject;
	
	@Test
	@Parameters({"dataFile"})
	public void testPostMessage(String pDataFile) throws Exception {
		getDriver();
		String lc_cookie = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/sendchatmessage/"+EnvironmentFactory.configReader.getPropertyByName("zurple_lead_id");
		request.setUrl(lUrl);
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lc_cookie));
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		request.setRestContent(getContent());
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(response),"Unable to verify the send sms message response..");
	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean isSuccess = false;
		boolean status,successMessage = false;
		if(httpCallResp.getStatus() == HttpStatusCodes.STATUS_CODE_OK) {
			status = httpCallResp.getJsonResponse().optString("success").equalsIgnoreCase("1");
			successMessage = httpCallResp.getJsonResponse().optString("message").equalsIgnoreCase("Message has been sent.");
			if(status && successMessage) {
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		Map<String, Part> multiParts = new HashMap<String, Part>();
		String lMessage = updateName(dataObject.optString("message_text"));
		String lPlatformMessageType = dataObject.optString("message_type");
		String lDirection = dataObject.optString("direction");
		multiParts.put("message", new Part(lMessage, PartType.STRING));
		multiParts.put("platform_message_type", new Part(lPlatformMessageType, PartType.STRING));
		multiParts.put("direction", new Part(lDirection, PartType.STRING));		
		restContent.setParts(multiParts);
		restContent.setMultiPart(true);
		AutomationLogger.info(restContent.getBody());
		return restContent;
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
