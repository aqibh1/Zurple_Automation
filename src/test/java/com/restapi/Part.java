/**
 * 
 */
package com.restapi;

/**
 * @author adar
 *
 */
public class Part {
	public enum PartType {
		FILE,
		STRING
	}
	
	private String content;
	private PartType type;
	
	public Part(){}
	
	public Part(String content, PartType type) {
		this.content = content;
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PartType getType() {
		return type;
	}
	public void setType(PartType type) {
		this.type = type;
	}
}
