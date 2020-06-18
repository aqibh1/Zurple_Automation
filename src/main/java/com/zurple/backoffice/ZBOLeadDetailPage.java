package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
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
	
	String list_of_disabled_nav_bts = "//ul[@id='lead_detail_navbar']/descendant::a[contains(@class,'lead-btn-disabled') and @title='This lead is currently unsubscribed from direct agent communications (Mass Email)']";
	
	@FindBy(xpath="//div[@id='z-activity-details-sent-tab']/descendant::input[contains(@class,'lead-btn-disabled') and @title='This lead is currently unsubscribed from direct agent communications (Mass Email)']")
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
	
	@FindBy(xpath="//div[@id='z-activity-details-sent-grid']/descendant::td[@headers='yui-dt5-th-messageDateTime ']/div")
	WebElement xpathForTestingDate;
	
	@FindBy(xpath="//button[text()='Done']")
	WebElement done_date_button;
	
	@FindBy(xpath="//button[text()='Now']")
	WebElement now_date_button;
	
	
		
	private ZBOLeadDetailsSearchBlock leadDetailSearchBlock;
	
	public ZBOLeadDetailPage() {
		
	}
	
	public ZBOLeadDetailPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		setLeadDetailSearchBlock();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOLeadDetailsSearchBlock getLeadDetailSearchBlock() {
		return leadDetailSearchBlock;
	}

	public void setLeadDetailSearchBlock() {
		this.leadDetailSearchBlock = new ZBOLeadDetailsSearchBlock(driver);
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
			if(ActionHelper.getText(driver, list_of_notes.get(i)).equalsIgnoreCase(pNoteToVerify)) {
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
}
