package com.restapi.autoprovision;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.google.api.client.http.HttpStatusCodes;
import com.restapi.HttpRequestHandler;
import com.restapi.Part;
import com.restapi.Part.PartType;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import resources.EnvironmentFactory;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author ahabib
 *
 */
public class ZBORestPostFetchNSRecord extends RestAPITest{
	private JSONObject dataObject;
	String lDataFile ="";
	
	@Test(priority=245)//,dependsOnGroups={"ZAUpdateAdminManagerPageTest.testSubmitUpdates"})
	@Parameters({"dataFile"})
	public void testPostFetchNSRecord(String pDataFile) throws Exception {
		getPage();
		lDataFile = pDataFile;
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/getnsrecord";
		request.setUrl(lUrl);
		request.setRestContent(getContent());
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(response),"Unable to verify the NS record data..");
	}
	
	@Test(priority=248)//,dependsOnGroups={"ZAUpdateAdminProfilePageTest.testSubmitUpdates"},groups={"ZBORestPostFetchNSRecord.testPostFetchNSRecordForAdminProfile"})
	@Parameters({"dataFile"})
	public void testPostFetchNSRecordForAdminProfile(String pDataFile) throws Exception {
		page=null;
		getPage();
		lDataFile = pDataFile;
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/getnsrecord";
		request.setUrl(lUrl);
		request.setRestContent(getContent());
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(response),"Unable to verify the NS record data..");
	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean fName,lName,email,phone,oName,dre, isSuccessful = false;
		JSONObject jObject = httpCallResp.getJsonResponse();
		if(httpCallResp.getStatus() == HttpStatusCodes.STATUS_CODE_OK) {
			fName = jObject.optString("first_name").equalsIgnoreCase(dataObject.getJSONObject("fName").optString("content"));
			lName = jObject.optString("last_name").equalsIgnoreCase(dataObject.getJSONObject("lName").optString("content"));
			email = jObject.optString("login_email").equalsIgnoreCase(dataObject.getJSONObject("email").optString("content"));
			phone = jObject.optString("phone").replaceAll("[()\\s-]+", "").contains(dataObject.getJSONObject("phone").optString("content").replaceAll("[()\\s-]+", ""));
			oName = jObject.optString("office_name").equalsIgnoreCase(dataObject.getJSONObject("oName").optString("content"));
			dre = jObject.optString("dre").equalsIgnoreCase(dataObject.getJSONObject("dre").optString("content"));
			if(fName && lName && email && phone && oName && dre) {
				String admin_id = jObject.optString("admin_id").toString();
				AutomationLogger.info("BO to NS syncing is working as expected for admin_id: "+admin_id);
				isSuccessful = true;
			}
		}
		return isSuccessful;
	}
	
	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		Map<String, Part> multiParts = new HashMap<String, Part>();
		String nsId = EnvironmentFactory.configReader.getPropertyByName("ap_ns_id");
		String access_token = EnvironmentFactory.configReader.getPropertyByName("ap_access_token"); //Done
		multiParts.put("netsuite_id", new Part(nsId, PartType.STRING));
		multiParts.put("access_token", new Part(access_token, PartType.STRING));			
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
