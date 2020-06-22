/**
 * 
 */
package com.zurple.backoffice.marketing;

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
}
