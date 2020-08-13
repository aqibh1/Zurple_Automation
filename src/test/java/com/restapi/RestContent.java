/**
 * 
 */
package com.restapi;

import java.util.Map;

/**
 * @author adar
 *
 */
public class RestContent {
	private boolean multiPart;
	private String body;
	private Map<String, Part> parts;
	
	public RestContent(){}
	
	public RestContent(String body){
		this.body = body;
	}
	
	
	public boolean isMultiPart() {
		return multiPart;
	}
	public void setMultiPart(boolean multiPart) {
		this.multiPart = multiPart;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Map<String, Part> getParts() {
		return parts;
	}
	public void setParts(Map<String, Part> parts) {
		this.parts = parts;
	}

}
