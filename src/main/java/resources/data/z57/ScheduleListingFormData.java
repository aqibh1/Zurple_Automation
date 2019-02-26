/**
 * 
 */
package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adar
 *
 */
public class ScheduleListingFormData extends JSONData<ScheduleListingFormData>{
	ScheduleListingFormData scheduleListingFormData;
	
	@JsonProperty("lead_name")
	String leadName;
	
	@JsonProperty("lead_email")
	String leadEmail;
	
	@JsonProperty("lead_phone_number")
	String leadPhoneNumber;
	
	@JsonProperty("comments")
	String comments;
	
	@JsonProperty("listing_url")
	String listingUrl;
	
	public ScheduleListingFormData() {
		
	}
	public ScheduleListingFormData(String pFileName){
		scheduleListingFormData=(ScheduleListingFormData) setDataMapper(pFileName, ScheduleListingFormData.class);
	}
	public ScheduleListingFormData getScheduleListingFormData() {
		return scheduleListingFormData;
	}
	
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public String getLeadEmail() {
		return leadEmail;
	}
	public void setLeadEmail(String leadEmail) {
		this.leadEmail = leadEmail;
	}
	public String getLeadPhoneNumber() {
		return leadPhoneNumber;
	}
	public void setLeadPhoneNumber(String leadPhoneNumber) {
		this.leadPhoneNumber = leadPhoneNumber;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getListingUrl() {
		return listingUrl;
	}
	public void setListingUrl(String listingUrl) {
		this.listingUrl = listingUrl;
	}
	
	



}
