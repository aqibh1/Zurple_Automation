/**
 * 
 */
package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adar
 *
 */
public class PPSocialData extends JSONData<PPSocialData>{
	
	@JsonProperty("facebook_status")
	String status;
	
	@JsonProperty("image_path")
	String image;
	
	@JsonProperty("post_schedule")
	String schedule;
	
	@JsonProperty("facebook_page")
	String facebookPage;
	
	@JsonProperty("start_date")
	String startDate;
	
	@JsonProperty("end_date")
	String endDate;
	
	@JsonProperty("time")
	String time;
	
	@JsonProperty("repeat_days")
	String repeatDays;
	
	@JsonProperty("promote_listing")
	String promoteListing;

	@JsonProperty("property_link")
	String propertyLink;
	
	
	public String getPropertyLink() {
		return propertyLink;
	}

	public void setPropertyLink(String propertyLink) {
		this.propertyLink = propertyLink;
	}

	public String getPromoteListing() {
		return promoteListing;
	}

	public void setPromoteListing(String promoteListing) {
		this.promoteListing = promoteListing;
	}

	public String getStatus() {
		return status;
	}

	public String getImage() {
		return image;
	}

	public String getSchedule() {
		return schedule;
	}

	public String getFacebookPage() {
		return facebookPage;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getTime() {
		return time;
	}

	public String getRepeatDays() {
		return repeatDays;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setRepeatDays(String repeatDays) {
		this.repeatDays = repeatDays;
	}
	
	PPSocialData socialData;
	
	public PPSocialData getSocialData() {
		return socialData;
	}
	public void setSocialData(String pDataFile) {
		this.socialData = (PPSocialData)setDataMapper(pDataFile, PPSocialData.class);
	}
	
	public PPSocialData() {
		
	}
	public PPSocialData(String pFileName) {
		setSocialData(pFileName);
	}

}
