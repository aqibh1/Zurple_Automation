/**
 * 
 */
package com.restapi;

import java.io.IOException;
import java.net.ProtocolException;

import org.apache.http.client.ClientProtocolException;

import resources.AbstractAPIRestTest;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class RestAPITest extends AbstractAPIRestTest {

	HTTPRequestClient request;
	HTTPResponseClient response;
	
	public RestAPITest(){
		request = new HTTPRequestClient();
	}

	//Set request parameters
	public void setUrl(String pURL) {
		request.setUrl(pURL);
	}
	
	public String getUrl() {
		return request.getUrl();
	}
	public void setRequestType(String pRequestType) {
		try {
			request.setRequestType(pRequestType);
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			AutomationLogger.error("Error setting up Request type "+pRequestType);
			e.printStackTrace();
		}
	}
	public void setRequestHeaders(String pKey, String pValue) {
		request.setHeader(pKey, pValue);
	}
	public void setRequestBody(String pKey, String pValue) {
		request.setBody(pKey, pValue);
	}
	
	public void execute() {
		try {
			request.execute();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HTTPResponseClient getResponse() {
		return request.getResponse();
	}
}
