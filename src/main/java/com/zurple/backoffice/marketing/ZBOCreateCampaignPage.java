/**
 * 
 */
package com.zurple.backoffice.marketing;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.forms.zurple.backoffice.ZBOAddTemplateForm;
import resources.forms.zurple.backoffice.ZBOLeadListForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOCreateCampaignPage extends Page{
	
	@FindBy(xpath="//div[@class='col-sm-4']/descendant::h3[text()='Campaign Details']")
	WebElement campaign_details_heading;
	
	@FindBy(id="add-template")
	WebElement addTemplate_button;
	
	String template_link = "//td/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//select[@id='templates-select']")
	WebElement template_options;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement update_button;
	
	@FindBy(id="title")
	WebElement campaign_name;
	
	@FindBy(id="description")
	WebElement campaign_desc;
	
	@FindBy(id="campaign-save")
	WebElement campaign_save;
	
	@FindBy(xpath="//table[@id='steps_table']/descendant::a")
	String template_list = "//table[@id='steps_table']/descendant::a";
	
	@FindBy(xpath="//table[@id='steps_table']/descendant::div[text()='Preview']")
	WebElement template_preview_button;
	
	@FindBy(id="preview")
	WebElement preview_window;
	
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter campaign name']")
	WebElement campaign_name_alert;
	
	@FindBy(id="massemail_type-lead")
	WebElement massemail_type_lead;
	@FindBy(id="massemail_type-allunresponsiveleads")
	WebElement massemail_type_allunresponsiveleads;
	@FindBy(id="massemail_type-allresponsiveleads")
	WebElement massemail_type_allresponsiveleads;
	@FindBy(id="massemail_type-allclients")
	WebElement massemail_type_allclients;
	
	@FindBy(xpath="//button[@title='close']/span")
	WebElement close_preview;
	
	@FindBy(id="view-matching-button")
	WebElement view_matching_button;
	
	@FindBy(id="enroll-button")
	WebElement enroll_button;
	
	String matching_lead_list = "//table[@id='filtered-lead-list']/tbody/tr/td/a";
	
	@FindBy(id="view-recipients-button")
	WebElement view_recipients_button;
	
	String view_recipients_campaigns= "//table[@id='campaigns_table']/descendant::tr/td[@class=' view_recipients_button']";
	String campaign_name_list = "//table[@id='campaigns_table']/descendant::tr/td/a[not(contains(@class,'btn'))]";
	String enrolled_leads_list = "//table[@id='filtered-lead-list']/descendant::input[@name]";
	
	@FindBy(id="unenroll-all-button")
	WebElement unenroll_all_button;
	
	ZBOAddTemplateForm zboAddTemplateForm;
	ZBOLeadListForm zboLeadListform;
	ZBOSucessAlert successalert;
	
	public ZBOCreateCampaignPage() {
		
	}
	public ZBOCreateCampaignPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setZboAddTemplateForm();
		setZboLeadListform();
		setSuccessAlert();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOAddTemplateForm getZboAddTemplateForm() {
		return zboAddTemplateForm;
	}
	public void setZboAddTemplateForm() {
		this.zboAddTemplateForm = new ZBOAddTemplateForm(driver);
	}
	public ZBOLeadListForm getZboLeadListform() {
		return zboLeadListform;
	}
	public void setZboLeadListform() {
		this.zboLeadListform = new ZBOLeadListForm(driver);
	}
	public ZBOSucessAlert getSuccessAlert() {
		return successalert;
	}
	public void setSuccessAlert() {
		successalert = new ZBOSucessAlert(driver);
	}
	public boolean isCampaignPage() {
		return ActionHelper.waitForElementToBeVisible(driver, campaign_details_heading, 15);
	}
	public boolean clickOnAddTemplateButton() {
		return ActionHelper.Click(driver, addTemplate_button);
	}
	public boolean clickOnTemplateLink(String pTemplateName) {
		boolean isClicked = false;
		ActionHelper.staticWait(10);
		WebElement element = ActionHelper.getDynamicElement(driver, template_link, pTemplateName);
		if(ActionHelper.waitForElementToBeVisible(driver, element, 20)) {
			isClicked =  ActionHelper.Click(driver, element);
		}
		return isClicked;
	}
	public String clickAndSelectTemplate() {
		boolean isSelected = false;
		String lTemplateName = "";
		if(clickOnAddTemplateButton() && ActionHelper.waitForElementToBeVisible(driver, update_button, 30)) {
			List<WebElement> option = ActionHelper.getListOfElementByXpath(driver, "//select[@id='templates-select']/option");
			int index = generateRandomInt(option.size());
			if(index==0) {
				index = index+1;
			}
			isSelected = ActionHelper.clickAndSelectByIndex(driver,template_options,"//select[@id='templates-select']/option",index);
			if(isSelected) {
				lTemplateName = ActionHelper.getText(driver, option.get(index));
				clickOnUpdateButton();
			}
		}
		return lTemplateName;
	}
	public boolean clickAndSelectAutoTemplate(String pTemplateName) {
		return ActionHelper.selectDropDownOptionIfContains(driver, template_options, pTemplateName);
	}
	public boolean clickOnUpdateButton() {
		return ActionHelper.Click(driver, update_button);
	}
	public boolean typeCampaignName(String pCampaignName) {
		return ActionHelper.Type(driver, campaign_name, pCampaignName);
	}
	public boolean typeCampaignDescription(String pCampaignDesc) {
		return ActionHelper.Type(driver, campaign_desc, pCampaignDesc);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, campaign_save);
	}
	public boolean isTemplatedAdded(String pTemplateName) {
		boolean isAdded = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, template_list);
		for(WebElement element: list) {
			if(ActionHelper.getText(driver, element).equalsIgnoreCase(pTemplateName)) {
				isAdded = true;
				break;
			}
		}
		return isAdded;
	}
	public boolean clickOnPreviewButton() {
		return ActionHelper.Click(driver, template_preview_button);
	}public boolean isPrviewContains(String pPlaceHolderValue) {
		return ActionHelper.getText(driver, preview_window).contains(pPlaceHolderValue);
	}
	public boolean isEmptyCampaignNameAlertVisible() {
		return ActionHelper.isElementVisible(driver, campaign_name_alert);
	}
	public boolean verifyRecipientsOptionsAreVisible() {
		boolean isOptionsVisible = true;
		if(!ActionHelper.isElementVisible(driver, massemail_type_allclients)) {
			isOptionsVisible = false;
		}else if(!ActionHelper.isElementVisible(driver, massemail_type_allresponsiveleads)) {
			isOptionsVisible = false;
		}else if(!ActionHelper.isElementVisible(driver, massemail_type_allunresponsiveleads)) {
			isOptionsVisible = false;
		}else if(!ActionHelper.isElementVisible(driver, massemail_type_lead)) {
			isOptionsVisible = false;
		}
		return isOptionsVisible;
	}
	public boolean closePreview() {
		return ActionHelper.Click(driver, close_preview);
	}
	public boolean clickOnMatchingLeadButton() {
		return ActionHelper.Click(driver, view_matching_button);
	}
	public boolean clickOnEnrollButton() {
		return ActionHelper.Click(driver, enroll_button);
	}
	public List<WebElement> getMatchingLeads(){
		return ActionHelper.getListOfElementByXpath(driver, matching_lead_list);
	}
	public boolean clickOnViewRecipientsButton() {
		return ActionHelper.Click(driver, view_recipients_button);
	}
	public boolean verifyLeadsAreEnrolled(List<WebElement> pViewEnrolledList,List<WebElement> pViewMatchingList ) {
		boolean isFound = false;
		for(int i=0;i<pViewMatchingList.size();i++) {
			String l_matching_enrolled_lead = ActionHelper.getText(driver, pViewMatchingList.get(i));
			for(int j=0;j<pViewEnrolledList.size();j++) {
				String l_view_enrolled_lead = ActionHelper.getText(driver, pViewMatchingList.get(j));
				if(l_matching_enrolled_lead.equalsIgnoreCase(l_view_enrolled_lead)) {
					isFound = true;
					break;
				}
				if(!isFound) {
					break;
				}
			}
		}
		return isFound;
	}
	public boolean verifyLeadCount(String pCampaignName, int pLeadCount) {
		boolean isVerifed = false;
		List<WebElement> l_campaignNamelist = ActionHelper.getListOfElementByXpath(driver, campaign_name_list);
		List<WebElement> l_campaignLead_count = ActionHelper.getListOfElementByXpath(driver, view_recipients_campaigns);
		for(int i=0;i<l_campaignNamelist.size();i++) {
			if(ActionHelper.getText(driver, l_campaignNamelist.get(i)).equalsIgnoreCase(pCampaignName)) {
				int lead_count = Integer.parseInt(ActionHelper.getText(driver, l_campaignLead_count.get(i)).split(" ")[0]);
				isVerifed = lead_count==pLeadCount;
				break;
			}
		}
		return isVerifed;
	}
	public boolean unenrollLeadFromCurrentlyEnrolledPanel(List<WebElement> pViewMatchingList, String pLeadName) {
		boolean isUnenrolled = false;
		pViewMatchingList = ActionHelper.getListOfElementByXpath(driver, matching_lead_list);;
		for(int i=0;i<pViewMatchingList.size();i++) {
			String l_matching_enrolled_lead = ActionHelper.getText(driver, pViewMatchingList.get(i));
			if(l_matching_enrolled_lead.equalsIgnoreCase(pLeadName)) {
				isUnenrolled = ActionHelper.Click(driver, ActionHelper.getListOfElementByXpath(driver, enrolled_leads_list).get(i+1));
				break;
			}
		}
		return isUnenrolled;
	}
	public String getEnrolledLead(List<WebElement> pEnrolledLeadsList) {
		pEnrolledLeadsList = ActionHelper.getListOfElementByXpath(driver, matching_lead_list);
		int random = generateRandomInt(pEnrolledLeadsList.size());
		return ActionHelper.getText(driver, pEnrolledLeadsList.get(random));
	}
	public boolean clickOnAllLeadsCommunicated() {
		return ActionHelper.Click(driver, massemail_type_allresponsiveleads);
	}	public boolean clickOnAllLeadsStatusClient() {
		return ActionHelper.Click(driver, massemail_type_allclients);
	}
	public boolean clickOnUnenrollButton() {
		return ActionHelper.Click(driver, unenroll_all_button);
	}
}
