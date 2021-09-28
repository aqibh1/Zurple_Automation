/**
 * 
 */
package com.zurple.rest.sapi.sellerlead;

import static org.testng.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.RestValidationAction;

import resources.EnvironmentFactory;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBORestPostImportLeadToBackOffice extends RestAPITest{
	private JSONObject dataObject;
	
	@Test
	@Parameters({"datafile"})
	public void testPostImportLeadToBackOffice(String pDataFile) throws Exception {
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/sapileads/import";
		request.setUrl(lUrl);
		request.setHeaders(HeadersConfig.getSellerLeadHeaders(ContentType.APPLICATION_JSON.toString()));
		request.setRestContent(getContent());
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(response), "Unable to verify the response..");
	}
	
	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String validationAction = getValidationAction(dataObject,this.getClass().getSimpleName());
		JSONObject lJsonResponse = httpCallResp.getJsonResponse();
		AutomationLogger.info(lJsonResponse.toString());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			if(validationAction.equals(RestValidationAction.SUCCESS)) {
				status = verifyResponse(lJsonResponse);
			}
		}
		else {
			status = false;
		}
		return status;
	}
	public boolean verifyResponse(JSONObject pJsonResponse) {
		boolean isVerified = false;
		if(pJsonResponse.getString("Message").equalsIgnoreCase("Lead successfully imported.")) {
			isVerified = true;
		}
		return isVerified;
	}
	
	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		JSONObject jsonObjectToPost = new JSONObject();
		String l_admin_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		String l_email = dataObject.optString("email");
//		String l_first_name = dataObject.optString("first_name");
		if(getIsProd()) {
			l_email = updateName(l_email.split("_")[0])+"@mailinator.com";
		}else {
			l_email = updateName(l_email.split("_")[0]).replace(" ", "_")+"_ZurpleQA@mailinator.com";
		}
		jsonObjectToPost.put("admin_id", l_admin_id);
		jsonObjectToPost.put("email", l_email);
		jsonObjectToPost.put("first_name", updateName(dataObject.optString("first_name")));
		jsonObjectToPost.put("street_address", dataObject.optString("street_address"));
		jsonObjectToPost.put("zip_code", dataObject.optString("zip_code"));
		jsonObjectToPost.put("phone", dataObject.optString("phone"));
		jsonObjectToPost.put("last_name", dataObject.optString("last_name"));
		jsonObjectToPost.put("city", dataObject.optString("city"));
		jsonObjectToPost.put("state", dataObject.optString("state"));
		jsonObjectToPost.put("beds", dataObject.optString("beds"));
		jsonObjectToPost.put("baths", dataObject.optString("baths"));
		jsonObjectToPost.put("sqft", dataObject.optString("sqft"));
		jsonObjectToPost.put("property_updates_flag", dataObject.optInt("property_updates_flag"));
		restContent.setBody(jsonObjectToPost.toString());
		return restContent;
	}
}
