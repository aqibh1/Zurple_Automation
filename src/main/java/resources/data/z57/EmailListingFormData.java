package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailListingFormData extends JSONData{
	
	EmailListingFormData emailListingFormData;
	
	@JsonProperty("lead_name")
	String leadName;
	
	@JsonProperty("lead_email")
	String leadEmail;
	
	@JsonProperty("lead_phone_number")
	String leadPhoneNumber;
	
	@JsonProperty("comments")
	String comments;
	
	@JsonProperty("recipient_one_name")
	String recipientOneName;
	
	@JsonProperty("recipient_one_email")
	String recipientOneEmail;
	
	@JsonProperty("recipient_two_name")
	String recipientTwoName;
	
	@JsonProperty("recipient_two_email")
	String recipientTwoEmail;
	
	@JsonProperty("listing_url")
	String listingUrl;

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

	public String getRecipientOneName() {
		return recipientOneName;
	}

	public void setRecipientOneName(String recipientOneName) {
		this.recipientOneName = recipientOneName;
	}

	public String getRecipientOneEmail() {
		return recipientOneEmail;
	}

	public void setRecipientOneEmail(String recipientOneEmail) {
		this.recipientOneEmail = recipientOneEmail;
	}

	public String getRecipientTwoName() {
		return recipientTwoName;
	}

	public void setRecipientTwoName(String recipientTwoName) {
		this.recipientTwoName = recipientTwoName;
	}

	public String getRecipientTwoEmail() {
		return recipientTwoEmail;
	}

	public void setRecipientTwoEmail(String recipientTwoEmail) {
		this.recipientTwoEmail = recipientTwoEmail;
	}

	public String getListingUrl() {
		return listingUrl;
	}

	public void setListingUrl(String listingUrl) {
		this.listingUrl = listingUrl;
	}
	public EmailListingFormData() {
		// TODO Auto-generated constructor stub
	}
	public EmailListingFormData(String pFileName){
		emailListingFormData=(EmailListingFormData) setDataMapper(pFileName, EmailListingFormData.class);
	}
	public EmailListingFormData getEmailListingData() {
		return emailListingFormData;
	}
}
