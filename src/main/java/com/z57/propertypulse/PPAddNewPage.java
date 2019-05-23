/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class PPAddNewPage extends Page{
	
	@FindBy(xpath="//div[@class='wrap']/descendant::h1[text()='Add New Page']")
	WebElement addNewPage_heading;
	
	@FindBy(xpath="//label[@id='title-prompt-text']/following::input[@name='post_title']")
	WebElement pageTitle_input;
	
	@FindBy(xpath="//input[@id='publish' and @class='button button-primary button-large']")
	WebElement publish_button;
	
	@FindBy(id="content_ifr")
	WebElement iFrame;
	
	@FindBy(xpath="//body[@id='tinymce']/p")
	WebElement paragraph_iframe;
	
	@FindBy(xpath="//span[@id='view-post-btn']/a")
	WebElement viewPage_button;
	
	@FindBy(xpath="//div[@id='delete-action']/a")
	WebElement moveToTrash_button;
	
	@FindBy(xpath="//div[@class='wrap']/descendant::a[text()='Add New']")
	WebElement addNew_button;
	
	@FindBy(xpath="//div[@class='wrap']/descendant::h1[text()='Edit Page ']")
	WebElement editPage_heading;
	
	@FindBy(xpath="//select[@name='z57_admin_lead_capture_settings_enabled']")
	WebElement leadCaptureEnabled;
	
	@FindBy(xpath="//select[@name='z57_admin_lead_capture_settings_strength']")
	WebElement leadCaptureStrength;
	
	@FindBy(xpath="//select[@name='z57_admin_lead_capture_settings_trigger']")
	WebElement leadCaptureTriggerPrompt;
	
	@FindBy(id="layout_value")
	WebElement pageLayout;
	
	@FindBy(id="sidebar_value")
	WebElement pageSidebarValue;
	
	@FindBy(xpath="//li[@id='contact_me_widget-4']/h3[text()='Contact Me']")
	WebElement contactMeWidget;
	
	@FindBy(xpath="//li[@id='contact_me_widget-2']/h3[text()='Contact Me']")
	WebElement contactMeWidgetDefault;
	
	@FindBy(xpath="//h2[text()='Fast Start Appointment']")
	WebElement fastStartHeading;
	
	@FindBy(xpath="//li[@id='idx_search_widget-3']/h3[text()='Home Search']")
	WebElement homeSearchWidget;
	
	public PPAddNewPage() {
		
	}
	public PPAddNewPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isAddNewPage() {
		return ActionHelper.waitForElementToBeVisible(driver, addNewPage_heading, 30);
	}
	
	public boolean typePageTitle(String pPageTitle) {
		return ActionHelper.Type(driver, pageTitle_input, pPageTitle);
	}
	
	public boolean clickOnPublishButton() {
		boolean isClick = false;
		try {
			Thread.sleep(3000);
		}catch(Exception ex) {

		}
		if(ActionHelper.waitForElementToBeVisible(driver, publish_button,15)) {
			isClick = ActionHelper.Click(driver, publish_button);
		}
		return isClick;
	}
	
	public boolean typeInBody(String pBody) {
		boolean result = true;
		try {
			String currentWindowsHandle =  driver.getWindowHandle();
			driver.switchTo().frame(iFrame);
			driver.findElement(By.id("tinymce")).clear();
			driver.findElement(By.id("tinymce")).sendKeys(pBody);
			driver.switchTo().window(currentWindowsHandle);
			focusOnPage();
		}catch(Exception ex) {
			AutomationLogger.info("Unable to type in HTML body");
			result = false;
		}
		return result;
	}
	
	public boolean isPublishSuccessful() {
		return ActionHelper.waitForElementToBeVisible(driver, addNew_button, 30);
	}
	public String getPageUrl() {
		return ActionHelper.getAttribute(viewPage_button, "href");
	}
	public boolean clickOnMoveToTrashButton() {
		return ActionHelper.Click(driver, moveToTrash_button);
	}
	public boolean isEditPage() {
		return ActionHelper.waitForElementToBeVisible(driver, editPage_heading, 30);
	}
	public boolean clickAndSelectLeadCaptureEnabled(String pOption) {
		return ActionHelper.selectDropDownOption(driver, leadCaptureEnabled,"", pOption);
	}
	public boolean clickAndSelectCaptureLeadStrength(String pStrength) {
		return ActionHelper.selectDropDownOption(driver, leadCaptureStrength,"", pStrength);
	}
	public boolean clickAndSelectCaptureLeadTrigger(String pTrigger) {
		return ActionHelper.selectDropDownOption(driver, leadCaptureTriggerPrompt,"", pTrigger);
	}
	public boolean clickAndSelectPageLayout(String pLayout) {
		return ActionHelper.selectDropDownOption(driver, pageLayout,"", pLayout);
	}
	public boolean clickAndSelectPageLayoutSidebar(String pSidebar) {
		return ActionHelper.selectDropDownOption(driver, pageSidebarValue,"", pSidebar);
	}
	public boolean isDefaultSidebarVisible() {
		return ActionHelper.isElementVisible(driver, contactMeWidgetDefault);
	}
	public boolean isContactMeWidgetVisible() {
		return ActionHelper.isElementVisible(driver, contactMeWidget);
	}
	public boolean isHomepageWidgetVisible() {
		return ActionHelper.isElementVisible(driver, fastStartHeading);
	}
	public boolean isHomesearchWidgetVisible() {
		return ActionHelper.isElementVisible(driver, homeSearchWidget);
	}
}
