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

import resources.forms.zurple.backoffice.ZBOAddTemplateForm;
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
	
	ZBOAddTemplateForm zboAddTemplateForm;
	
	public ZBOCreateCampaignPage() {
		
	}
	public ZBOCreateCampaignPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setZboAddTemplateForm();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOAddTemplateForm getZboAddTemplateForm() {
		return zboAddTemplateForm;
	}
	public void setZboAddTemplateForm() {
		this.zboAddTemplateForm = new ZBOAddTemplateForm(driver);
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
			}
		}
		return lTemplateName;
	}
	public boolean clickOnUpdateButton() {
		return ActionHelper.Click(driver, update_button);
	}
	public boolean typeCampaignName(String pCampaignName) {
		return ActionHelper.Type(driver, campaign_name, pCampaignName);
	}
	public boolean typeCampaignDescription(String pCampaignDesc) {
		return ActionHelper.Type(driver, campaign_name, pCampaignDesc);
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
}
