/**
 * 
 */
package com.zurple.backoffice.marketing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.blocks.zurple.ZBODatePickerBlock;
import resources.forms.zurple.backoffice.ZBOAttachFileForm;
import resources.forms.zurple.backoffice.ZBOInsertImageForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
/**
 * @author darrraqi
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
		
	String preview_lead_message = "//div[@id='z-activity-details-messages-to-admin-grid']/descendant::td[@headers='yui-dt5-th-body ']/div";

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
	
	@FindBy(id="campaign_template")
	WebElement campaign_template_dropdown;
	
	@FindBy(xpath="//div[@id='ui-datepicker-div']/descendant::button[text()='Done']")
	WebElement datePicker_done_button;
	
	@FindBy(id="new-post-schedule")
	WebElement schedule_button;
	
	@FindBy(xpath="//div[@class='alert alert-success ']/strong[text()='Your email is scheduled']")
	WebElement scheduled_message;
		
	@FindBy(xpath="//h1[contains(text(),'Updates for San Diego')]")
	WebElement puns_text;
	
	@FindBy(xpath="//span[@id='scheduled-label']/span[1]")
	WebElement scheduled_label;

	@FindBy(id="massemail_type-lead")
	WebElement individual_recipient;
	
	@FindBy(id="listing_subject")
	WebElement listing_subject;
	
	@FindBy(xpath="//strong[text()='Address']/preceding::input[1]")
	WebElement address_radio_input;
	@FindBy(xpath="//strong[text()='City']/preceding::input[1]")
	WebElement city_radio_input;
	@FindBy(xpath="//strong[text()='Zip']/preceding::input[1]")
	WebElement zip_radio_input;
	@FindBy(xpath="//strong[text()='MLS ID']/preceding::input[1]")
	WebElement mls_radio_input;
	
	@FindBy(id="name")
	WebElement search_input;
	
	@FindBy(id="propertiesGrid_processing")
	WebElement processing;
	
	@FindBy(id="z-properties-grid-filter-button")
	WebElement search_button;
	
	String mls_number_in_search_results = "//span[@data-mls_number='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="nextbtn")
	WebElement next_button;
	
	@FindBy(id="subject_validation")
	WebElement subject_validation;
	
	@FindBy(id="selected_validation")
	WebElement selected_validation;
	
	String add_listing_button_list = "//span[@class='not-added-property']/span";
	String remove_listing_button_list = "//ul[@class='select2-selection__rendered']/li/span";
	String added_label_list = "//span[@class='added-property' and text()='Added']";
	
	@FindBy(id="new-post-schedule")
	WebElement calendar_button;
	
	@FindBy(xpath="//*[@id='scheduled-label']/descendant::span[@class='schedule-label']")
	WebElement schedule_label;
	
	@FindBy(id="remove-schedule")
	WebElement remove_Schedule_button;
	
	@FindBy(xpath="//*[@id='preview_heading']/h3[text()='Preview Email']")
	WebElement preview_heading;
	
	private ZBOInsertImageForm zboInsertImageForm;
	private ZBOAttachFileForm zbAttachFileForm;
	private ZBODatePickerBlock datePicker;
	
	public ZBOMarketingEmailMessagePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setInsertImageForm();
		setAttachFileForm();
		setDatePicker();
		PageFactory.initElements(driver, this);
	}
	public ZBODatePickerBlock getDatePicker() {
		return datePicker;
	}

	private void setDatePicker() {
		this.datePicker = new ZBODatePickerBlock(driver);
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
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, email_heading, 20, 10);
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
		ActionHelper.waitForElementToBeVisible(driver, emailListingFlyer_button, 30);
		return ActionHelper.Click(driver, emailListingFlyer_button);
	}
	
	public boolean typeToEmail(String pEmail) {
		return ActionHelper.Type(driver, toEmail_input, pEmail);
	}
	
	public boolean typeMLSId(String pMlsId) {
		return ActionHelper.Type(driver,mlsId_input, pMlsId);
	}
	
	public boolean clickOnFindListingButton() {
		ActionHelper.waitForElementToBeVisible(driver, findListing_button, 30);
		return ActionHelper.Click(driver, findListing_button);
	}
	public boolean waitForFlyerHeadingToAppear() {
		return ActionHelper.waitForElementToBeVisible(driver, newHomeOnMarket_heading, 30);
	}
	public boolean typeToSubject(String pSubject) {
		return ActionHelper.ClearAndType(driver, subject_input, pSubject);
	}
	public boolean clickOnPreviewButton() {
		return ActionHelper.Click(driver, preview_button);
	}
	public boolean clickOnLeadMessagesPreview() {
		return ActionHelper.ClickByXpathIndex(driver, preview_lead_message,0);
	}
	public String getPreviewText() {
		return ActionHelper.getText(driver, puns_text);
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
	public boolean isTemplateExists(String pTemplateName) {
		return ActionHelper.selectDropDownOption(driver,campaign_template_dropdown , "", pTemplateName);
	}

	public boolean selectSchedule() {
		boolean isScheduleSelected = false;
		boolean isClicked = ActionHelper.Click(driver, schedule_button);
		ActionHelper.staticWait(5);
		WebElement minutes_slider = ActionHelper.getDynamicElement(driver, "//div[@id='ui-datepicker-div']/descendant::div[contains(@class,'ui_tpicker_minute_slider')]/a[@class='ui-slider-handle ui-state-default ui-corner-all']", "");
		if(isClicked && ActionHelper.dragAndDropByPixels(driver, minutes_slider, 15, 0)) {
			isScheduleSelected = ActionHelper.Click(driver, datePicker_done_button);
		}
		ActionHelper.staticWait(2);
		return isScheduleSelected;
	}
	public boolean isScheduledMessageDisplayed() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver,scheduled_message , 20, 10);
	}
	public String getScheduledLabel() {
		String lSchedule = "";
		if(ActionHelper.waitForElementToBeVisible(driver, scheduled_label, 30)) {
			lSchedule = ActionHelper.getText(driver, scheduled_label);
		}
		return lSchedule;
	}
	public boolean checkSelectedRecipient() {
		return ActionHelper.isElementSelected(driver, individual_recipient);
	}
	public boolean isListingSubjectVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, listing_subject, 30);
	}
	public boolean typeListingEmailSubject(String pSubject) {
		return ActionHelper.Type(driver, listing_subject, pSubject);
	}
	public boolean isEmailListingFlyerButtonVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, emailListingFlyer_button,30);
	}
	public boolean isAddressRadioButtonVisible() {
		return ActionHelper.isElementVisible(driver, address_radio_input);
	}
	public boolean isCityRadioButtonVisible() {
		return ActionHelper.isElementVisible(driver, city_radio_input);
	}
	public boolean isZipRadioButtonVisible() {
		return ActionHelper.isElementVisible(driver, zip_radio_input);
	}
	public boolean isMLSRadioButtonVisible() {
		return ActionHelper.isElementVisible(driver, mls_radio_input);
	}
	public boolean clickOnMLSRadioButton() {
		return ActionHelper.Click(driver, mls_radio_input);
	}
	public boolean typeInputField(String pStringToType) {
		return ActionHelper.Type(driver, search_input, pStringToType);	
	}
	public boolean clickOnSearchButton() {
		boolean isClicked = false;
		if(ActionHelper.Click(driver, search_button)) {
			ActionHelper.waitForElementToBeDisappeared(driver, processing,60);
			isClicked = true;
		}
		return isClicked;
	}
	public boolean isListingWithMLSIDPresent(String pMLSID) {
		return ActionHelper.getDynamicElement(driver, mls_number_in_search_results, pMLSID)!=null;
	}
	public boolean clickOnNextButton() {
		return ActionHelper.Click(driver, next_button);
	}
	public boolean isSubjectValidationMessageVisible() {
		return ActionHelper.isElementVisible(driver, subject_validation);
	}
	public boolean isSelectListingValidationMessageVisible() {
		return ActionHelper.isElementVisible(driver, selected_validation);
	}
	public boolean clickOnAddListingButton() {
		boolean isAdded = false;
		if(ActionHelper.Click(driver, ActionHelper.getListOfElementByXpath(driver, add_listing_button_list).get(0))) {
			isAdded = ActionHelper.getListOfElementByXpath(driver, added_label_list).size()>0;
		}
		return isAdded;
	}
	public boolean clickOnRemoveButton() {
		return ActionHelper.Click(driver, ActionHelper.getListOfElementByXpath(driver, remove_listing_button_list).get(0));
	}
	public boolean clickOnCalendarButton() {
		return ActionHelper.Click(driver, calendar_button);
	}
	public String getScheduleLabel() {
		String lSchedule = "";
		if(ActionHelper.waitForElementToBeVisible(driver, schedule_label, 30)) {
			lSchedule = ActionHelper.getText(driver, schedule_label);
		}
		return lSchedule;
	}
	public boolean clickOnRemoveScheduleButton() {
		return ActionHelper.Click(driver, remove_Schedule_button);
	}
	public boolean isScheduleButtonVisible() {
		return ActionHelper.isElementVisible(driver, schedule_button);
	}
	public boolean isPreviewHeadingVisibleForSendListingEmail() {
		return ActionHelper.isElementVisible(driver, preview_heading);
	}
}
