package com.zurple.backoffice.marketing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class ZBOTemplateManagerPage extends Page{
	
	@FindBy(xpath="//h1[text()='Template Manager']")
	WebElement templateManager_heading;
	
	@FindBy(id="template-create-button")
	WebElement createTemplate_button;
	
	String template_row = "//a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::tr[@data-template-id]";
	
	@FindBy(xpath="//li[@id='templates-table_next' and @class='paginate_button next']/a[text()='Next']")
	WebElement next_button;
	
	public ZBOTemplateManagerPage() {
		
	}
	public ZBOTemplateManagerPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isTemplateManagerPageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, templateManager_heading, 15);
	}
	public boolean clickOnCreateTemplateButton() {
		return ActionHelper.Click(driver, createTemplate_button);
	}
	public boolean clickOnEditButton(String pTemplateName) {
		boolean isClicked = false;
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, template_row, pTemplateName);
		if(element!=null) {
			isClicked = ActionHelper.Click(driver, element.findElement(By.xpath("/descendant::a[text()='Edit']")));
		}
		return isClicked;
	}
	public boolean searchAndClickEditButton(String pTemplateName) {
		AutomationLogger.info("Template Name :: "+pTemplateName );
		boolean isClicked = false;
		do {
		
			isClicked = clickOnEditButton(pTemplateName);
			if(!isClicked) {
				ActionHelper.Click(driver, next_button);
				ActionHelper.staticWait(10);
			}else {
				break;
			}
		}while(ActionHelper.isElementVisible(driver, next_button));
		return isClicked;
	}
}
