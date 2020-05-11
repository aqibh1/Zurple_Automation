package com.zurple.backoffice.marketing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOTemplateManagerPage extends Page{
	
	@FindBy(xpath="//h1[text()='Template Manager']")
	WebElement templateManager_heading;
	
	@FindBy(id="template-create-button")
	WebElement createTemplate_button;
	
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
}
