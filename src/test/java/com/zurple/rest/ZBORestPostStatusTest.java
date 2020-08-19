/**
 * 
 */
package com.zurple.rest;

import static org.testng.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.restapi.HeadersConfig;
import com.restapi.HttpRequestHandler;
import com.restapi.Part;
import com.restapi.RestAPITest;
import com.restapi.RestContent;
import com.restapi.RestRequest;
import com.restapi.RestResponse;
import com.restapi.RestValidationAction;
import com.restapi.Part.PartType;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBORestPostStatusTest extends RestAPITest{
	private JSONObject dataObject;
	
	@Test
	@Parameters({"datafile"})
	public void testGetPostStatus(String pDataFile) throws Exception {
		getDriver();
		String lc_cookie = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/social/post";
//		String lCookie = "_ga=GA1.2.1716417370.1586937320; _ga=GA1.4.1716417370.1586937320; _gid=GA1.2.56884822.1597057199; PHPSESSID=kj4i6irrjogi2m7smgn6engbq2; LEADS_LIST_FILTER=O%3A18%3A%22LeadSearchCriteria%22%3A7%3A%7Bs%3A5%3A%22where%22%3Ba%3A5%3A%7Bi%3A0%3Bs%3A34%3A%22%28us_filter.user_search_id+IS+NULL%29%22%3Bi%3A1%3Bs%3A19%3A%22AND+%28u.user_id+%3E+0%29%22%3Bi%3A2%3Bs%3A23%3A%22AND+%28u.delete_flag+%3D+0%29%22%3Bi%3A3%3Bs%3A31%3A%22AND+%28u.user_status+%21%3D+%27hidden%27%29%22%3Bi%3A4%3Bs%3A585%3A%22AND+%28a.admin_id+IN+%2812666%2C12773%2C12774%2C12775%2C12776%2C12777%2C12778%2C12779%2C12780%2C12781%2C12782%2C12783%2C12784%2C12785%2C12786%2C12787%2C12788%2C12789%2C12790%2C12791%2C12792%2C12793%2C12795%2C12796%2C12797%2C12798%2C12799%2C12800%2C12801%2C12802%2C12803%2C12804%2C12805%2C12808%2C12812%2C12813%2C12814%2C12815%2C12816%2C12817%2C12818%2C12820%2C12821%2C12822%2C12823%2C12824%2C12825%2C12826%2C12829%2C12834%2C12835%2C12836%2C12839%2C12840%2C12841%2C12842%2C12843%2C12844%2C12845%2C12848%2C12850%2C12852%2C12853%2C12854%2C12855%2C12856%2C12857%2C12858%2C12859%2C12860%2C12861%2C12862%2C12863%2C12864%2C12865%2C12866%2C12867%2C12868%2C12872%2C12873%2C12874%2C12875%2C12877%2C12879%2C12880%2C12881%2C12882%2C12883%2C12884%2C12885%2C12886%2C12887%2C12888%2C12890%29%29%22%3B%7Ds%3A4%3A%22join%22%3BN%3Bs%3A5%3A%22order%22%3Ba%3A2%3A%7Bi%3A0%3Ba%3A2%3A%7Bi%3A0%3Bs%3A17%3A%22u.create_datetime%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7Di%3A1%3Ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22u.user_id%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7D%7Ds%3A7%3A%22records%22%3Bi%3A5%3Bs%3A6%3A%22offset%22%3Bi%3A0%3Bs%3A9%3A%22list_type%22%3BN%3Bs%3A15%3A%22current_user_id%22%3BN%3B%7D; _gid=GA1.4.56884822.1597057199; SESSION=9744; LAST_LOGIN_ACTION_COOKIE_KEY=LOGIN; _gat_gtag_UA_52300713_3=1";
		request.setUrl(lUrl);
		request.setRestContent(getContent());
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lc_cookie));
		
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);
		
		assertTrue(validateMapResp(response),"Unable to verify the response..");
	}
	
	@Test
	@Parameters({"datafile"})
	public void testScheduledPost(String pDataFile) throws Exception {
		getDriver();
		String lc_cookie = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Cookie);
		dataObject = getDataFile(pDataFile);
		RestRequest request = new RestRequest();
		String lUrl = getBaseUrl()+"/social/post";
		
		request.setUrl(lUrl);
		request.setRestContent(getContent("scheduled"));
		request.setHeaders(HeadersConfig.getMultipartFormDataHeaders(lc_cookie));
		
		HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
		RestResponse response = httpRequestHandler.doPost(this.getClass().getName(), request, true);

		assertTrue(validateMapResp(response,"scheduled"),"Unable to verify the response..");

	}

	@Override
	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {

		boolean status = false;
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String validationAction = getValidationAction(dataObject,this.getClass().getSimpleName());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			if(validationAction.equals(RestValidationAction.CREATE)) {
				status = httpCallResp.getJsonResponse().optString("status").equalsIgnoreCase("1");
				String lc_post_id = httpCallResp.getJsonResponse().getJSONObject("data").get("post_id").toString();
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurplePostId, lc_post_id);
			}
		}
		else {
			status = false;
		}
		ActionHelper.staticWait(30);
		return status;
	}
	
	@Override
	public boolean validateMapResp(RestResponse httpCallResp, String postType) throws Exception {

		boolean status = false;
		int statusCode = Integer.parseInt(dataObject.optString("status_code"));
		String validationAction = getValidationAction(dataObject,this.getClass().getSimpleName());
		if(httpCallResp.getStatus() == statusCode && statusCode == HttpStatus.SC_OK) {
			if(validationAction.equals(RestValidationAction.CREATE)) {
				status = httpCallResp.getJsonResponse().optString("status").equalsIgnoreCase("1");
				if(postType.equalsIgnoreCase("scheduled")) {
					String lc_post_id = httpCallResp.getJsonResponse().getJSONObject("data").get("post_id").toString();
					saveToFile(lc_post_id);
				//	ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurplePostScheduleId, lc_post_id);
				}
			}
		}
		else {
			status = false;
		}
		ActionHelper.staticWait(30);
		return status;
	}

	private RestContent getContent() throws Exception {
		RestContent restContent = new RestContent();
		Map<String, Part> multiParts = new HashMap<String, Part>();
		
		multiParts.put("post_message", new Part(updateName(dataObject.optString("post_message")), PartType.STRING));
		multiParts.put("social_network", new Part(dataObject.optString("social_network"), PartType.STRING));
		multiParts.put("page_id", new Part(dataObject.optString("page_id"), PartType.STRING));
		multiParts.put("operation", new Part(dataObject.optString("operation"), PartType.STRING));
		multiParts.put("post_type", new Part(dataObject.optString("post_type"), PartType.STRING));
		if(!dataObject.optString("image_path").isEmpty()) {
			multiParts.put("post_image", new Part(dataObject.optString("image_path"), PartType.FILE));
		}
		restContent.setParts(multiParts);
		restContent.setMultiPart(true);
		AutomationLogger.info(restContent.getBody());
		return restContent;
	}
	
	private RestContent getContent(String postType) throws Exception {
		RestContent restContent = new RestContent();
		Map<String, Part> multiParts = new HashMap<String, Part>();
		String postMessage = updateName(dataObject.optString("post_message"));
		multiParts.put("post_message", new Part(postMessage, PartType.STRING));
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurplePostMessage, postMessage);
		multiParts.put("social_network", new Part(dataObject.optString("social_network"), PartType.STRING));
		multiParts.put("page_id", new Part(dataObject.optString("page_id"), PartType.STRING));
		multiParts.put("operation", new Part(dataObject.optString("operation"), PartType.STRING));
		multiParts.put("post_type", new Part(dataObject.optString("post_type"), PartType.STRING));
		if(!dataObject.optString("image_path").isEmpty()) {
			multiParts.put("post_image", new Part(dataObject.optString("image_path"), PartType.FILE));
		}
		if(postType.equalsIgnoreCase("scheduled")) {
			multiParts.put("post_scheduled_to", new Part(setScheduledPostDate(), PartType.STRING));
		}
		restContent.setParts(multiParts);
		restContent.setMultiPart(true);
		AutomationLogger.info(restContent.getBody());
		return restContent;
	}
	
	public boolean saveToFile(String postID) {
		try {
		      File myObj = new File("postids.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	AutomationLogger.info("File already exists.");
		      }
		    } catch (IOException e) {
		    	AutomationLogger.info("An error occurred.");
		      e.printStackTrace();
		    }
		
		try {
			  boolean append = false;
		      //FileWriter myWriter = new FileWriter("postids.txt", append);
		      Writer output = new BufferedWriter(new FileWriter("postids.txt", true));
		      output.append("\n");
		      output.append(postID);
		      
		      output.close();
		      AutomationLogger.info("Successfully wrote to the file.");
		      return true;
		    } catch (IOException e) {
		    	AutomationLogger.info("An error occurred.");
		      e.printStackTrace();
		      return false;
		    }
	}

	public String readFromFile() {
		String data = "";
		try {
		      File myObj = new File("postids.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data = myReader.nextLine();
		        AutomationLogger.info(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		    	AutomationLogger.info("An error occurred.");
		      e.printStackTrace();
		    }
		return data;
	}
}
