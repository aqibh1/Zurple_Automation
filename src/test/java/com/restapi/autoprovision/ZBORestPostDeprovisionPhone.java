package com.restapi.autoprovision;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.google.api.client.http.HttpStatusCodes;
import com.restapi.HttpRequestHandler;
import com.restapi.Part;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.Part.PartType;

import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.orm.hibernate.models.zurple.Admin;
import resources.utility.AutomationLogger;

/**
 * 
 * @author habibaaq
 *
 */

public class ZBORestPostDeprovisionPhone extends RestAPITest{
	
	@Test(priority=249)//,dependsOnGroups={"ZBORestPostFetchNSRecord.testPostFetchNSRecordForAdminProfile"})
	public void testPostFetchAndRemoveAliasPhone() throws Exception {
		getDriver();
		fetchAdminIdsAndDeprovision("APAdmin");
	}
	
	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean isSuccessful = false;
		if(httpCallResp.getStatus() == HttpStatusCodes.STATUS_CODE_OK) {
			isSuccessful = true;
		}
		return isSuccessful;
	}
	
	public void fetchAdminIdsAndDeprovision(String lName) throws Exception {
		DBHelperMethods dbmethods = new DBHelperMethods(getEnvironment());
		List<Admin> list_of_admins_with_aliasphone = dbmethods.getAllAPAdminsWithPhone(lName);
		for(Admin admin_id : list_of_admins_with_aliasphone) {
			RestRequest request = new RestRequest();
			String lUrl = getZurpleAPIUrl()+"/admins/"+admin_id.getId()+"/cancel-phone-number";
			request.setUrl(lUrl);
			HashMap<String,String> auth = new HashMap<String, String>();
			auth.put("Authorization",EnvironmentFactory.configReader.getPropertyByName("basic_auth"));
			request.setHeaders(auth);
			HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
			RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
			assertTrue(validateMapResp(response),"Unable to Remove Alias Phone Numbers for AP admins..");
		}
		
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
