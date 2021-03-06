package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class ZBODashboardPage extends Page{
	@FindBy(className="z-lead-phone")
	WebElement phoneNumber;
	
	@FindBy(xpath="//li[@class='menu-main-item dropdown' and @role='presentation']/a[@role='button'  and not(@id)]")
	WebElement siteOwner_dropdown;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[text()='Logout']")
	WebElement logout_button;
	
	@FindBy(id="username")
	WebElement username_input;
	
	//Key Stats
	@FindBy(id="key-stats-header")
	WebElement key_stats;
	@FindBy(xpath="//div[@id='new-leads-key-stat']/descendant::div[@class='row1 stat_title']")
	WebElement new_leads;
	@FindBy(xpath="//div[@id='new-leads-key-stat']/descendant::div[@class='row2']")
	WebElement last_30_days;
	@FindBy(id="key-stats-new-leads")
	WebElement leads_key_stats;
	
	//Leads Managed
	@FindBy(xpath="//div[@id='all-leads-key-stat']/descendant::div[@class='row1 stat_title']")
	WebElement leads_managed;
	@FindBy(id="key-stats-leads-managed")
	WebElement leads_key_leads_managed;
	
	//Messages sent
	@FindBy(xpath="//div[@id='messages-sent-key-stat']/descendant::div[@class='row1 stat_title']")
	WebElement messages_sent;
	@FindBy(xpath="//div[@id='messages-sent-key-stat']/descendant::div[@class='row2']")
	WebElement _messages_last_30_days;
	@FindBy(id="key-stats-messages-sent")
	WebElement messages_sent_stats;
	
	//Messages Open rate
	@FindBy(xpath="//div[@id='open-rate-key-stat']/descendant::div[@class='row1 stat_title']")
	WebElement messages_open_rate;
	@FindBy(xpath="//div[@id='open-rate-key-stat']/descendant::div[@class='row2']")
	WebElement _messages_open_last_30_days;
	@FindBy(id="key-stats-open-rate")
	WebElement messages_open_stats;
	
	//All Visits
	@FindBy(id="visitsToday")
	WebElement visitsToday;
	@FindBy(id="allvisitschart")
	WebElement visblleChartToday;
	@FindBy(id="visitsPastWeek")
	WebElement visitsPastWeek;
	@FindBy(id="visitsPastMonth")
	WebElement visitsPastMonth;
	@FindBy(id="visitsPastYear")
	WebElement visitsPastYear;
	
	//Zurple Updates
	@FindBy(xpath="//div[@class='panel panel-default']/descendant::h2[text()='Latest Zurple Updates']")
	WebElement zurple_updates;
	@FindBy(xpath="//div[@class='panel-body']")
	WebElement zurple_update_text;
	
	//New Leads
	@FindBy(xpath="//h2[@class='panel-title' and text()='New Leads']")
	WebElement newLeads_heading;
	@FindBy(xpath="//span[@class='z-panel-info-data']")
	WebElement lead_data;
	String leads_list = "//span[@class='z-lead-name']";
	
	//Hot Behaviors
	@FindBy(xpath="//h2[@class='panel-title' and text()='Hot Behaviors']")
	WebElement hotBehaviors_heading;
	@FindBy(xpath="//span[text()='View Hot Leads']")
	WebElement viewHotLeads_button;
	
	//New Leads
	String lead_new = "//div[@id='z-new-leads-grid']/descendant::a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	@FindBy(id="loading-key-stats-modal")
	WebElement loading_key_stats_modal;
	
	@FindBy(id="key-stats-auto-leads")
	WebElement zurple_autoleads_stats_count;
	
	@FindBy(id="key-stats-lead-replies")
	WebElement lead_replies_stats_count;
	
	@FindBy(id="key-stats-alerttriggered")
	WebElement alert_triggered_stats_count;
	
	@FindBy(id="key-stats-website-visits")
	WebElement website_visit_stats_count;
	
	
	public ZBODashboardPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPhoneNumberText(String pPhoneNumber) {
		String pNumText = ActionHelper.getText(driver, phoneNumber);
		// pNumText = pNumText.replace(' ', '-');
		AutomationLogger.info("Fetching phone number");
		return pNumText.equalsIgnoreCase(pPhoneNumber);
	}
	
	public boolean verifyPhoneAlert() {
		boolean isVerified = false;
		ActionHelper.switchToOriginalWindow(driver);
		ActionHelper.resizeWindow(driver, 444, 562);
		ActionHelper.RefreshPage(driver);
		ActionHelper.switchToOriginalWindow(driver);
		ActionHelper.Click(driver, phoneNumber);

		isVerified = ActionHelper.sendSpecialKeys(driver,Keys.ESCAPE);
		return isVerified;
	}
	public boolean doLogout() {
		boolean isLogoutSuccessful = false;
		if(ActionHelper.MouseHoverOnElement(driver, siteOwner_dropdown) && ActionHelper.Click(driver, logout_button)) {
			isLogoutSuccessful = ActionHelper.waitForElementToBeVisible(driver, username_input, 60);
		}
		return isLogoutSuccessful;
	}
	public boolean isKeyStatsVisible() {
		boolean isVisible = false;
		if(ActionHelper.isElementVisible(driver, key_stats) && ActionHelper.isElementVisible(driver, new_leads)
				 && ActionHelper.isElementVisible(driver, leads_key_stats)) {
			isVisible = true;
		}
		return isVisible;
	}
	public boolean isLeadsManagedStatsVisible() {
		boolean isVisible = false;
		if(ActionHelper.isElementVisible(driver, leads_managed) && ActionHelper.isElementVisible(driver, leads_key_leads_managed)) {
			isVisible = true;
		}
		return isVisible;
	}
	public boolean isMessagesSentVisible() {
		boolean isVisible = false;
		if(ActionHelper.isElementVisible(driver, messages_sent) && ActionHelper.isElementVisible(driver, messages_sent_stats)) {
			isVisible = true;
		}
		return isVisible;
	}
	public boolean isMessagesOpenRateVisible() {
		boolean isVisible = false;
		if(ActionHelper.isElementVisible(driver, messages_open_rate) && ActionHelper.isElementVisible(driver, messages_open_stats)) {
			isVisible = true;
		}
		return isVisible;
	}
	public boolean allVisitesTodayWorking() {
		boolean isWorking = false;
		if(ActionHelper.Click(driver, visitsToday)) {
			isWorking = ActionHelper.isElementVisible(driver, visblleChartToday);
		}
		return isWorking;
	}
	public boolean allVisitesPastWeekWorking() {
		boolean isWorking = false;
		if(ActionHelper.Click(driver, visitsPastWeek)) {
			isWorking = ActionHelper.isElementVisible(driver, visblleChartToday);
		}
		return isWorking;
	}
	public boolean allVisitesPastMontWorking() {
		boolean isWorking = false;
		if(ActionHelper.Click(driver, visitsPastMonth)) {
			isWorking = ActionHelper.isElementVisible(driver, visblleChartToday);
		}
		return isWorking;
	}
	public boolean allVisitesPastYearWorking() {
		boolean isWorking = false;
		if(ActionHelper.Click(driver, visitsPastYear)) {
			isWorking = ActionHelper.isElementVisible(driver, visblleChartToday);
		}
		return isWorking;
	}
	public boolean isZurpleUpdateVisible() {
		return ActionHelper.isElementVisible(driver, zurple_updates);
	}
	public boolean isZurpleUpdatesTextVisible() {
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, "//div[@class='panel-body']");
		return ActionHelper.isElementVisible(driver, list.get(1));
	}
	public boolean isNewLeadsHeadingAndStatsDisplayed() {
		boolean isDisplayed = false;
		if(ActionHelper.isElementVisible(driver, newLeads_heading)) {
			isDisplayed = ActionHelper.isElementVisible(driver, lead_data);
		}
		return isDisplayed;
	}
	public boolean isLeadNamesDisplayed() {
		return ActionHelper.getListOfElementByXpath(driver, leads_list).size()>0;
	}
	public boolean isHotBehaviorsDisplayed() {
		return ActionHelper.isElementVisible(driver, hotBehaviors_heading);
	}
	public boolean clickOnViewLeadsButton() {
		boolean isButtonWorking = false;
		if(ActionHelper.Click(driver, viewHotLeads_button)) {
			isButtonWorking = driver.getCurrentUrl().contains("/leads/index/ext/hotlead");
		}
		return isButtonWorking;
	}
	public boolean isLeadDisplayed(String pLeadName) {
		return ActionHelper.getDynamicElementAfterRegularIntervals(driver, lead_new, pLeadName, 5);
	}
	public boolean clickOnLeadName(String pLeadName) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, lead_new, pLeadName));
	}
	public String getNewLeadsCountFromKeyStats() {
		return ActionHelper.getText(driver, leads_key_stats);
	}
	public String getLeadsManagedCountFromKeyStats() {
		return ActionHelper.getText(driver, leads_key_leads_managed).replace(",","");
	}
	public String getMessagesSentCountFromKeyStats() {
		return ActionHelper.getText(driver, messages_sent_stats).replace(",","");
	}
	public String getMessagesOpenRateCountFromKeyStats() {
		return ActionHelper.getText(driver, messages_open_stats).replace("%","");
	}
	public boolean waitForLoadingKeyStatsToDisappear() {
		return ActionHelper.waitForElementToBeDisappeared(driver, loading_key_stats_modal, 60);
	}
	public String getZurpleAutoLeadsStatsCount() {
		return ActionHelper.getText(driver, zurple_autoleads_stats_count);
	}
	public String getZurpleLeadRepliesStatsCount() {
		return ActionHelper.getText(driver, lead_replies_stats_count).replace(",","");
	}
	public String getAlertTriggeredFromKeyStats() {
		return ActionHelper.getText(driver, alert_triggered_stats_count).replace(",","");
	}
	public String getWebsiteVisitFromKeyStats() {
		return ActionHelper.getText(driver, website_visit_stats_count).replace(",","");
	}
}
