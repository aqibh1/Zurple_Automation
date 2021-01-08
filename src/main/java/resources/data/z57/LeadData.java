package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadData extends JSONData{
	
	LeadData leadData;
	
	@JsonProperty("lead_name")
	String leadName;
	
	@JsonProperty("lead_email")
	String leadEmail;
	
	@JsonProperty("lead_phone")
	String leadPhone;
	
	@JsonProperty("lead_DOB")
	String leadDOB;
	
	@JsonProperty("lead_address")
	String leadAddress;
	
	@JsonProperty("lead_city")
	String leadCity;
	
	@JsonProperty("lead_state")
	String leadState;
	
	@JsonProperty("lead_zip")
	String leadZip;
	
	@JsonProperty("lead_status")
	String leadStatus;
	

	public String getLeadStatus() {
		return leadStatus;
	}
	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}
	public void setLeadData(LeadData leadData) {
		this.leadData = leadData;
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
	public String getLeadPhone() {
		return leadPhone;
	}
	public void setLeadPhone(String leadPhone) {
		this.leadPhone = leadPhone;
	}
	public String getLeadDOB() {
		return leadDOB;
	}
	public void setLeadDOB(String leadDOB) {
		this.leadDOB = leadDOB;
	}
	public String getLeadAddress() {
		return leadAddress;
	}
	public void setLeadAddress(String leadAddress) {
		this.leadAddress = leadAddress;
	}
	public String getLeadCity() {
		return leadCity;
	}
	public void setLeadCity(String leadCity) {
		this.leadCity = leadCity;
	}
	public String getLeadState() {
		return leadState;
	}
	public void setLeadState(String leadState) {
		this.leadState = leadState;
	}
	public String getLeadZip() {
		return leadZip;
	}
	public void setLeadZip(String leadZip) {
		this.leadZip = leadZip;
	}
	public LeadData(String pFileName){
		leadData=(LeadData) setDataMapper(pFileName, LeadData.class);
	}
	public LeadData getLeadData() {
		return leadData;
	}
	public LeadData(){
	
	}
	
}
