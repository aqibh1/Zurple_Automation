/**
 * 
 */
package com.restapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;

/**
 * @author adar
 *
 */
public class HeadersConfig {
	
	public static Map<String, String> getMultipartFormDataHeaders(String pCookie) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HTTPConstants.Cookie, pCookie);
		headers.put(HTTPConstants.ContentType,ContentType.MULTIPART_FORM_DATA.toString());
//		headers.put("Referer", "https://my.stage01.zurple.com/social/createpost");
		return headers;
	}
}
