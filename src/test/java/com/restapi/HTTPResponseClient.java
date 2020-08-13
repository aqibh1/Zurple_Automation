/**
 * 
 */
package com.restapi;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

/**
 * @author adar
 *
 */
public class HTTPResponseClient {
	
	HttpResponse response;

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}
	
	public int getStatus() {
		int status = 0;
		if(response!=null) {
			status = response.getStatusLine().getStatusCode();
		}
		return status;
	}
	public JSONObject getJsonResponse() {
		JSONObject jObject;
		String strResponse = "";
		if(response!=null) {
			try {
				
				strResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			} catch (UnsupportedOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jObject = new JSONObject(strResponse);
		return jObject;
	}
}
