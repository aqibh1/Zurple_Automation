package com.torchx;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * 
 * @author ahabib
 *
 */
public class TXBORecentVisitorsPanel extends Page {

	@FindBy(className="customized")
	WebElement panel_header;
	
	@FindBy(xpath="//div[@class='col-md-6 ']/descendant::i[@class='info-icon-white']")
	WebElement tooltip_text;
	
	@FindBy(className="full_name")
	WebElement lead_info;
	
	@FindBy(xpath="//div[@class='dataTables_scrollHead']/descendant::th[@class='sorting_disabled']")
	WebElement actions_header;
	
	@FindBy(className="fa-envelope")
	WebElement email_icon;
	
	@FindBy(className="send-email-lead")
	WebElement email_icon_button;
	
	@FindBy(className="fa-sms")
	WebElement sms_icon;

	@FindBy(className="fa-bell")
	WebElement bell_icon;

	@FindBy(className="fa-sticky-note")
	WebElement note_icon;
	
	@FindBy(xpath="//span[@class='glyphicon email-validation-glyph glyphicon-exclamation-sign rejected']")
	WebElement verified_email_mark;
	
	@FindBy(xpath="//div[@class='full_name']/a")
	WebElement lead_href;
	
	public List<String> leadInfoList = new ArrayList<String>();
	
	TXBORecentVisitorsPanel(){
	}

	public TXBORecentVisitorsPanel(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPanelHeaderText() {
		return ActionHelper.getText(driver, panel_header);
	}
	
	public String getToolTipText() {
		return ActionHelper.getAttribute(tooltip_text,"title");
	}
	
	public List<String> getLeadInfo() {
		String leadInfo = ActionHelper.getText(driver, lead_info);
		String leadName = leadInfo.split("\\(")[0]; //0210202121639 Autouser
		String leadPhone = leadInfo.split("\\s{4}")[1];
		leadPhone = leadPhone.split("\\[")[0]; //202) 555-0149
		String leadVisitDate = leadInfo.split("\\[")[1]; //02/10/21 at 2:00am]
		leadInfoList.add(leadName);
		leadInfoList.add(leadPhone);
		leadInfoList.add(leadVisitDate);
		return leadInfoList;
	}
	
	public String getActionHeader() {
		return ActionHelper.getText(driver, actions_header);
	}
	
	public boolean emailActionButton() {
		return ActionHelper.waitForElementToBeVisible(driver, email_icon, 30);
	}
	
	public boolean smsActionButton() {
		return ActionHelper.waitForElementToBeVisible(driver, sms_icon, 30);
	}

	public boolean reminderActionButton() {
		return ActionHelper.waitForElementToBeVisible(driver, bell_icon, 30);
	}

	public boolean noteActionButton() {
		return ActionHelper.waitForElementToBeVisible(driver, note_icon, 30);
	}
	
	public String smsActionDisabled() {
		ActionHelper.waitForElementToBeVisible(driver, sms_icon, 30);
		return ActionHelper.getAttribute(sms_icon,"disabled");
	}
	
	public String emailActionDisabled() {
		ActionHelper.waitForElementToBeVisible(driver, email_icon_button, 30);
		return ActionHelper.getAttribute(email_icon_button,"disabled");
	}
	
	public boolean isEmailVerified() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, verified_email_mark, 20, 10);
	}
	
	public String clickFirstLead() {
		ActionHelper.waitForElementToBeVisible(driver, lead_href, 30);
		return ActionHelper.getAttribute(lead_href,"href");
	}
	
}
