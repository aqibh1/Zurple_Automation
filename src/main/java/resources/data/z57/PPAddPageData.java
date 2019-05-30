/**
 * 
 */
package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adar
 *
 */
public class PPAddPageData extends JSONData<PPAddPageData>{
	
	@JsonProperty("page_title")
	String pageTitle;
	
	@JsonProperty("page_body")
	String pageBody;
	
	@JsonProperty("page_body_update")
	String pageBodyUpdate;
	
	@JsonProperty("page_delete")
	boolean pageDelete;
	
	@JsonProperty("lead_capture_enabled")
	String leadCaptureEnabled;
	
	@JsonProperty("lead_strength")
	String leadStrength;
	
	@JsonProperty("lead_capture_prompt")
	String leadCapturePrompt;
	
	@JsonProperty("lead_strength_update")
	String leadStrengthUpdate;
	
	@JsonProperty("page_layout")
	String pageLayout;
	
	@JsonProperty("page_layout_sidebar")
	String pageLayoutSidebar;
	
	@JsonProperty("page_layout_update")
	String pageLayoutUpdate;
	
	@JsonProperty("page_layout_sidebar_update")
	String pageLayoutSidebarUpdate;
	
	@JsonProperty("widget_type")
	String widgetType;

	public String getPageTitle() {
		return pageTitle;
	}

	public String getPageBody() {
		return pageBody;
	}

	public String getPageBodyUpdate() {
		return pageBodyUpdate;
	}

	public boolean isPageDelete() {
		return pageDelete;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public void setPageBody(String pageBody) {
		this.pageBody = pageBody;
	}

	public void setPageBodyUpdate(String pageBodyUpdate) {
		this.pageBodyUpdate = pageBodyUpdate;
	}

	public void setPageDelete(boolean pageDelete) {
		this.pageDelete = pageDelete;
	}
	
	public String getLeadCaptureEnabled() {
		return leadCaptureEnabled;
	}

	public String getLeadStrength() {
		return leadStrength;
	}

	public String getLeadCapturePrompt() {
		return leadCapturePrompt;
	}

	public void setLeadCaptureEnabled(String leadCaptureEnabled) {
		this.leadCaptureEnabled = leadCaptureEnabled;
	}

	public void setLeadStrength(String leadStrength) {
		this.leadStrength = leadStrength;
	}

	public void setLeadCapturePrompt(String leadCapturePrompt) {
		this.leadCapturePrompt = leadCapturePrompt;
	}

	public String getLeadStrengthUpdate() {
		return leadStrengthUpdate;
	}

	public void setLeadStrengthUpdate(String leadStrengthUpdate) {
		this.leadStrengthUpdate = leadStrengthUpdate;
	}

	public String getPageLayout() {
		return pageLayout;
	}

	public String getPageLayoutSidebar() {
		return pageLayoutSidebar;
	}

	public String getPageLayoutUpdate() {
		return pageLayoutUpdate;
	}

	public String getPageLayoutSidebarUpdate() {
		return pageLayoutSidebarUpdate;
	}

	public void setPageLayout(String pageLayout) {
		this.pageLayout = pageLayout;
	}

	public void setPageLayoutSidebar(String pageLayoutSidebar) {
		this.pageLayoutSidebar = pageLayoutSidebar;
	}

	public void setPageLayoutUpdate(String pageLayoutUpdate) {
		this.pageLayoutUpdate = pageLayoutUpdate;
	}

	public void setPageLayoutSidebarUpdate(String pageLayoutSidebarUpdate) {
		this.pageLayoutSidebarUpdate = pageLayoutSidebarUpdate;
	}

	private PPAddPageData addPageData;

	public PPAddPageData() {
		
	}
	public PPAddPageData(String pFileName) {
		setAddPageData(pFileName);
	}
	public PPAddPageData getAddPageData() {
		return addPageData;
	}

	public void setAddPageData(String pFileName) {
		addPageData=(PPAddPageData) setDataMapper(pFileName, PPAddPageData.class);
	}
	
	

}