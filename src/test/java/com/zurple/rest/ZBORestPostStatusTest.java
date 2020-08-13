/**
 * 
 */
package com.zurple.rest;

import static org.testng.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.restapi.HTTPConstants;
import com.restapi.HTTPResponseClient;
import com.restapi.RestAPITest;

/**
 * @author adar
 *
 */
public class ZBORestPostStatusTest extends RestAPITest{
	private RestAPITest restApiTest;
	private HTTPResponseClient response;
	
	public RestAPITest getRestCallObject(){
		restApiTest = new RestAPITest();
		return restApiTest;
	}
	@Test
	public void testPostStatus() {
		getRestCallObject();
		String lUrl = getBaseUrl();
		restApiTest.setRequestHeaders(HTTPConstants.Cookie, "_ga=GA1.2.1716417370.1586937320; _ga=GA1.4.1716417370.1586937320; _gid=GA1.4.56884822.1597057199; _gid=GA1.2.56884822.1597057199; PHPSESSID=kj4i6irrjogi2m7smgn6engbq2; SESSION=9705; LAST_LOGIN_ACTION_COOKIE_KEY=LOGIN; LEADS_LIST_FILTER=O%3A18%3A%22LeadSearchCriteria%22%3A7%3A%7Bs%3A5%3A%22where%22%3Ba%3A5%3A%7Bi%3A0%3Bs%3A34%3A%22%28us_filter.user_search_id+IS+NULL%29%22%3Bi%3A1%3Bs%3A19%3A%22AND+%28u.user_id+%3E+0%29%22%3Bi%3A2%3Bs%3A23%3A%22AND+%28u.delete_flag+%3D+0%29%22%3Bi%3A3%3Bs%3A31%3A%22AND+%28u.user_status+%21%3D+%27hidden%27%29%22%3Bi%3A4%3Bs%3A585%3A%22AND+%28a.admin_id+IN+%2812666%2C12773%2C12774%2C12775%2C12776%2C12777%2C12778%2C12779%2C12780%2C12781%2C12782%2C12783%2C12784%2C12785%2C12786%2C12787%2C12788%2C12789%2C12790%2C12791%2C12792%2C12793%2C12795%2C12796%2C12797%2C12798%2C12799%2C12800%2C12801%2C12802%2C12803%2C12804%2C12805%2C12808%2C12812%2C12813%2C12814%2C12815%2C12816%2C12817%2C12818%2C12820%2C12821%2C12822%2C12823%2C12824%2C12825%2C12826%2C12829%2C12834%2C12835%2C12836%2C12839%2C12840%2C12841%2C12842%2C12843%2C12844%2C12845%2C12848%2C12850%2C12852%2C12853%2C12854%2C12855%2C12856%2C12857%2C12858%2C12859%2C12860%2C12861%2C12862%2C12863%2C12864%2C12865%2C12866%2C12867%2C12868%2C12872%2C12873%2C12874%2C12875%2C12877%2C12879%2C12880%2C12881%2C12882%2C12883%2C12884%2C12885%2C12886%2C12887%2C12888%2C12890%29%29%22%3B%7Ds%3A4%3A%22join%22%3BN%3Bs%3A5%3A%22order%22%3Ba%3A2%3A%7Bi%3A0%3Ba%3A2%3A%7Bi%3A0%3Bs%3A17%3A%22u.create_datetime%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7Di%3A1%3Ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22u.user_id%22%3Bi%3A1%3Bs%3A4%3A%22desc%22%3B%7D%7Ds%3A7%3A%22records%22%3Bi%3A5%3Bs%3A6%3A%22offset%22%3Bi%3A0%3Bs%3A9%3A%22list_type%22%3BN%3Bs%3A15%3A%22current_user_id%22%3BN%3B%7D; _gat_gtag_UA_52300713_3=1");
		restApiTest.setRequestHeaders(HTTPConstants.ContentType, "multipart/form-data");
		restApiTest.setUrl(lUrl+"/social/post");
		restApiTest.setRequestType(HTTPConstants.POST);
//		restApiTest.setRequestBody("post_id", "");
		restApiTest.setRequestBody("post_message", "This is automated restapi status");
		restApiTest.setRequestBody("social_network","facebook");
		restApiTest.setRequestBody("page_id", "472062480203994");
//		restApiTest.setRequestBody("link", "");
		restApiTest.setRequestBody("operation", "create");
		restApiTest.setRequestBody("post_type", "status");
//		restApiTest.setRequestBody("post_source", "undefined");
//		restApiTest.setRequestBody("property_id", "");
		restApiTest.execute();
		response = restApiTest.getResponse();
		assertTrue(validateResponse(), "Unable to validate the response..");
		
	}
	private boolean validateResponse() {
		boolean isValidated = false;
		int status = response.getStatus();
		if(HttpStatus.SC_OK==status) {
			System.out.println(response.getJsonResponse().toString());
			isValidated = true;
		}
		return isValidated;
	}
	

}
