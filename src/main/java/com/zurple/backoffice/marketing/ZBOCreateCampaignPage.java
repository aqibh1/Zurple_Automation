/**
 * 
 */
package com.zurple.backoffice.marketing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSelectCampaignAlert;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.forms.zurple.backoffice.ZBOAddTemplateForm;
import resources.forms.zurple.backoffice.ZBOLeadListForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
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
	String template_options_xpath = "//select[@id='templates-select']/option";
	
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
	
	@FindBy(className="ui-button-icon-primary")
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
	
	@FindBy(id="toemail")
	WebElement toemail;
	
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please verify the email address you have entered belongs to one of your active leads.']")
	WebElement email_verify_Alert;
	
	String added_templates_list = "//div[@id='steps_table_wrapper']/descendant::tr[@data-template]";
	
	@FindBy(xpath="//div[@id='steps_table_wrapper']/descendant::tr[@id='row-0']")
	WebElement template_row_0;
	
	@FindBy(xpath="//div[@id='steps_table_wrapper']/descendant::tr[@id='row-1']")
	WebElement template_row_1;
	
	String number_of_days_input = "//input[@name='days_after_last_step["+FrameworkConstants.DYNAMIC_VARIABLE+"]']";
	
	String drag_drop_icon = "//td[@class='row-index sorting_1']";
	
	@FindBy(xpath="//li[@id='filtered-lead-list_next' and @class='paginate_button next']/a[text()='Next']")
	WebElement next_button;
	
	@FindBy(xpath="//li[@id='campaigns_table_next' and @class='paginate_button next']")
	WebElement campaigns_next_button;
	
	ZBOAddTemplateForm zboAddTemplateForm;
	ZBOLeadListForm zboLeadListform;
	ZBOSucessAlert successalert;
	ZBOSelectCampaignAlert zboSelectCampaignAlert;
	
	public ZBOCreateCampaignPage() {
		
	}
	public ZBOCreateCampaignPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setZboAddTemplateForm();
		setZboLeadListform();
		setSuccessAlert();
		setSelectCampaignAlert();
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
	public ZBOSelectCampaignAlert getSelectCampaignAlert() {
		return zboSelectCampaignAlert;
	}
	public void setSelectCampaignAlert() {
		this.zboSelectCampaignAlert = new ZBOSelectCampaignAlert(driver);
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
	public List<String> getLeadsName(){
		List<WebElement> list_element = getMatchingLeads();
		List<String> name_list = new ArrayList<String>();
		for(int i=0;i<list_element.size();i++) {
			String l_lead_name = ActionHelper.getText(driver, list_element.get(i));
			name_list.add(i, l_lead_name);
		}
		return name_list;
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
		try {
		while(!isVerifed) {
			List<WebElement> l_campaignNamelist = ActionHelper.getListOfElementByXpath(driver, campaign_name_list);
			List<WebElement> l_campaignLead_count = ActionHelper.getListOfElementByXpath(driver, view_recipients_campaigns);
			for(int i=0;i<l_campaignNamelist.size();i++) {
				if(ActionHelper.getText(driver, l_campaignNamelist.get(i)).equalsIgnoreCase(pCampaignName)) {
					int lead_count = Integer.parseInt(ActionHelper.getText(driver, l_campaignLead_count.get(i)).split(" ")[0]);
					isVerifed = lead_count==pLeadCount;
					break;
				}
			}
			if(ActionHelper.isElementVisible(driver, campaigns_next_button)){
				ActionHelper.Click(driver, driver.findElement(By.xpath("//li[@id='campaigns_table_next']/a")));
				ActionHelper.staticWait(5);
			}
		}
		}catch(Exception ex) {
			AutomationLogger.info("Lead count was not verified");
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
	public boolean clickOnIndividualLeadOption() {
		return ActionHelper.Click(driver, massemail_type_lead);
	}
	public boolean typeEmailAddress(String pEmail) {
		return ActionHelper.Type(driver, toemail, pEmail);
	}
	public boolean isEmailVerificationAlertIsTriggered() {
		return ActionHelper.waitForElementToBeVisible(driver, email_verify_Alert, 20);
	}
	public boolean verifyInputEmailTurnsRed() {
		return ActionHelper.getCssValueOfTheElement(driver, toemail, "border-color").equalsIgnoreCase("rgb(169, 68, 66)");
	}
	public int getAddedTemplatesCount() {
		return ActionHelper.getListOfElementByXpath(driver, added_templates_list).size();
	}
	public boolean clickAndSelectMultipleTemplate() {
		boolean isClicked = false;
		int index = 0;
		clickOnAddTemplateButton();
		if(ActionHelper.isElementVisible(driver, template_options)) {
			List<WebElement> list_of_options = ActionHelper.getListOfElementByXpath(driver, template_options_xpath);
			index = generateRandomInteger(list_of_options.size());
			isClicked = ActionHelper.clickAndSelectByIndex(driver, template_options, template_options_xpath, index);
			clickOnUpdateButton();
			ActionHelper.staticWait(3);
			int index2 = generateRandomInteger(list_of_options.size(),index);
			clickOnAddTemplateButton();
			ActionHelper.isElementVisible(driver, template_options);
			isClicked = ActionHelper.clickAndSelectByIndex(driver, template_options, template_options_xpath, index2);	
			clickOnUpdateButton();
		}
		return isClicked;
	}
	public boolean dragRow1ToRow0() {
		return ActionHelper.dragAndDropByPixels(driver, template_row_1, 0, -125);
//		return ActionHelper.dragAndDrop(driver, row_1, row_0);
	}
	public String getRow0TemplateId() {
		WebElement row_0 = driver.findElement(By.xpath("//div[@id='steps_table_wrapper']/descendant::tr[@id='row-0']"));
		return ActionHelper.getAttribute(row_0, "data-template");
	}public String getRow1TemplateId() {
		WebElement row_1 = driver.findElement(By.xpath("//div[@id='steps_table_wrapper']/descendant::tr[@id='row-1']"));
		return ActionHelper.getAttribute(row_1, "data-template");
	}
	
	public boolean typeNumberOfDaysInTemplate(String pTemplateId, String pNumOfDays) {
		return ActionHelper.ClearAndType(driver,ActionHelper.getDynamicElement(driver, number_of_days_input, pTemplateId) , pNumOfDays);
	}
	public String getNumberOfDaysInTemplate(String pTemplateId) {
		return ActionHelper.getTextByValue(driver,ActionHelper.getDynamicElement(driver, number_of_days_input, pTemplateId));
	}
	public boolean isDragDropIconVisible() {
		return ActionHelper.isElementVisible(driver, ActionHelper.getListOfElementByXpath(driver, drag_drop_icon).get(0));
	}
	public boolean isLeadEnrolledInTheList(List<WebElement> pViewEnrolledList,List<String> pViewMatchingList ) {
		boolean isFound = false;
		for(int i=0;i<pViewMatchingList.size();i++) {
			String l_matching_enrolled_lead = pViewMatchingList.get(i);
			for(int j=0;j<pViewEnrolledList.size();j++) {
				String l_view_enrolled_lead = ActionHelper.getText(driver,pViewEnrolledList.get(j));
				if(l_matching_enrolled_lead.equalsIgnoreCase(l_view_enrolled_lead)) {
					isFound = true;
					break;
				}
			}
			if(!isFound) {
				break;
			}
		}
		return isFound;
	}
	public boolean verifyIsLeadEnrolledInTheList(List<String> pViewMatchingList) {
		boolean isFound = false;
		String l_matching_enrolled_lead = pViewMatchingList.get(0);
		while(!isFound) {
			List<WebElement> pViewEnrolledList = getMatchingLeads();
			for(int j=0;j<pViewEnrolledList.size();j++) {
				String l_view_enrolled_lead = ActionHelper.getText(driver,pViewEnrolledList.get(j));
				if(l_matching_enrolled_lead.equalsIgnoreCase(l_view_enrolled_lead)) {
					isFound = true;
					break;
				}
			}
			if(!isFound && ActionHelper.isElementVisible(driver, next_button)) {
				ActionHelper.Click(driver, next_button);
			}else {
				break;
			}
		}
		return isFound;
	}
	 private int generateRandomInteger(int pUpperRange){
	    	Random random = new Random();
	    	int lNum = random.nextInt(pUpperRange);
	    	if(lNum==pUpperRange) {
	    		lNum = lNum - 1;
	    	}else if(lNum==0) {
	    		lNum++;
	    	}
	    	return lNum;
	} private int generateRandomInteger(int pUpperRange, int pDuplicateNumber){
		int randNum = 1;
    	while(randNum==pDuplicateNumber) {
    		randNum = generateRandomInt(pUpperRange);
    	}
    	return randNum;
}
}
