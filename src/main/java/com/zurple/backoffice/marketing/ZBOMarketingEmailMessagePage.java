/**
 * 
 */
package com.zurple.backoffice.marketing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.zurple.backoffice.ZBOAttachFileForm;
import resources.forms.zurple.backoffice.ZBOInsertImageForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOMarketingEmailMessagePage extends Page{

	@FindBy(xpath="//h1[text()='Email']")
	WebElement email_heading;
	
	@FindBy(id="massemail_type-lead")
	WebElement individual_lead_radio;
	@FindBy(id="massemail_type-prospectnew")
	WebElement prospectnew_lead_radio;
	@FindBy(id="massemail_type-prospectactive")
	WebElement prospectactive_lead_radio;
	@FindBy(id="massemail_type-allunresponsiveleads")
	WebElement prospectunresponsive_lead_radio;
	@FindBy(id="massemail_type-allresponsiveleads")
	WebElement allresponsive_lead_radio;
	@FindBy(id="massemail_type-allclients")
	WebElement allclient_lead_radio;
	@FindBy(id="massemail_type-all")
	WebElement all_lead_radio;
	@FindBy(id="massemail_type-filter")
	WebElement filter_lead_radio;
	@FindBy(id="massemail_type-transactiongoals")
	WebElement transactiongoals_lead_radio;
	@FindBy(id="massemail_type-import")
	WebElement import_lead_radio;
	
	@FindBy(id="send_email_listing_flyer")
	WebElement emailListingFlyer_button;
	
	@FindBy(id="toemail")
	WebElement toEmail_input;
	
	@FindBy(id="mls_id")
	WebElement mlsId_input;
	
	@FindBy(id="find_listing_button")
	WebElement findListing_button;
	
	@FindBy(id="subject")
	WebElement subject_input;
	
	@FindBy(xpath="//h1[text()='New Home on the Market']")
	WebElement newHomeOnMarket_heading;
	
	@FindBy(id="preview_button")
	WebElement preview_button;
	
	@FindBy(xpath="//div[@id='preview']/descendant::h1[text()='New Home on the Market']")
	WebElement newHomeOnMarket_preview_heading;
	
	@FindBy(xpath="//div[@aria-describedby='preview_box']/descendant::button[@title='close' and @aria-disabled='false']")
	WebElement closePreview_button;
	
	@FindBy(id="send_button")
	WebElement sendNow_button;
	
	@FindBy(xpath="//div[@class='alert alert-success ']/strong[text()='Your email will be sent within the next 5 minutes.']")
	WebElement success_message;
//	String success_message = "//div[@class='alert alert-success ']/strong[text()='Your email will be sent within the next 5 minutes.']";
	
	@FindBy(id="send_standard_email")
	WebElement send_standard_email_button;
	
	@FindBy(xpath="//label[@for='attachment']")
	WebElement attachFile_button;
	
	@FindBy(id="attachment-remove")
	WebElement attachment_remove_button;
	
	@FindBy(id="attachment_label")
	WebElement attachment_label_preview;
	
	private ZBOInsertImageForm zboInsertImageForm;
	private ZBOAttachFileForm zbAttachFileForm;
	
	public ZBOMarketingEmailMessagePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setInsertImageForm();
		setAttachFileForm();
		PageFactory.initElements(driver, this);
	}
	private void setAttachFileForm() {
		zbAttachFileForm = new ZBOAttachFileForm(driver);
	}
	public ZBOAttachFileForm getAttachFileForm() {
		return zbAttachFileForm;
	}
	private void setInsertImageForm() {
		zboInsertImageForm = new ZBOInsertImageForm(driver);
	}
	public ZBOInsertImageForm getInsertImageForm() {
		return zboInsertImageForm;
	}
	public boolean isMarketingEmailPage() {
		return ActionHelper.waitForElementToBeVisible(driver, email_heading, 30);
	}
	public boolean selectRecipients(String pRecipient) {
		boolean isSelected = false;
		switch(pRecipient) {

		case "Individual Lead with any Status other than Inactive":
			isSelected = ActionHelper.Click(driver, individual_lead_radio);
			break;
		case "All Leads with Status of Prospect - New":
			isSelected = ActionHelper.Click(driver, prospectnew_lead_radio);
			break;
		case "All Leads with Status of Prospect - Active":
			isSelected = ActionHelper.Click(driver, prospectactive_lead_radio);
			break;
		case "All Leads with Status of Prospect - Unresponsive":
			isSelected = ActionHelper.Click(driver, prospectunresponsive_lead_radio);
			break;
		case "All Leads with Status of Prospect - Communicated with Me":
			isSelected = ActionHelper.Click(driver, allresponsive_lead_radio);
			break;
		case "All Leads with Status of Client - Active Opportunity, Client - Sold":
			isSelected = ActionHelper.Click(driver, allclient_lead_radio);
			break;
		case "All Leads with any Status other than Inactive":
			isSelected = ActionHelper.Click(driver, all_lead_radio);
			break;
		case "All Leads Matching a Filter...":
			isSelected = ActionHelper.Click(driver, filter_lead_radio);
			break;
		case "Transaction Goals":
			isSelected = ActionHelper.Click(driver, transactiongoals_lead_radio);
			break;
		case "Imported Leads from a Lead Import Batch...":
			isSelected = ActionHelper.Click(driver, import_lead_radio);
			break;
		}

		return isSelected;

	}	
	public boolean clickOnEmailListingFlyer() {
		return ActionHelper.Click(driver, emailListingFlyer_button);
	}
	
	public boolean typeToEmail(String pEmail) {
		return ActionHelper.Type(driver, toEmail_input, pEmail);
	}
	
	public boolean typeMLSId(String pMlsId) {
		return ActionHelper.Type(driver,mlsId_input, pMlsId);
	}
	public boolean clickOnFindListingButton() {
		boolean isListingFound = false;
		if(ActionHelper.Click(driver, findListing_button)) {
			isListingFound = ActionHelper.waitForElementToBeVisible(driver, newHomeOnMarket_heading, 30);
		}
		return isListingFound;
	}
	public boolean typeToSubject(String pSubject) {
		return ActionHelper.ClearAndType(driver, subject_input, pSubject);
	}
	public boolean clickOnPreviewButton() {
		return ActionHelper.Click(driver, preview_button);
	}
	public boolean isPreviewHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, newHomeOnMarket_preview_heading, 30);
	}
	public boolean closePreviewWindow() {
		return ActionHelper.Click(driver, closePreview_button);
	}
	public boolean clickOnSendButton() {
		return ActionHelper.Click(driver, sendNow_button);
	}
	public boolean isSuccessMessage() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver,success_message , 20, 10);
	}
	public boolean clickOnSendStandardEmailButton() {
		return ActionHelper.Click(driver, send_standard_email_button);
	}
	public boolean clickOnAttachFileButton() {
		return ActionHelper.Click(driver, attachFile_button);
	}
	public boolean isAttachmentRemoveButtonVisible() {
		return ActionHelper.isElementVisible(driver, attachment_remove_button);
	}
	public boolean isAttachmentLabelVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, attachment_label_preview, 30);
	}
}
