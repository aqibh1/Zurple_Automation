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

/**
 * @author adar
 *
 */
public class ZBOCreateCampaignPage extends Page{
	
	@FindBy(xpath="//div[@class='col-sm-4']/descendant::h3[text()='Campaign Details']")
	WebElement campaign_details_heading;
	
	@FindBy(id="add-template")
	WebElement addTemplate_button;
	
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
}
