/**
 * 
 */
package com.restapi;

import java.io.File;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import com.restapi.Part.PartType;

/**
 * @author adar
 *
 */
public class RestRequest {


	private String url;
	private String method;
	private Map<String, String> headers;
	private Map<String, Object> params;
	private String stringBody;
	private HttpEntity multiPartEntity;
	private RestContent restContent;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		//setUrl(url, true);
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getStringBody() {
		return stringBody;
	}
	public void setStringBody(String stringBody) {
		this.stringBody = stringBody;
	}
	public HttpEntity getMultiPartEntity() {
		return multiPartEntity;
	}
	public void setMultiPartEntity(HttpEntity multiPartEntity) {
		this.multiPartEntity = multiPartEntity;
	}
	public RestContent getRestContent() {
		return restContent;
	}
	public void setRestContent(RestContent restContent) {
		if(restContent == null) {
			return;
		}
		this.restContent = restContent;
		
		if(restContent.isMultiPart()) {
			setMultipartEntityFromRestContent(restContent);
		}
		else {
			setStringBodyFromRestContent(restContent);
		}
	}
	private void setMultipartEntityFromRestContent(RestContent restContent) {
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		
		Map<String, Part> parts = restContent.getParts();
		
		for (String partName : parts.keySet()) {
			Part part = parts.get(partName);
			PartType partType = part.getType();
			
			switch (partType) {
			case FILE:
				builder.addPart(partName, new FileBody(new File(part.getContent())));
				break;
			case STRING:
				builder.addTextBody(partName, part.getContent());
				break;

			default:
				break;
			}
		}
		setMultiPartEntity(builder.build());
	}
	private void setStringBodyFromRestContent(RestContent restContent) {
		setStringBody(restContent.getBody());
	}

}
