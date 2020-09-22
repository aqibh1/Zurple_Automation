package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSelectCampaignAlert;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.blocks.zurple.ZBOLeadDetailsSearchBlock;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ZBOLeadDetailPage extends Page{

	boolean isRefreshPageRequired = true;

	@FindBy(xpath="//div[@class='row']/descendant::h3[text()='Lead Detail']")
	WebElement leadDetailHeading;

	String propertyViewed = "//div[@id='property-views-stats']/descendant::td";

	@FindBy(xpath="//div[@id='property-views-stats']/descendant::td/div[text()='Loading...']")
	WebElement loading;

	String lead_name = "//div[@id='lead-details-main']/descendant::h2";

	String lead_email_xpath = "//span[@class='lead-details-detail']/descendant::a[@title='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";

	String email_subject = ".yui-dt-col-subject";

	@FindBy(className="yui-dt5-col-subject")
	WebElement email_Subject;

	@FindBy(xpath="//span[@class='lead-details-detail wrap']")
	WebElement lead_address;

	String prop_details_xpath = "//span[@class='lead-details-detail']";

	@FindBy(xpath="//div[@id='z-lead-notes']/descendant::td[@headers='yui-dt0-th-note ']/div[@class='yui-dt-liner']")
	WebElement notes_text;

	//Sorting Headers

	String sorting_column_ascending_xpath = "//th[@aria-label='"+FrameworkConstants.DYNAMIC_VARIABLE+": activate to sort column ascending']";

	String sorting_column_descending_xpath = "//th[@aria-label='"+FrameworkConstants.DYNAMIC_VARIABLE+": activate to sort column descending']";

	String lead_details_xpath = "//span[@class='lead-details-title' and text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/following::span[1]";

	@FindBy(xpath="//div[@class='detail-row-inline bottom-small-padd']/descendant::i[@title='Phone']/parent::span/parent::span")
	WebElement phoneNumber;

	@FindBy(xpath="//span[@class='lead-details-title' and text()='Website:']/following::a[1]")
	WebElement website_element;

	@FindBy(xpath="//ul[@class='z-lead-preferences z-grid-view-content']")
	WebElement email_preferences;

	@FindBy(xpath="//div[@id='z-lead-notes']/descendant::div[text()='No records found.']")
	WebElement lead_notes_no_record;

	String email_prefernces_xpath = "//ul[@class='z-lead-preferences z-grid-view-content']/descendant::span";
	String leadName_xpath = "//div[@id='lead-details-main']/descendant::h2[@class='panel-title']";

	@FindBy(xpath="//div[@id='z-activity-details-alert-emails-grid']/descendant::div[text()='Welcome to our new site']")
	WebElement welcome_email;

	@FindBy(xpath="//span[@class='glyphicon email-validation-glyph glyphicon-ok-circle verified']")
	WebElement verified_email_tick;

	@FindBy(xpath="//span[@class='z-lead-primary']")
	WebElement lead_primary_alerts;

	@FindBy(id="z-activity-details-alerts")
	WebElement alerts_tab_button;

	String alerts_type_xpath="//span[@class='z-alert-type']";

	String lead_activity_xpath="//span[@class='z-lead-activity']/span";

	@FindBy(id="z-activity-details-alert-emails")
	WebElement zurple_messages_tab_button;

	@FindBy(xpath="//div[@id='z-activity-details-alert-emails-grid']/descendant::div[text()='Quick Question']")
	WebElement quick_question_subject;

	@FindBy(xpath="//div[@id='z-activity-details-alert-emails-grid']/descendant::td[@headers='yui-dt4-th-messageDateTime ']/div")
	WebElement date_time_email;

	@FindBy(id="lead_status")
	WebElement lead_prospect_dropdown;

	@FindBy(id="z-activity-details-sent")
	WebElement myMessages_tab_button;
	
	@FindBy(id="z-activity-details-messages-to-admin")
	WebElement leadMessages_tab_button;

	String myMessages_emails_xpath = "//div[@id='z-activity-details-sent-grid']/descendant::div[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";

	@FindBy(id="text_area_note")
	WebElement note_text_area;

	@FindBy(id="submit_save_note")
	WebElement save_note_button;

	@FindBy(xpath="//button[text()='OK']")
	WebElement ok_button_notes;

	String notes_Added_xpath = "//div[@id='z-lead-notes']/descendant::td[@headers='yui-dt0-th-note ']/div";
	String notes_date_xpath = "//div[@id='z-lead-notes']/descendant::td[@headers='yui-dt0-th-date ']/div";

	@FindBy(id="z-activity-details-searches")
	WebElement lead_searches;

	@FindBy(id="z-activity-details-searches-tab")
	WebElement search_block;

	String list_of_disabled_nav_bts = "//ul[@id='lead_detail_navbar']/descendant::a[contains(@class,'lead-btn-disabled') and @title]";

	@FindBy(id="disabled-enroll-campaign")
	WebElement enrollInCampaign_button;

	@FindBy(id="text_area_reminder")
	WebElement text_area_reminder;

	@FindBy(id="task_reminder_date_1")
	WebElement taskReminder_date_input;

	@FindBy(id="submit_save_reminder")
	WebElement save_reminder_button;

	String xpathForTestingSubject = "//div[@id='z-activity-details-sent-grid']/descendant::td[@headers='yui-dt5-th-subject ']/div";
	
	@FindBy(xpath="//div[@id='z-activity-details-sent-grid']/descendant::td[@headers='yui-dt5-th-subject ']/div")
	WebElement flyer_email;
	
	@FindBy(xpath="//div[@id='z-activity-details-messages-to-admin-grid']/descendant::td[@headers='yui-dt5-th-subject ']/div")
	WebElement reply_email;
	
	String lead_messages_subject = "//div[@id='z-activity-details-messages-to-admin-grid']/descendant::td[@headers='yui-dt5-th-subject ']/div";

	@FindBy(xpath="//div[@id='z-activity-details-sent-grid']/descendant::td[@headers='yui-dt5-th-messageDateTime ']/div")
	WebElement xpathForTestingDate;
	
	String lead_messages_date = "//div[@id='z-activity-details-messages-to-admin-grid']/descendant::td[@headers='yui-dt5-th-messageDateTime ']/div";

	@FindBy(xpath="//button[text()='Done']")
	WebElement done_date_button;

	@FindBy(xpath="//button[text()='Now']")
	WebElement now_date_button;

	@FindBy(id="assigned-to")
	WebElement lead_assigned_to_agent;
	
	@FindBy(id="reassign-lead")
	WebElement lead_reassign_button;
	
	@FindBy(id="admins_list")
	WebElement admin_list_dropdown;

	@FindBy(id="reassign-lead-save")
	WebElement save_lead_Assignment_button;
	
	@FindBy(xpath="//div[@role='alert' and contains(text(),'email address has bounced')]")
	WebElement bounced_email_error;
	
	@FindBy(xpath="//div[@role='alert' and contains(text(),'unable to send emails')]")
	WebElement bounced_email_attention_error;
	
	@FindBy(xpath="//span[@class='glyphicon email-validation-glyph glyphicon-exclamation-sign rejected']")
	WebElement bounced_exclamationmark;
	
	String email_preferences_label="//ul[@class='z-lead-preferences z-grid-view-content']/descendant::span[@class='z-lead-preferences-label']";
	String email_preferences_value = "//ul[@class='z-lead-preferences z-grid-view-content']/descendant::span[@class='z-lead-preferences-data']";
	
	@FindBy(id="z-activity-details-scheduled-grid")
	WebElement scheduledEmails;
	
	String scheduled_messages_list = "//div[@id='z-activity-details-scheduled-grid']/descendant::td[contains(@class,'subject')]/div[@class='yui-dt-liner']";
	
	@FindBy(id="enroll-campaign")
	WebElement ENROLL_IN_CAMPAIGN_BUTTON;
	
	String enrollInCampaign = "//span[@id='campaign-title']/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//a[@class='btn lead-btn-disabled btn-sm' and text()='Send Email']")
	WebElement sendEmail_disabled_button;
	String sendTextMessage_disabled_button = "//a[@class='btn lead-btn-disabled btn-sm']";
	@FindBy(id="disabled-assign-campaign-button")
	WebElement campaign_disabled_button;
	
	@FindBy(xpath="//li[@title='Return']")
	WebElement return_label;
	@FindBy(xpath="//li[@title='Browsing']")
	WebElement browsing_label;
	
	@FindBy(id="z-activity-details-favorites")
	WebElement favorites_tab_button;
	
	private ZBOLeadDetailsSearchBlock leadDetailSearchBlock;
	private ZBOSelectCampaignAlert selectCampaign;

	public ZBOLeadDetailPage() {

	}

	public ZBOLeadDetailPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		setLeadDetailSearchBlock();
		setSelectCampaign();
		PageFactory.initElements(driver, this);
	}

	public ZBOLeadDetailsSearchBlock getLeadDetailSearchBlock() {
		return leadDetailSearchBlock;
	}

	public void setLeadDetailSearchBlock() {
		this.leadDetailSearchBlock = new ZBOLeadDetailsSearchBlock(driver);
	}

	public ZBOSelectCampaignAlert getSelectCampaign() {
		return selectCampaign;
	}

	public void setSelectCampaign() {
		this.selectCampaign = new ZBOSelectCampaignAlert(driver);
	}

	public boolean isLeadDetailPage() {
		return ActionHelper.waitForElementToBeVisible(driver, leadDetailHeading, 30);
	}

	public boolean isPropertyTracked(String pPropTitle) {
		isLoading();
		boolean isPropTracked = false;
		List<WebElement> list_of_props = ActionHelper.getListOfElementByXpath(driver, propertyViewed);
		for(WebElement element: list_of_props) {
			if(!element.getText().trim().isEmpty() && pPropTitle.contains(element.getText().trim())) {
				isPropTracked = true;
				break;
			}
		}
		return isPropTracked;

	}
	private boolean isLoading() {
		return ActionHelper.waitForElementToBeDisappeared(driver, loading);
	}
	public boolean isLeadNameExist(String pName) {
		boolean isLeadExist = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, lead_name);
		for(WebElement element: list_element) {
			if(element.getText().trim().equalsIgnoreCase(pName)) {
				isLeadExist = true;		
				break;
			}
		}
		return isLeadExist;
	}
	public boolean verifyLeadAddress(String pStreet, String pCity, String pState, String pZip) {
		boolean isSuccess = true;
		String lAddress = ActionHelper.getText(driver, lead_address);
		String lAddress_array[] = lAddress.split(",");
		if(!pStreet.equalsIgnoreCase(lAddress_array[0])) {
			isSuccess = false;
		}if(!pCity.equalsIgnoreCase(lAddress_array[1].trim())) {
			isSuccess = false;
		}if(!pState.equalsIgnoreCase(lAddress_array[2].trim())) {
			isSuccess = false;
		}if(!pZip.equalsIgnoreCase(lAddress_array[3].trim())) {
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean verifyProp(String pPropToVerify, String pPropValue) {
		boolean isVerified = false;
		List<WebElement> list_pf_element = ActionHelper.getListOfElementByXpath(driver, prop_details_xpath);
		for(WebElement element: list_pf_element) {
			List<WebElement> list_of_tags_i = element.findElements(By.tagName("i"));
			for(WebElement i_element: list_of_tags_i) {
				if(ActionHelper.getAttribute(i_element, "title").equalsIgnoreCase(pPropToVerify)) {
					if(element.getText().trim().replace(",", "").equalsIgnoreCase(pPropValue)) {
						isVerified = true;
						break;
					}
				}
			}
			if(isVerified) {
				break;
			}
		}
		return isVerified;
	}
	public boolean verifyMinPrice(String pMinPrice) {
		return verifyPropFromNotes("minimum price", pMinPrice.replace(",", "").replace("$", ""));
	}
	public boolean verifyMaxPrice(String pMaxPrice) {
		return verifyPropFromNotes("maximum price", pMaxPrice.replace(",", "").replace("$", ""));
	}
	public boolean verifyMinBeds(String pBeds) {
		return verifyPropFromNotes("minimum bedrooms", pBeds.replace("+", ""));
	}
	public boolean verifyMinBathrooms(String pBaths) {
		return verifyPropFromNotes("minimum bathrooms", pBaths.replace("+", ""));
	}
	public boolean verifyMinSqFeet(String pSqFeet) {
		return verifyPropFromNotes("minimum sqft", pSqFeet);
	}
	public boolean verifyLotSize(String pLotSize) {
		return verifyPropFromNotes("Added lot size", pLotSize);
	}
	public boolean verifyNeighborhood(String pNeighborhood) {
		return verifyPropFromNotes("Added neighborhood", pNeighborhood);
	}
	public boolean verifySchoolDistrict(String pSchoolDistrict) {
		return verifyPropFromNotes("Added school district", pSchoolDistrict);
	}
	public boolean verifyZipCode(String pZipCide) {
		return verifyPropFromNotes("Added zip code", pZipCide);
	}
	public boolean verifyCounty(String pCounty) {
		return verifyPropFromNotes("Added county", pCounty);
	}
	public boolean verifyFeatures(String pFeatures) {
		return verifyPropFromNotes("Added features", pFeatures);
	}
	public boolean verifyPropView(String pPropView) {
		return verifyPropFromNotes("Added property view", pPropView);
	}
	public boolean verifyPropStyle(String pPropStyle) {
		return verifyPropFromNotes("Added property style", pPropStyle);
	}
	public String getLeadDetails(String pPropName) {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, lead_details_xpath, pPropName));
	}
	public String getPhoneNum() {
		return ActionHelper.getText(driver, phoneNumber);
	}
	public String getWebSite() {
		return ActionHelper.getText(driver, website_element);
	}
	public boolean verifyEmailPreferences(String pPrefToVerify, String pPrefValue) {
		boolean isVerified = false;
		ActionHelper.waitForElementToBeVisible(driver, email_preferences, 30);
		List<WebElement> email_pref = email_preferences.findElements(By.tagName("span"));
		for(int i=0;i<email_pref.size();i++) {
			if(ActionHelper.getText(driver, email_pref.get(i)).contains(pPrefToVerify)) {
				isVerified = email_pref.get(i+1).getText().contains(pPrefValue);
				break;
			}
			i++;
		}
		return isVerified;
	}
	public boolean isNotesEmpty() {
		return ActionHelper.isElementVisible(driver, lead_notes_no_record);
	}

	public HashMap<String,String> populateEmailPreferencesMap() {
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, email_prefernces_xpath);
		HashMap<String,String> email_pref_Map = new HashMap<String,String>();

		for(int i=0; i<list_element.size();i++) {
			email_pref_Map.put(list_element.get(i).getText().trim().replace("", "").trim(), list_element.get(i+1).getText().trim());
			i++;
		}
		return email_pref_Map;
	}
	public boolean verifyLeadEmail(String pEmail) {
		return ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_email_xpath, pEmail));
	}

	public boolean verifyEmailDateTime() {
		boolean isSuccess = false;
		ActionHelper.waitForElementToBeVisible(driver, xpathForTestingDate, 30);
		String emailDateTime = ActionHelper.getText(driver, xpathForTestingDate);
		if(!emailDateTime.equals("")) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public boolean verifyLeadMessagesDateTime() {
		String str; 
		boolean isDateVisible = false;
		ActionHelper.waitForStringXpathToBeVisible(driver, lead_messages_date, 30);
		List<WebElement> dateList = ActionHelper.getListOfElementByXpath(driver, lead_messages_date);
		
		for(WebElement element:dateList) {
			str = ActionHelper.getText(driver, element);
			if(str.contains(getYesterdaysDate())) {
				isDateVisible = true;
				break;
			}
		} 
		return isDateVisible;
}
	
	public boolean isWelcomeEmailSent() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, welcome_email, 30, 30);
	}
	public boolean isEmailVerified() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, verified_email_tick, 30, 30);
	}
	public boolean verifySignupAlert() {
		return verifyAlerts("New Sign-up", "");
	}
	public boolean isQuickQuestionEmailGenerated() {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(45);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "300");
			isVerified = verifyQuickQuestionEmailGenerated();
			counter++;
		}
		return isVerified;
	}
	public boolean verifyQuickQuestionEmailGenerated() {
		boolean isVerified = false;
		boolean isEmailExists = false;
		boolean isTimeDateCorrect = false;
		if(ActionHelper.Click(driver, zurple_messages_tab_button)) {
			ActionHelper.staticWait(3);
			isEmailExists = ActionHelper.waitForElementToBeVisible(driver, quick_question_subject, 30);
			if(isEmailExists) {
				isTimeDateCorrect = ActionHelper.getText(driver, date_time_email).contains(getTodaysDate().replace("2020", "20"));
			}
			isVerified = (isEmailExists && isTimeDateCorrect)?true:false;
		}
		return isVerified;
	}
	public boolean verifyLeadReqShowingActivityInAlerts(String pListingAddress) {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(45);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "300");
			isVerified = verifyAlerts("Requested Showing", pListingAddress);
			counter++;
		}
		return isVerified;
	}
	public boolean verifyLeadActivityInAlerts(String pAlertType,String pListingAddress) {
		isRefreshPageRequired = true;
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			isVerified = verifyAlerts(pAlertType, pListingAddress);
			if(!isRefreshPageRequired) {
				break;
			}else {
				ActionHelper.staticWait(25);
				ActionHelper.RefreshPage(driver);
				ActionHelper.ScrollDownByPixels(driver, "300");
			}
			counter++;
		}
		return isVerified;
	}
	public boolean clickAndSelectLeadProspect(String pOption) {
		return ActionHelper.selectDropDownOption(driver, lead_prospect_dropdown, "", pOption);
	}
	private boolean verifyAlerts(String pAlertToVerify, String pAlertValueToVerify) {

		boolean isVerified = false;
		boolean alertVerified = false;
		boolean dateVerified = false;
		List<WebElement> list_lead_activity_list = new ArrayList<WebElement>();
		List<WebElement> list_lead_activity_date_time_list = new ArrayList<WebElement>();
		
		if(ActionHelper.Click(driver, alerts_tab_button)) {
			ActionHelper.staticWait(5);
			switch(pAlertToVerify) {
			case "New Sign-up":
				List<WebElement> list_alert_type = ActionHelper.getListOfElementByXpath(driver, alerts_type_xpath);
				for(WebElement element: list_alert_type) {
					alertVerified = ActionHelper.getText(driver, element).equalsIgnoreCase("New Sign-up");
					if(alertVerified) {
						if(ActionHelper.getText(driver, driver.findElement(By.xpath("//span[@class='z-alert-datetime']")))!=null) {
							String lDate = ActionHelper.getText(driver, driver.findElement(By.xpath("//span[@class='z-alert-datetime']")));
							dateVerified = lDate.contains(getTodaysDate().replace("2020", "20"))?true:false;
						}
					}
				}
				isVerified = (alertVerified && dateVerified)?true:false;
				break;

			case "Requested Showing":
				List<WebElement> list_lead_activity = ActionHelper.getListOfElementByXpath(driver, "//span[@class='z-lead-activity']/span");
				for (WebElement element: list_lead_activity) {
					alertVerified = element.getText().trim().contains("Requested Showing");
					if(alertVerified) {
						alertVerified = element.findElement(By.tagName("a")).getText().trim().contains(pAlertValueToVerify);
						dateVerified = element.getText().trim().contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
					}
				}
				break;

			case "Modified Search Preferences":
				List<WebElement> list_lead_activity_pref = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]");
				for(int i=0;i<list_lead_activity_pref.size();i++) {
					alertVerified = list_lead_activity_pref.get(i).findElement(By.xpath("/descendant::span[@class='z-alert-type']")).getText().trim().contains("Modified Search Preferences");
					if(alertVerified) {
						isRefreshPageRequired = false;
						List<WebElement> list_of_lead_activity = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-lead-activity']/span");
						for(WebElement element: list_of_lead_activity) {
							alertVerified = element.getText().contains(pAlertValueToVerify);
							if(alertVerified) {
								break;
							}
						}
						dateVerified = list_lead_activity_pref.get(i).getText().trim().contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
					}
				}
				break;
			case "Homeowner Asked for a CMA":
				List<WebElement> list_lead_activity_cma = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-type']");
				List<WebElement> list_lead_activity_date_time = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-datetime']");

				for(int i=0;i<list_lead_activity_cma.size();i++) {
					alertVerified = ActionHelper.getText(driver, list_lead_activity_cma.get(i)).contains("Homeowner Asked for a CMA") ;
					if(alertVerified) {

						dateVerified = ActionHelper.getText(driver,list_lead_activity_date_time.get(i)).contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
					}
				}
				break;
			
			case "Lots of Browsing":
				List<WebElement> list_lead_activity_high = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-type']");
				List<WebElement> list_lead_high_activity_date_time = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-datetime']");

				for(int i=0;i<list_lead_activity_high.size();i++) {
					alertVerified = ActionHelper.getText(driver, list_lead_activity_high.get(i)).contains("Lots of Browsing") ;
					if(alertVerified) {

						dateVerified = ActionHelper.getText(driver,list_lead_high_activity_date_time.get(i)).contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
					}
				}
			case "High Return":
				List<WebElement> list_lead_activity_high_return = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-type']");
				List<WebElement> list_lead_high_return_activity_date_time = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-datetime']");

				for(int i=0;i<list_lead_activity_high_return.size();i++) {
					alertVerified = ActionHelper.getText(driver, list_lead_activity_high_return.get(i)).contains("High Return") ;
					if(alertVerified) {

						dateVerified = ActionHelper.getText(driver,list_lead_high_return_activity_date_time.get(i)).contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
					}
				}
			case "Agent Inquiry":
				list_lead_activity_list = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-type']");
				list_lead_activity_date_time_list = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-datetime']");

				for(int i=0;i<list_lead_activity_list.size();i++) {
					alertVerified = ActionHelper.getText(driver, list_lead_activity_list.get(i)).contains("Agent Inquiry") ;
					if(alertVerified) {

						dateVerified = ActionHelper.getText(driver,list_lead_activity_date_time_list.get(i)).contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
					}
				}
			case "Seller Inquiry":
				list_lead_activity_list = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-type']");
				list_lead_activity_date_time_list = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-activity-details-alerts-grid']/descendant::tr[@id]/descendant::span[@class='z-alert-datetime']");

				for(int i=0;i<list_lead_activity_list.size();i++) {
					alertVerified = ActionHelper.getText(driver, list_lead_activity_list.get(i)).contains(pAlertToVerify) ;
					if(alertVerified) {

						dateVerified = ActionHelper.getText(driver,list_lead_activity_date_time_list.get(i)).contains(getTodaysDate().replace("2020", "20"));
					}
					if(alertVerified && dateVerified) {
						isVerified = true;
						break;
						
						
					}
				}
				break;
			default:
				break;
			}
		}
		return isVerified;
	}
	private boolean verifyPropFromNotes(String pPropToVerify, String pValue) {
		boolean isVerified = false;
		List<WebElement> list_of_notes = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-lead-notes']/descendant::td[@headers='yui-dt0-th-note ']/div[@class='yui-dt-liner']");
		for(WebElement element_notes: list_of_notes) {
			String lNotes[] = ActionHelper.getText(driver, element_notes).split("\n");
			for (String lNote: lNotes) {
				if(lNote.contains(pPropToVerify)) {
					if(lNote.split(":")[1].trim().equalsIgnoreCase(pValue)) {
						isVerified = true;
						break;
					}
				}
			}
			if(isVerified) {
				break;
			}
		}
		return isVerified;
	}

	public boolean verifyMyMessages(String pEmailToVerify) {
		boolean isEmailReceived = false;
		if(ActionHelper.Click(driver, myMessages_tab_button)) {
			isEmailReceived = checkStatusAfterReg(pEmailToVerify, myMessages_emails_xpath);
		}
		return isEmailReceived;
	}

	public boolean verifyMyMessagesEmails(String pEmailToVerify) {
		boolean isEmailReceived = false;
		if(ActionHelper.Click(driver, myMessages_tab_button)) {
			isEmailReceived = checkStatusAfterSendingEmail(pEmailToVerify);
		}
		return isEmailReceived;
	}
	
	public boolean verifyLeadMessagesEmailsAndDate(String pEmailToVerify) {
		boolean isEmailReceived = false;
		if(ActionHelper.Click(driver, leadMessages_tab_button)) {
			isEmailReceived = checkLeadMessagesStatus(pEmailToVerify);
		}
		return isEmailReceived;
	}

	public boolean checkStatusAfterReg(String pEmailToVerify, String pXpath) {
		int counter = 0;
		boolean lIsEmailVisible = false;
		boolean isVerified = false;
		while(!lIsEmailVisible && counter<15) {
			WebElement dynamicEmail = ActionHelper.getDynamicElement(driver, pXpath, pEmailToVerify);
			isVerified = dynamicEmail!=null?true:false;
			if(isVerified && ActionHelper.isElementVisible(driver, dynamicEmail)) {
				lIsEmailVisible = true;
				break;
			}else {
				ActionHelper.staticWait(30);
				ActionHelper.RefreshPage(driver);
				ActionHelper.ScrollDownByPixels(driver, "400");
				ActionHelper.Click(driver, myMessages_tab_button);
			}
			counter++;
		}
		return isVerified;
	}

	public void waitForMessageAppearance() {
		ActionHelper.staticWait(30);
		ActionHelper.RefreshPage(driver);
		ActionHelper.ScrollDownByPixels(driver, "400");
		ActionHelper.Click(driver, myMessages_tab_button);
	}

	public boolean checkStatusAfterSendingEmail(String pEmailToVerify) {
		String str = "";
		int counter = 0;
		boolean lIsEmailVisible = false;
		while(!lIsEmailVisible && counter<15) {
			if(ActionHelper.isElementVisible(driver, flyer_email)) {
				List<WebElement> subjectList = ActionHelper.getListOfElementByXpath(driver, xpathForTestingSubject);
				ActionHelper.staticWait(2);
				for(WebElement element:subjectList) {
					str = ActionHelper.getText(driver, element);
					if(str.equals(pEmailToVerify)) {
						assertTrue(verifyEmailDateTime(), "unable to verify date");
						lIsEmailVisible = true;
						break;
					}
				}
			} 
			if(!lIsEmailVisible) {
				waitForMessageAppearance();
			}
			counter++;
		}
		return lIsEmailVisible;
	}
	
	public boolean checkLeadMessagesStatus(String pEmailToVerify) {
		String str = "";
		int counter = 0;
		int index = 0;
		boolean lIsEmailAndDateVisible = false;
		while(!lIsEmailAndDateVisible && counter<15) {
			if(ActionHelper.isElementVisible(driver, reply_email)) {
				List<WebElement> subjectList = ActionHelper.getListOfElementByXpath(driver, lead_messages_subject);
				List<WebElement> dateList = ActionHelper.getListOfElementByXpath(driver, lead_messages_date);
				
				ActionHelper.staticWait(2);
				for(WebElement element:subjectList) {
					str = ActionHelper.getText(driver, element);
					if(str.equalsIgnoreCase(pEmailToVerify)) {
						index = subjectList.indexOf(element);
						str = "";
						str = ActionHelper.getText(driver, dateList.get(index));
						if(str.contains(getYesterdaysDate())) {
							lIsEmailAndDateVisible = true;
							break;
					}
				}
			}
			}
			if(!lIsEmailAndDateVisible) {
				ActionHelper.staticWait(5);
				ActionHelper.RefreshPage(driver);
				ActionHelper.ScrollDownByPixels(driver, "400");
				ActionHelper.staticWait(2);
				ActionHelper.Click(driver, leadMessages_tab_button);
				ActionHelper.staticWait(2);
			}
			counter++;
		}
		return lIsEmailAndDateVisible;
	}

	public boolean typeComment(String pComment) {
		return ActionHelper.Type(driver, note_text_area, pComment);
	}

	public boolean clickOnSaveNotesButton() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, save_note_button)) {
			if(ActionHelper.waitForElementToBeVisible(driver, ok_button_notes, 20)) {
				isSuccessful = ActionHelper.Click(driver, ok_button_notes);
			}
		}
		ActionHelper.staticWait(5);
		return isSuccessful;
	}
	public boolean verifyNoteAndTime(String pNoteToVerify) {
		boolean isVerified = false;
		boolean isNoteFound = false;
		int counter = 0;
		List<WebElement> list_of_notes = ActionHelper.getListOfElementByXpath(driver, notes_Added_xpath);
		for(int i =0;i<list_of_notes.size();i++) {
			if(ActionHelper.getText(driver, list_of_notes.get(i)).contains(pNoteToVerify)) {
				isNoteFound = true;
			}

			if(isNoteFound) {
				counter = i;
				break;
			}
		}
		if(isNoteFound) {
			List<WebElement> list_of_notes_dates = ActionHelper.getListOfElementByXpath(driver, notes_date_xpath);
			String lTodaysDate = getCurrentPSTDate();
			String lNotesDate = ActionHelper.getText(driver,list_of_notes_dates.get(counter));
			isVerified = lNotesDate.contains(lTodaysDate);
		}

		return isVerified;
	}
	public boolean clickOnSearchTabButton() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver,lead_searches)) {
			isSuccess = ActionHelper.waitForElementToBeVisible(driver, search_block, 10);
		}
		return isSuccess;
	}
	private String getCurrentPSTDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		String formattedDate = dateFormat.format(new Date(System.currentTimeMillis())).toString().toLowerCase();
		return formattedDate;

	}
	public boolean verifyNavButtonIsDisabled(String pButtonToVerify) {
		boolean isButtonDisabled = false;
		List<WebElement> list_of_disabled_buttons = ActionHelper.getListOfElementByXpath(driver, list_of_disabled_nav_bts);
		for(WebElement element: list_of_disabled_buttons) {
			if(ActionHelper.getText(driver, element).equalsIgnoreCase(pButtonToVerify)) {
				isButtonDisabled = true;
				break;
			}
		}
		return isButtonDisabled;
	}
	public boolean clickOnMyMessagesTab() {
		return ActionHelper.Click(driver, myMessages_tab_button);
	}
	public boolean clickOnLeadMessagesTab() {
		ActionHelper.waitForElementToBeClickAble(driver, leadMessages_tab_button);
		return ActionHelper.Click(driver, leadMessages_tab_button);
	}
	public boolean isEnrollInCampaignButtonDisabled() {
		return ActionHelper.waitForElementToBeVisible(driver, enrollInCampaign_button, 10);
	}
	public boolean clickOnDateReminder() {
		return ActionHelper.Click(driver, taskReminder_date_input);
	}
	public boolean typeReminderNote(String pReminder) {
		return ActionHelper.ClearAndType(driver, text_area_reminder, pReminder);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_reminder_button);
	}
	public boolean clickOnDateDoneButton() {
		boolean isTimeSelected = false;
		if(ActionHelper.Click(driver, now_date_button)) {
			ActionHelper.staticWait(2);
			isTimeSelected = ActionHelper.Click(driver, done_date_button);
		}
		return isTimeSelected;
	}

	public String getLeadProspectSelectedOption() {
		return ActionHelper.getSelectedOption(driver, lead_prospect_dropdown, "");
	}
	public boolean verifyLeadAssignedToAgent(String pAgentName) {
		if(ActionHelper.getText(driver, lead_assigned_to_agent).contains(pAgentName)) {
			return true;
		}else {
			return false;
		}
	}
	public boolean clickAndAssignAgentToLead(String pAgentName) {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, lead_reassign_button, 10)) {
			isClicked = ActionHelper.Click(driver, lead_reassign_button);
			ActionHelper.staticWait(2);
			if(isClicked && ActionHelper.selectDropDownOption(driver, admin_list_dropdown, "", pAgentName)) {
				isClicked = ActionHelper.Click(driver, save_lead_Assignment_button);			}
		}
		return isClicked;
	}
	public boolean isEmailBounced() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, bounced_exclamationmark, 30, 30);
	}
	public boolean isBouncedEmailErrorVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, bounced_email_error, 15);
	}
	public boolean isBouncedEmailAttentionErrorVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, bounced_email_attention_error, 15);
	}
	public boolean verifySubscriptionUnsubscriptionStatus(String pSubscriptionToVerify, String pSubscriptionStatusToVerify) {
		boolean status = false;
		List<WebElement> list_of_subscriptions = ActionHelper.getListOfElementByXpath(driver, email_preferences_label);
		List<WebElement> list_of_subscriptions_status = ActionHelper.getListOfElementByXpath(driver, email_preferences_value);;

		for(int i =0;i<list_of_subscriptions.size();i++) {
			if(ActionHelper.getText(driver, list_of_subscriptions.get(i)).contains(pSubscriptionToVerify)){
				status = ActionHelper.getText(driver,list_of_subscriptions_status.get(i)).equalsIgnoreCase(pSubscriptionStatusToVerify)?true:false;
				break;
			}
		}
		return status;
	}
	public boolean verifyScheduledEmail(String pEmailToVerify) {
		boolean isEmailReceived = false;
		if(ActionHelper.Click(driver, myMessages_tab_button)) {
			ActionHelper.staticWait(2);
			isEmailReceived = checkScheduledEmail(pEmailToVerify);
		}
		return isEmailReceived;
	}
	public boolean checkScheduledEmail(String pEmailToVerify) {
		String str = "";
		int counter = 0;
		boolean lIsEmailVisible = false;
		while(!lIsEmailVisible && counter<15) {
			//if(ActionHelper.isElementVisible(driver, scheduledEmails)) {
				List<WebElement> subjectList = ActionHelper.getListOfElementByXpath(driver, scheduled_messages_list);
				ActionHelper.staticWait(2);
				for(WebElement element:subjectList) {
					str = ActionHelper.getText(driver, element);
					if(str.equals(pEmailToVerify)) {
//						assertTrue(verifyEmailDateTime(), "unable to verify date");
						lIsEmailVisible = true;
						break;
					}
		//		}
			} 
			if(!lIsEmailVisible) {
				waitForMessageAppearance();
			}
			counter++;
		}
		return lIsEmailVisible;
	}
	public boolean enrollUnenrollInCampaign(String pCampaignName, boolean pEnrollInCampaign) {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, myMessages_tab_button)) {
			if(pEnrollInCampaign) {
				ActionHelper.staticWait(2);
				clickOnEnrollInCampaignButton();
				assertTrue(getSelectCampaign().isSelectCampaignAlert(), "Campaign alert is not displayed");
				assertTrue(getSelectCampaign().selectCampaignFromDropdown(pCampaignName), "Unable to select campaign from drop down");
				assertTrue(getSelectCampaign().clickOnEnrollButton(), "Unable to click on enroll button..");
				isSuccess = getSelectCampaign().clickOnOkButton();
			}else {
				ActionHelper.staticWait(2);
				clickOnEnrollInCampaignButton();
				ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
				assertTrue(successAlert.isUnenrollCampaignAlert(), "Unable to click on unenroll button..");
				isSuccess = successAlert.clickOnUnEnrollButton();
			}
		}
		return isSuccess;
	}
	public boolean isCampaignNameVisibleInMyMessages(String pCampaignName) {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, myMessages_tab_button)) {
			ActionHelper.staticWait(5);
			isSuccess = ActionHelper.getDynamicElementAfterRegularIntervals(driver, enrollInCampaign, pCampaignName, 2);
		}
		return isSuccess;
	}
	public boolean clickOnEnrollInCampaignButton() {
		return ActionHelper.Click(driver, ENROLL_IN_CAMPAIGN_BUTTON);
	}
	public boolean isSendEmailButtonDisabled() {
		return ActionHelper.isElementVisible(driver, sendEmail_disabled_button);
	}
	public boolean isSendTextButtonDisabled() {
		boolean isDisabled = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, sendTextMessage_disabled_button);
		for(WebElement element: list) {
			if(ActionHelper.getText(driver, element).equalsIgnoreCase("Send Text Message")) {
				isDisabled = ActionHelper.isElementVisible(driver, element);
			}
		}
		return isDisabled;
	}
	public boolean isEnrollInCampaignTabButtonDisabled() {
		return ActionHelper.isElementVisible(driver, campaign_disabled_button);
	}
	
	public boolean verifyHomeEvaluationAlert(String pText) {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(45);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "300");
			isVerified = verifyAlerts("Homeowner Asked for a CMA", pText);
			counter++;
		}
		return isVerified;
	}
	public boolean verifyHighActivityAlert() {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(45);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "400");
			isVerified = verifyAlerts("Lots of Browsing", "");
			counter++;
		}
		return isVerified;
	}
	public boolean verifyHighReturnAlert() {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(45);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "500");
			isVerified = verifyAlerts("High Return", "");
			counter++;
		}
		return isVerified;
	}
	public boolean isReturnHotBehaviorVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, return_label, 30);
	}
	public boolean isBrowsingHotBehaviorVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, browsing_label, 30);
	}
	public boolean verifyAgentInquiryAlert() {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(20);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "600");
			isVerified = verifyAlerts("Agent Inquiry", "");
			counter++;
		}
		return isVerified;
	}
	public boolean verifyActivityAlert(String pAlertName) {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(20);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "600");
			isVerified = verifyAlerts(pAlertName, "");
			counter++;
		}
		return isVerified;
	}
	public boolean verifyFavoritesAlert(String pCity, String pAddress, String pPrice) {
		int counter = 0;
		boolean isVerified = false;
		while(!isVerified && counter<15) {
			ActionHelper.staticWait(20);
			ActionHelper.RefreshPage(driver);
			ActionHelper.ScrollDownByPixels(driver, "600");
			isVerified = verifyFavoriteProperty(pCity, pAddress, pPrice);
			counter++;
		}
		return isVerified;
	}
	private boolean verifyFavoriteProperty(String pCity, String pAddress, String pPrice) {
		boolean isVerified = false;
		
		if(ActionHelper.Click(driver, favorites_tab_button)) {
			ActionHelper.staticWait(5);
			String lCity_favorite = ActionHelper.getText(driver, driver.findElement(By.xpath("//div[@id='z-activity-details-favorites-grid']/descendant::tr[@id]/descendant::td[contains(@headers,'city')]/div")));
			String lAddress_favorite = ActionHelper.getText(driver, driver.findElement(By.xpath("//div[@id='z-activity-details-favorites-grid']/descendant::tr[@id]/descendant::td[contains(@headers,'address')]/div")));
			String lPrice_favorite = ActionHelper.getText(driver, driver.findElement(By.xpath("//div[@id='z-activity-details-favorites-grid']/descendant::tr[@id]/descendant::td[contains(@headers,'price')]/div"))).replace(",", "").replace("$", "");
			
			if(lCity_favorite.equalsIgnoreCase(pCity) && lAddress_favorite.equalsIgnoreCase(pAddress) && lPrice_favorite.equalsIgnoreCase(pPrice)) {
				isVerified = true;
			}
		}
		return isVerified;
	}
}
