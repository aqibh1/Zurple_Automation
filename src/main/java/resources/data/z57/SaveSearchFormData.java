package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveSearchFormData extends JSONData{

	SaveSearchFormData emailListingFormData;

	@JsonProperty("lead_name")
	String leadName;

	@JsonProperty("lead_email")
	String leadEmail;

	@JsonProperty("lead_phone_number")
	String leadPhoneNumber;

	@JsonProperty("title")
	String title;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListingUrl() {
		return listingUrl;
	}

	public void setListingUrl(String listingUrl) {
		this.listingUrl = listingUrl;
	}
	public SaveSearchFormData() {
		// TODO Auto-generated constructor stub
	}
	public SaveSearchFormData(String pFileName){
		emailListingFormData=(SaveSearchFormData) setDataMapper(pFileName, SaveSearchFormData.class);
	}
	public SaveSearchFormData getEmailListingData() {
		return emailListingFormData;
	}
}
