package com.zurple.rest.zapier;

import static org.testng.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import com.restapi.RestAPITest;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.zurple.backoffice.ZBOLeadEmailPreferencesPageTest;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

public class ZapierRestGetUpdatedLead extends RestAPITest{
	private JSONObject dataObject;
	ZBOLeadEmailPreferencesPageTest updateLead = new ZBOLeadEmailPreferencesPageTest();
	private RestResponse newLeadResponse;
	String accessToken = EnvironmentFactory.configReader.getPropertyByName("access_token");
	
	@Test
	@Parameters({"updateLead"})
	public void testUpdateLead(String pDataFile) {
		getDriver();
		updateLead.testUpdateLeadInfoForZapier(pDataFile);
	}
	
	@Test
	@Parameters({"datafile"})
	public void testZapierResponse(String pDataFile) throws Exception {
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/triggers/updatedleads/?"+accessToken;
		String lCookie =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);// "_ga=GA1.2.1716417370.1586937320; _ga=GA1.4.1716417370.1586937320; _gid=GA1.2.56884822.1597057199; PHPSESSID=kj4i6irrjogi2m7smgn6engbq2; LEADS_LIST_FILTER=O%3A18%3A%22LeadSearchCriteria%22%3A7%3A%7Bs%3A5%3A%22where%22%3Ba%3A5%3A%7Bi%3A0%3Bs%3A34%3A%22%28us_filter.user_search_id+IS+NULL%29%22%3Bi%3A1%3Bs%3A19%3A%22AND+%28u.user_id+%3E+0%29%22%3Bi%3A2%3Bs%3A23%3A%22AND+%28u.delete_flag+%3D+0%29%22%3Bi%3A3%3Bs%3A31%3A%22AND+%28u.user_status+%21%3D+%27hidden%27%29%22%3Bi%3A4%3Bs%3A585%3A%22AND+%28a.admin_id+IN+%2812666%2C12773%2C12774%2C12775%2C12776%2C12777%2C12778%2C12779%2C12780%2C12781%2C12782%2C12783%2C12784%2C12785%2C12786%2C12787%2C12788%2C12789%2C12790%2C12791%2C12792%2C12793%2C12795%2C12796%2C12797%2C12798%2C12799%2C12800%2C12801%2C12802%2C12803%2C12804%2C12805%2C12808%2C12812%2C12813%2C12814%2C12815%2C12816%2C12817%2C12818%2C12820%2C12821%2C12822%2C12823%2C12824%2C12825%2C12826%2C12829%2C12834%2C12835%2C12836%2C12839%2C12840%2C12841%2C12842%2C12843%2C12844%2C12845%2C12848%2C12850%2C12852%2C12853%2C12854%2C12855%2C12856%2C12857%2C12858%2C12859%2C12860%2C12861%2C12862%2C12863%2C12864%2C12865%2C12866%2C12867%2C12868%2C12872%2C12873%2C12874%2C12875%2C12877%2C12879%2C12880%2C12881%2C12882%2C12883%2C12884%2C12885%2C12886%2C12887%2C12888%2C12890%29%29%22%3B%7Ds%3A4%3A%22join%22%3BN%3Bs%3A5%3A%22order%22%3Ba%3A2%3A%7Bi%3A0%3Ba%3A2%3A%7Bi%3A0%3Bs%3A17%3A%22u.create_datetime%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7Di%3A1%3Ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22u.user_id%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7D%7Ds%3A7%3A%22records%22%3Bi%3A5%3Bs%3A6%3A%22offset%22%3Bi%3A0%3Bs%3A9%3A%22list_type%22%3BN%3Bs%3A15%3A%22current_user_id%22%3BN%3B%7D; _gid=GA1.4.56884822.1597057199; _gat=1; SESSION=9744; LAST_LOGIN_ACTION_COOKIE_KEY=LOGIN; _gat_gtag_UA_52300713_3=1";
		request.setUrl(lUrl);
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lCookie));
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		newLeadResponse = httpRequestHandler.doGet(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(newLeadResponse), "Unable to verify the response..");
	}
	
	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String validationAction = getValidationAction(dataObject,this.getClass().getSimpleName());
		JSONArray lJsonResponseArray = httpCallResp.getJsonResponseArray();
		JSONObject lJsonResponse =  lJsonResponseArray.getJSONObject(0);
		AutomationLogger.info(lJsonResponse.toString());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			status = verifyPostResponse(lJsonResponse);
		}
		else {
			status = false;
		}
		return status;
	}

	private boolean verifyPostResponse(JSONObject lJsonResponse) throws Exception {
		boolean isVerified = false;
			AutomationLogger.info("Zapier lead email ID :: "+lJsonResponse.get("email").toString());			
			String leadEmail = updateLead.updatedLeadData.get("email");// ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);	
			if(lJsonResponse.get("email").toString().equalsIgnoreCase(leadEmail)) {
					isVerified = true;
					String leadName = updateLead.updatedLeadData.get("city"); //ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
					if(!lJsonResponse.optString("city").equalsIgnoreCase(leadName)) {
						AutomationLogger.info("Lead first name is not valid..");
						isVerified = false;
					}
					String leadSource = updateLead.updatedLeadData.get("zip");
					if(!lJsonResponse.optString("zip").equalsIgnoreCase(leadSource)) {
						AutomationLogger.info("Lead source is not valid..");
						isVerified = false;
					}
					String leadLastName = updateLead.updatedLeadData.get("state");
					if(!lJsonResponse.optString("state").equalsIgnoreCase(leadLastName)) {
						AutomationLogger.info("Lead last name is not valid..");
						isVerified = false;
					}
					String leadCell = "1"+updateLead.updatedLeadData.get("cell");
					if(!lJsonResponse.optString("cell").equalsIgnoreCase(leadCell)) {
						AutomationLogger.info("Lead cell is not valid..");
						isVerified = false;
					}
					String leadPhone = "1"+updateLead.updatedLeadData.get("phone");
					if(!lJsonResponse.optString("phone").equalsIgnoreCase(leadPhone)) {
						AutomationLogger.info("Lead phone is not valid..");
						isVerified = false;
					}
		}
		return isVerified;
	}
}
