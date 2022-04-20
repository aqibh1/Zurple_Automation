/**
 * 
 */
package com.zurple.backoffice.marketing;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.zurple.backoffice.ZBOPreviewForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOCampaignDetailsPage extends Page{
	
	@FindBy(xpath="//div[@class='col-sm-4']/descendant::h3[text()='Campaign Details']")
	WebElement campaign_details_heading;
	
	@FindBy(id="title")
	WebElement campaign_name_title;
	
	@FindBy(id="description")
	WebElement campaign_name_description;
	
	@FindBy(id="add-template")
	WebElement addTemplate_button;
	
	String remove_button_list = "//table[@id='steps_table']/descendant::div[text()='Remove']";
	
	String preview_button_list = "//table[@id='steps_table']/descendant::div[text()='Preview']";
	
	String days_hours_mins_input_list = "//*[@id='campaign-edit-form']/descendant::div[@title]/input[contains(@name,'_after_last_step')]";
	
	@FindBy(id="massemail_type-lead")
	WebElement massemail_type_lead;
	@FindBy(id="massemail_type-prospectnew")
	WebElement massemail_type_prospectnew;
	@FindBy(id="massemail_type-allunresponsiveleads")
	WebElement massemail_type_allunresponsiveleads;
	@FindBy(id="massemail_type-allresponsiveleads")
	WebElement massemail_type_allresponsiveleads;
	@FindBy(id="massemail_type-allclients")
	WebElement massemail_type_allclients;
	
	@FindBy(xpath="//li[@title='Zurple Auto Leads']")
	WebElement zurple_auto_leads_source;
	
	@FindBy(xpath="//*[@id='form-element-traffic_sources']/descendant::input")
	WebElement lead_source_input;
	
	@FindBy(xpath="//div[@id='form-element-auto_enroll']/descendant::span")
	WebElement auto_enroll_toggle;
	
	@FindBy(id="phone_required")
	WebElement phone_required;
	
	@FindBy(xpath="//div[@id='form-element-phone_required']/descendant::span")
	WebElement phone_required_toggle;
	
	String template_name_list = "//table[@id='steps_table']/descendant::td/a";
	
	private ZBOPreviewForm previewForm;
	
	public ZBOCampaignDetailsPage() {
		
	}
	public ZBOCampaignDetailsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setPreviewForm();
		PageFactory.initElements(driver, this);
	}
	public ZBOPreviewForm getPreviewForm() {
		return previewForm;
	}
	public void setPreviewForm() {
		this.previewForm = new ZBOPreviewForm(driver);
	}
	public boolean isCampaignPage() {
		return ActionHelper.waitForElementToBeVisible(driver, campaign_details_heading, 15);
	}
	public String getCampaignName() {
		return ActionHelper.getTextByValue(driver, campaign_name_title);
	}
	public boolean isAddTemplateButtonVisible() {
		return ActionHelper.isElementVisible(driver, addTemplate_button);
	}
	public boolean isRemoveButtonDisabled() {
		boolean isEnabled = false;
		List<WebElement> list_remove_button = ActionHelper.getListOfElementByXpath(driver, remove_button_list);
//		List<WebElement> list_remove_button = ActionHelper.getListOfElementByXpath(driver, preview_button_list);
		for(WebElement element: list_remove_button) {
			if(!ActionHelper.isElementDisabled(driver, element)) {
				isEnabled = true;
				break;
			}
		}
		return !isEnabled;
	}
	public boolean verifyUserCanPreviewTheTemplates() {
		boolean isPreviewButtonWorking = false;
		List<WebElement> list_remove_button = ActionHelper.getListOfElementByXpath(driver, preview_button_list);
		for(WebElement element: list_remove_button) {
			isPreviewButtonWorking = false;
			if(ActionHelper.Click(driver, element)) {
				ActionHelper.staticWait(2);
				isPreviewButtonWorking = getPreviewForm().isPreviewHeadingVisibleTemplate();
				getPreviewForm().closePreviewWindow();
				ActionHelper.staticWait(2);
			}
			if(!isPreviewButtonWorking) {
				break;
			}
		}
		return isPreviewButtonWorking;
	}
	public boolean isTimeLineInputDisabled() {
		boolean isDisabled = false;
		List<WebElement> list_remove_button = ActionHelper.getListOfElementByXpath(driver, days_hours_mins_input_list);
		for(WebElement element: list_remove_button) {
			isDisabled = false;
			if(ActionHelper.isElementDisabled(driver, element)) {
				isDisabled = true;
			}else {
				break;
			}
		}
		return isDisabled;
	}
	public boolean isCampaignNameInputDisabled() {
		return !ActionHelper.isElementEnabled(driver, campaign_name_title);
	}
	public boolean isCampaignDescriptionInputDsiabled() {
		return !ActionHelper.isElementEnabled(driver, campaign_name_description);
	}
	public boolean isIndividualLeadOptionDisabled() {
		return !ActionHelper.isElementEnabled(driver, massemail_type_lead);
	}
	public boolean isAllLeadNewOptionDisabled() {
		return !ActionHelper.isElementEnabled(driver, massemail_type_prospectnew);
	}
	public boolean isIndividualLeadUnresponsiveOptionDisabled() {
		return !ActionHelper.isElementEnabled(driver, massemail_type_allunresponsiveleads);
	}
	public boolean isIndividualLeadCommunicatedOptionDisabled() {
		return !ActionHelper.isElementEnabled(driver, massemail_type_allresponsiveleads);
	}
	public boolean isIndividualLeadActiveOptionDisabled() {
		return !ActionHelper.isElementEnabled(driver, massemail_type_allclients);
	}
	public boolean isZurpleAutoLeadsSourceVisible() {
		return ActionHelper.isElementVisible(driver, zurple_auto_leads_source);
	}
	public boolean isLeadSourceInputDisabled() {
		return !ActionHelper.isElementEnabled(driver, lead_source_input);
	}
	public boolean verifyAutoEnrollCanBeToggled() {
		boolean isChecked = false;
		boolean isToggledValue = ActionHelper.isElementSelected(driver, driver.findElement(By.id("auto_enroll")));
		ActionHelper.Click(driver, auto_enroll_toggle);
		isChecked = isToggledValue!=ActionHelper.isElementSelected(driver, driver.findElement(By.id("auto_enroll")));
		ActionHelper.Click(driver, auto_enroll_toggle);
		return isChecked;
	}
	public boolean verifyPhoneNumberCannotBeToggled() {
		boolean isChecked = false;
		boolean isToggledValue = ActionHelper.isElementSelected(driver, phone_required);
		ActionHelper.Click(driver, phone_required_toggle);
		isChecked = isToggledValue!=ActionHelper.isElementSelected(driver,phone_required);
		ActionHelper.Click(driver, phone_required_toggle);
		return isChecked;
	}
	public boolean isUnresponsiveRadioSelected() {
		return ActionHelper.isElementSelected(driver, massemail_type_allunresponsiveleads);
	}public boolean isProspectNewRadioSelected() {
		return ActionHelper.isElementSelected(driver, massemail_type_prospectnew);
	}
	public List<String> getTemplateNames(){
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, template_name_list);
		List<String> template_name_list = new ArrayList<String>();
		for(WebElement element: list_of_elements) {
			String template_name = ActionHelper.getText(driver, element);
			template_name_list.add(template_name);
		}
		return template_name_list;
	}
}
