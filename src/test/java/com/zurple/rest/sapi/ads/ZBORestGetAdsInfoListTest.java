/**
 * 
 */
package com.zurple.rest.sapi.ads;

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
import com.restapi.RestValidationAction;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBORestGetAdsInfoListTest extends RestAPITest{
	private JSONObject dataObject;
	int l_ad_id = 0;
	@Test
	@Parameters({"datafile"})
	public void testGetAdInfoList(String pDataFile) throws Exception {
		getDriver();
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		//api/12828/ad/adId	
		String l_agent_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		String l_ad_id_str = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAdId).toString().split("ad=")[1];
		l_ad_id = Integer.parseInt(l_ad_id_str);
		String lUrl = getSAPIUrl()+"/api/"+l_agent_id+"/ad/list";
		String lAuthorization =EnvironmentFactory.configReader.getPropertyByName("authorization");
		request.setUrl(lUrl);
		request.setHeaders(HeadersConfig.getSAPIHeaders(lAuthorization));
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doGet(this.getClass().getName(), request, true);
		
		assertTrue(validateMapResp(response), "Unable to verify the response..");
	}
	

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		JSONArray response_data_array = new JSONArray();
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String[] validationActions = getValidationActions(dataObject,this.getClass().getSimpleName()).split(",");
		String ld_status_to_verify = dataObject.optString("status_to_verify");
		JSONObject lJsonResponse = httpCallResp.getJsonResponse();
		AutomationLogger.info(lJsonResponse.toString());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			for(String validationAction: validationActions) {
				if(validationAction.equals(RestValidationAction.VERIFY)) {
					status = lJsonResponse.optString("success").equalsIgnoreCase("1");
					if(status) {
						response_data_array = lJsonResponse.getJSONArray("data");
						status = verifyAdStatus(response_data_array,l_ad_id, ld_status_to_verify);
					}

				}if(validationAction.equals(RestValidationAction.DUPLICATE)) {
					status = lJsonResponse.optString("success").equalsIgnoreCase("1");
					if(status) {
						response_data_array = lJsonResponse.getJSONArray("data");
						status = !verifyAdIsDuplicated(response_data_array,l_ad_id);
					}
				}
				if(!status) {
					break;
				}
			}
		}
		else {
			status = false;
		}
		return status;
	}

	private boolean verifyAdIsDuplicated(JSONArray pArray, int pAdId) {
		boolean isSuccess = false;
		int ad_count = 0;
		for(int i=0;i<pArray.length();i++) {
			JSONObject jObject = pArray.getJSONObject(i);
			if(jObject.optInt("sup_ad_id")==pAdId) {
				ad_count++;
			}
			if(ad_count>=2) {
				isSuccess = true;
				break;
			}
		}
		return isSuccess;
	}


	public boolean verifyAdStatus(JSONArray pArray, int pAdId, String pStatusToVerify) {
		boolean isSuccess = false;
		for(int i=0;i<pArray.length();i++) {
			JSONObject jObject = pArray.getJSONObject(i);
			if(jObject.optInt("sup_ad_id")==pAdId) {
				if(jObject.optString("status").equalsIgnoreCase(pStatusToVerify)) {
					isSuccess = true;
					break;
				}
			}
		}
		return isSuccess;
	}

}
