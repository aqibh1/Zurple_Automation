/**
 * 
 */
package com.restapi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adar
 *
 */
public class HeadersConfig {
	
	public static Map<String, String> getMultipartFormDataHeaders(String pCookie) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HTTPConstants.Cookie, pCookie);
//		headers.put(HTTP.CONTENT_TYPE,ContentType.MULTIPART_FORM_DATA.toString());
////		headers.put(HTTP.CONTENT_LEN,"0");
//		headers.put("Referer", "https://my.stage01.zurple.com/social/createpost");
//		headers.put(HTTP.TARGET_HOST,"https://my.stage01.zurple.com");
//		headers.put("Origin","https://my.stage01.zurple.com");
//		headers.put("Accept","*/*");
		return headers;
	}
	public static Map<String, String> getSAPIHeaders(String pAuthToken) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HTTPConstants.Authorization, pAuthToken);
		return headers;
	}
	public static Map<String, String> getSellerLeadHeaders(String pContentType) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HTTPConstants.ContentType, pContentType);
		return headers;
	}
}
