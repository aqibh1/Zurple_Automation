package com.zurple.rest.sapi.sellerlead;

import static org.testng.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.restapi.HttpRequestHandler;
import com.restapi.RestAPITest;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.RestValidationAction;

import resources.utility.AutomationLogger;

public class ZBORestGetLeadInfoFromFacebook extends RestAPITest{
	private JSONObject dataObject;

	@Test
	@Parameters({"datafile"})
	public void testGetLeadInfoFromFacebook(String pDataFile) throws Exception {
		dataObject = getDataFile(pDataFile);
		String fb_access_token = "access_token=EAAGWKffWUQUBAHqez3dnFZAv9uAmAkGzy86NhmE3DKESKCufQh8bCmBCEyttmZCZAZBXYhab9TerZC2J2588Ya7uWUgkyZCArZAxBl2sZACpYaGFXsv2KJSzxOeZAvGaZAZBGUy9vgDWqZAuZBelaNsUpuT9M8R6AaW3jm8XjYkZCUNRaoALpZAiTEuabOS";
		String fb_graph_api_url = "https://graph.facebook.com/v10.0/360155755681336?";
		RestRequest request = new RestRequest();
		String lUrl = fb_graph_api_url+fb_access_token;
//		String lCookie =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);
		request.setUrl(lUrl);
//		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lCookie));
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doGet(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(response), "Unable to verify the response..");
	}
	
	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String validationAction = getValidationAction(dataObject,this.getClass().getSimpleName());
		JSONObject lJsonResponse =  httpCallResp.getJsonResponse();
		AutomationLogger.info(lJsonResponse.toString());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			if(validationAction.equals(RestValidationAction.VERIFY)) {
				status = verifyPostResponse(lJsonResponse);
			}	
		}
		else {
			status = false;
		}
		return status;
	}

	private boolean verifyPostResponse(JSONObject lJsonResponse) throws Exception {
		boolean isVerified = false;
		if(!lJsonResponse.get("created_time").toString().isEmpty() && !lJsonResponse.get("id").toString().isEmpty()) {
			isVerified = true;
		}
		return isVerified;
	}

}
