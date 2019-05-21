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
