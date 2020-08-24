/**
 * 
 */
package com.zurple.rest;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;
import com.restapi.RestAPITest;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.RestValidationAction;

/**
 * @author adar
 *
 */
public class ZBORestGetPostHistoryPageTest extends RestAPITest{
	private JSONObject dataObject;
	private boolean isScheduled = false;
	ZBORestPostStatusTest zbo = new ZBORestPostStatusTest();
	private RestResponse scheduledPostHistoryResponse;
	private RestResponse postHistoryResponse;
	List<String> list = new ArrayList<String>();
	String scheduledPostId = "";
	
	@Test
	@Parameters({"datafile"})
	public void testGetPostHistory(String pDataFile) throws Exception {
		dataObject = getDataFile(pDataFile);
		getDriver();
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/social/getposthistory?page_num=1";
		String lCookie =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);// "_ga=GA1.2.1716417370.1586937320; _ga=GA1.4.1716417370.1586937320; _gid=GA1.2.56884822.1597057199; PHPSESSID=kj4i6irrjogi2m7smgn6engbq2; LEADS_LIST_FILTER=O%3A18%3A%22LeadSearchCriteria%22%3A7%3A%7Bs%3A5%3A%22where%22%3Ba%3A5%3A%7Bi%3A0%3Bs%3A34%3A%22%28us_filter.user_search_id+IS+NULL%29%22%3Bi%3A1%3Bs%3A19%3A%22AND+%28u.user_id+%3E+0%29%22%3Bi%3A2%3Bs%3A23%3A%22AND+%28u.delete_flag+%3D+0%29%22%3Bi%3A3%3Bs%3A31%3A%22AND+%28u.user_status+%21%3D+%27hidden%27%29%22%3Bi%3A4%3Bs%3A585%3A%22AND+%28a.admin_id+IN+%2812666%2C12773%2C12774%2C12775%2C12776%2C12777%2C12778%2C12779%2C12780%2C12781%2C12782%2C12783%2C12784%2C12785%2C12786%2C12787%2C12788%2C12789%2C12790%2C12791%2C12792%2C12793%2C12795%2C12796%2C12797%2C12798%2C12799%2C12800%2C12801%2C12802%2C12803%2C12804%2C12805%2C12808%2C12812%2C12813%2C12814%2C12815%2C12816%2C12817%2C12818%2C12820%2C12821%2C12822%2C12823%2C12824%2C12825%2C12826%2C12829%2C12834%2C12835%2C12836%2C12839%2C12840%2C12841%2C12842%2C12843%2C12844%2C12845%2C12848%2C12850%2C12852%2C12853%2C12854%2C12855%2C12856%2C12857%2C12858%2C12859%2C12860%2C12861%2C12862%2C12863%2C12864%2C12865%2C12866%2C12867%2C12868%2C12872%2C12873%2C12874%2C12875%2C12877%2C12879%2C12880%2C12881%2C12882%2C12883%2C12884%2C12885%2C12886%2C12887%2C12888%2C12890%29%29%22%3B%7Ds%3A4%3A%22join%22%3BN%3Bs%3A5%3A%22order%22%3Ba%3A2%3A%7Bi%3A0%3Ba%3A2%3A%7Bi%3A0%3Bs%3A17%3A%22u.create_datetime%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7Di%3A1%3Ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22u.user_id%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7D%7Ds%3A7%3A%22records%22%3Bi%3A5%3Bs%3A6%3A%22offset%22%3Bi%3A0%3Bs%3A9%3A%22list_type%22%3BN%3Bs%3A15%3A%22current_user_id%22%3BN%3B%7D; _gid=GA1.4.56884822.1597057199; _gat=1; SESSION=9744; LAST_LOGIN_ACTION_COOKIE_KEY=LOGIN; _gat_gtag_UA_52300713_3=1";
		request.setUrl(lUrl);
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lCookie));
		
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		postHistoryResponse = httpRequestHandler.doGet(this.getClass().getName(), request, true);
		assertTrue(validateMapResp(postHistoryResponse), "Unable to verify the response..");
	}
	
	@Test
	@Parameters({"datafile"})
	public void testGetScheduledPostHistory(String pDataFile) throws Exception {
		dataObject = getDataFile(pDataFile);
		getDriver();
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/social/getpostschedule?page_num=1";
		String lCookie =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);// "_ga=GA1.2.1716417370.1586937320; _ga=GA1.4.1716417370.1586937320; _gid=GA1.2.56884822.1597057199; PHPSESSID=kj4i6irrjogi2m7smgn6engbq2; LEADS_LIST_FILTER=O%3A18%3A%22LeadSearchCriteria%22%3A7%3A%7Bs%3A5%3A%22where%22%3Ba%3A5%3A%7Bi%3A0%3Bs%3A34%3A%22%28us_filter.user_search_id+IS+NULL%29%22%3Bi%3A1%3Bs%3A19%3A%22AND+%28u.user_id+%3E+0%29%22%3Bi%3A2%3Bs%3A23%3A%22AND+%28u.delete_flag+%3D+0%29%22%3Bi%3A3%3Bs%3A31%3A%22AND+%28u.user_status+%21%3D+%27hidden%27%29%22%3Bi%3A4%3Bs%3A585%3A%22AND+%28a.admin_id+IN+%2812666%2C12773%2C12774%2C12775%2C12776%2C12777%2C12778%2C12779%2C12780%2C12781%2C12782%2C12783%2C12784%2C12785%2C12786%2C12787%2C12788%2C12789%2C12790%2C12791%2C12792%2C12793%2C12795%2C12796%2C12797%2C12798%2C12799%2C12800%2C12801%2C12802%2C12803%2C12804%2C12805%2C12808%2C12812%2C12813%2C12814%2C12815%2C12816%2C12817%2C12818%2C12820%2C12821%2C12822%2C12823%2C12824%2C12825%2C12826%2C12829%2C12834%2C12835%2C12836%2C12839%2C12840%2C12841%2C12842%2C12843%2C12844%2C12845%2C12848%2C12850%2C12852%2C12853%2C12854%2C12855%2C12856%2C12857%2C12858%2C12859%2C12860%2C12861%2C12862%2C12863%2C12864%2C12865%2C12866%2C12867%2C12868%2C12872%2C12873%2C12874%2C12875%2C12877%2C12879%2C12880%2C12881%2C12882%2C12883%2C12884%2C12885%2C12886%2C12887%2C12888%2C12890%29%29%22%3B%7Ds%3A4%3A%22join%22%3BN%3Bs%3A5%3A%22order%22%3Ba%3A2%3A%7Bi%3A0%3Ba%3A2%3A%7Bi%3A0%3Bs%3A17%3A%22u.create_datetime%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7Di%3A1%3Ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22u.user_id%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7D%7Ds%3A7%3A%22records%22%3Bi%3A5%3Bs%3A6%3A%22offset%22%3Bi%3A0%3Bs%3A9%3A%22list_type%22%3BN%3Bs%3A15%3A%22current_user_id%22%3BN%3B%7D; _gid=GA1.4.56884822.1597057199; _gat=1; SESSION=9744; LAST_LOGIN_ACTION_COOKIE_KEY=LOGIN; _gat_gtag_UA_52300713_3=1";
		request.setUrl(lUrl);
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lCookie));
		
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		scheduledPostHistoryResponse = httpRequestHandler.doGet(this.getClass().getName(), request, true);
		
		assertTrue(validateMapResp(scheduledPostHistoryResponse), "Unable to verify the response..");
	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
		boolean status = false;
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String validationAction = getValidationAction(dataObject,this.getClass().getSimpleName());
		JSONObject lJsonResponse = httpCallResp.getJsonResponse();
		AutomationLogger.info(lJsonResponse.toString());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			if(validationAction.equals(RestValidationAction.FOUND)) {
				String lc_post_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurplePostId);
				status = lJsonResponse.optString("status").equalsIgnoreCase("1") && verifyPostResponse(lJsonResponse,lc_post_id);
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurplePostId, lc_post_id);
			}
			if(validationAction.equals(RestValidationAction.VERIFY)) {
				isScheduled = true;
				String postMessage = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurplePostMessage);
				String postScheduleID = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurplePostId);
				status = lJsonResponse.optString("status").equalsIgnoreCase("1") && verifyPostResponse(lJsonResponse,postScheduleID);
				}
			if(validationAction.equals(RestValidationAction.VALIDATE)) {
				status = getVerifyShceduledPostsData();
			}
			}
		else {
			status = false;
		}
		return status;
	}

	private boolean verifyPostResponse(JSONObject lJsonResponse, String pPostId) throws JSONException, IOException {
		boolean isVerified = false;
		JSONArray jArray;
		jArray = lJsonResponse.getJSONArray("data");
		JSONObject jObject = new JSONObject();
		for(int i=0;i<jArray.length(); i++) {
			jObject = jArray.getJSONObject(i);
			AutomationLogger.info("Post Schedule id to Verify :: "+pPostId);
			AutomationLogger.info("Post Schedule ID :: "+jObject.get("post_schedule_id").toString());
			//			AutomationLogger.info("Post ID :: "+jObject.get("post_id").toString());
			if(isScheduled) {
				if(jObject.get("post_schedule_id").toString().equalsIgnoreCase(pPostId)) {
					isVerified = true;
					break;
				}
			}
			if(!isScheduled) {
				if(jObject.get("post_id").toString().equalsIgnoreCase(pPostId)) {
					isVerified = true;
					break;
				}
			}

		}
		if(isVerified) {
			getDriver();
			String post_message = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurplePostMessage);
			if(isScheduled) {
				if(!jObject.optString("schedule_message").trim().equals(post_message.trim())) {
					AutomationLogger.error("Post scheduled message is not valid..");
					isVerified = false;
				}
				if(!dataObject.optString("post_type").equalsIgnoreCase(jObject.optString("schedule_type"))) {
					AutomationLogger.error("Post type is not valid..");
					isVerified = false;
				}
			} else {
				if(!jObject.optString("post_message").trim().equals(post_message.trim())) {
					AutomationLogger.error("Post message is not valid..");
					isVerified = false;
				}			
				if(!dataObject.optString("social_network").equalsIgnoreCase(jObject.optString("social_network_name"))) {
				AutomationLogger.error("Post platform is not valid..");
				isVerified = false;
				}
				if(!dataObject.optString("post_type").equalsIgnoreCase(jObject.optString("post_type"))) {
				AutomationLogger.error("Post type is not valid..");
				isVerified = false;
			}
			}
		}
		return isVerified;
	}

	private boolean getPostHistoryData() throws Exception {
		boolean isVerified = false;
		JSONObject JsonResponse = postHistoryResponse.getJsonResponse();
		JSONArray iArray = JsonResponse.getJSONArray("data");
		String lFileToWriteProd = getIsProd()?"/resources/cache/scheduled-post-prod.json":"/resources/cache/scheduled-post-qa.json";
		JSONArray jArray = new JSONArray(getDataFileContentJsonArray(System.getProperty("user.dir")+lFileToWriteProd));
		JSONObject iObject = new JSONObject();
		JSONObject jObject = new JSONObject();
		String post_schedule_id = "";
		for(int i=0;i<iArray.length(); i++) {
			for(int j=0;j<jArray.length(); j++) {
				iObject = iArray.getJSONObject(i);
				jObject = jArray.getJSONObject(j);				
				AutomationLogger.info("Post Schedule ID in Post History :: "+iArray.getJSONObject(i).get("post_schedule_id").toString());
				post_schedule_id = jArray.getJSONObject(j).getJSONObject("data").get("post_id").toString();
				AutomationLogger.info("Post Schedule ID in Data File:: "+post_schedule_id);
				if(post_schedule_id.equalsIgnoreCase(iObject.get("post_schedule_id").toString().trim())) {
					isVerified = true;
					break;
				}
			}
			break;
	}
	return isVerified;
}
	private boolean getVerifyShceduledPostsData() throws Exception {
		boolean isVerified = true;
		boolean lFlag = false;
		String lFileToWriteProd = getIsProd()?"/resources/cache/scheduled-post-prod.json":"/resources/cache/scheduled-post-qa.json";
		JSONObject JsonResponse = postHistoryResponse.getJsonResponse();
		JSONArray lResponseDataArray = JsonResponse.getJSONArray("data");
		JSONArray lPostIdsToVerifyArray = new JSONArray(getDataFileContentJsonArray(System.getProperty("user.dir")+lFileToWriteProd));
		String post_schedule_id_to_verify = "";
		for(int i=0;i<lPostIdsToVerifyArray.length();i++) {
			lFlag = false;
			post_schedule_id_to_verify = lPostIdsToVerifyArray.getJSONObject(i).getJSONObject("data").get("post_id").toString();
			for(int j=0;j<lResponseDataArray.length();j++) {
				String l_response_post_id = lResponseDataArray.getJSONObject(j).get("post_schedule_id").toString();
				//Comparing response and data post id
				AutomationLogger.info("Post ID in data file :: "+post_schedule_id_to_verify);
				AutomationLogger.info("Post ID in post history:: "+l_response_post_id);
				if(post_schedule_id_to_verify.equalsIgnoreCase(l_response_post_id)) {
					lFlag = true;
					break;
				}
			}
			if(!lFlag) {
				isVerified = false;
				AutomationLogger.error("Unable to verify Post ID :: "+post_schedule_id_to_verify);
			}
		}

		return isVerified;
}
}
