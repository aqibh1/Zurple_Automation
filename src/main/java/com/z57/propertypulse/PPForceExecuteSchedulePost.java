/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPForceExecuteSchedulePost extends Page{
	
	String result_xpath = "//h2[text()='EXECUTING SCHEDULE']/following::div[contains(text(),'["+FrameworkConstants.DYNAMIC_VARIABLE+"] =>')]";
	
	@FindBy(xpath="//h2[text()='EXECUTING SCHEDULE']")
	WebElement executingSchedule;
	
	String listing_detail_xpath = "//h3[text()='LISTING DETAILS']/following::div[contains(text(),'["+FrameworkConstants.DYNAMIC_VARIABLE+"] =>')]";

	String results_by_scheduled_jobs_xpath = "//h3[contains(text(),'as captured by scheduled jobs')]/following::div[contains(text(),'["+FrameworkConstants.DYNAMIC_VARIABLE+"] =>')]";

	@FindBy(xpath="//h3[text()='LISTING DETAILS'])")
	WebElement listing_details;
	
	@FindBy(xpath="//h3[contains(text(),'QA TEST TOOL')]")
	WebElement test_tool_heading;
	
	String facebook_listing_id_xpath = "//h3[text()='FACEBOOK POSTS']/following::div[contains(text(),'["+FrameworkConstants.DYNAMIC_VARIABLE+"] =>')]";

	String twitter_listing_id_xpath = "//h3[text()='TWITTER POSTS']/following::div[contains(text(),'listing_id:') and contains(text(),'TWITTER POST')]";
	
//	private ActionHelper actionHelper;
	public PPForceExecuteSchedulePost() {
		// TODO Auto-generated constructor stub
	}
	public PPForceExecuteSchedulePost(WebDriver pWebDriver) {
		driver = pWebDriver;
//		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isExecutingSchedulePage() {
		return ActionHelper.waitForElementToBeVisible(driver, executingSchedule, 30);
	}
	public String getResultMessage() {
		String resultMessage = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, result_xpath, "result"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
	
	public String getStatus() {
		String resultMessage = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, result_xpath, "status"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
	
	public String getListingPrice() {
		String lListingPrice = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, listing_detail_xpath, "list_price"));
		lListingPrice = lListingPrice.split("=>")[1].trim();
		return lListingPrice;
	}
	
	public String getScheduledPostsStatus() {
		String resultMessage = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, results_by_scheduled_jobs_xpath, "status"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
	
	public String getStatusMessage() {
		String resultMessage = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, results_by_scheduled_jobs_xpath, "message"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
	public boolean isListingDetailsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, listing_details, 30);
	}
	public boolean isQATestToolPage() {
		return ActionHelper.waitForElementToBeVisible(driver, test_tool_heading, 30);
	}
	public boolean isListingIdExistFacebook(String pListingId) {
		boolean found = false;
		List<WebElement> listing_id_list = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, facebook_listing_id_xpath, "listing_id"));
		for(WebElement element: listing_id_list) {
			if(element.getText().contains(pListingId)) {
				found = true;
				break;
			}
		}
		return found;
	}
	public String getFacebookListingStatus() {
		WebElement statusElement = ActionHelper.getDynamicElement(driver, facebook_listing_id_xpath, "status");
		String lStatus = statusElement.getText().split("=>")[1].trim();
		return lStatus;

	}
	
	public boolean isListingIdExistTwitter(String pListingId) {
		boolean found = false;
		List<WebElement> listing_id_list = ActionHelper.getListOfElementByXpath(driver, twitter_listing_id_xpath);
		for(WebElement element: listing_id_list) {
			if(element.getText().contains(pListingId)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public String getTwitterListingStatus() {
		WebElement statusElement = ActionHelper.getDynamicElement(driver, facebook_listing_id_xpath, "status");
		String lStatus = statusElement.getText().split("=>")[1].trim();
		return lStatus;

	}
}
