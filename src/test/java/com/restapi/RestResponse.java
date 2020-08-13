/**
 * 
 */
package com.restapi;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/**
 * @author adar
 *
 */
public class RestResponse {
	private int status;
	private long timeOfCall;
	private String content;
	private Header[] headers;
	private HttpEntity entity;
	private String reasonPhrase;
	private RestRequest request;

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getTimeOfCall() {
		return timeOfCall;
	}
	public void setTimeOfCall(long timeOfCall) {
		this.timeOfCall = timeOfCall;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public HttpEntity getEntity() {
		return entity;
	}
	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public String getHeader(String headerName){

		for(Header header: headers){
			if(header.getName().equals(headerName)){
				return header.getValue();
			}
		}

		return null;
	}
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}


	public RestRequest getRequest() {
		return request;
	}
	public void setRequest(RestRequest request) {
		this.request = request;
	}

	public org.json.JSONObject getJsonResponse() throws Exception{
		return new org.json.JSONObject(this.content);
	}

	

//	public StringToStringsMap getHeadersMap() {
//		StringToStringsMap map = new StringToStringsMap();
//
//		for (Header header : headers) {
//			String key = header.getName();
//			String value = header.getValue();
//			map.put(key, value);
//		}
//		return map;
//	}
}
