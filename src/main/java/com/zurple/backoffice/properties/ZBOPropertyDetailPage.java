package com.zurple.backoffice.properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOPropertyDetailPage extends Page{

	@FindBy(id="comment")
	WebElement textArea;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement save_button;
	
	@FindBy(xpath="//a[text()='view on site']")
	WebElement view_on_site;
	
	public ZBOPropertyDetailPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeNote(String pNote) {
		return ActionHelper.ClearAndType(driver, textArea, pNote);
	}
	
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
	public boolean clickOnViewOnWebSite() {
		return ActionHelper.Click(driver, view_on_site);
	}
	public boolean isPropertyDetailPageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, textArea, 30);
	}
}
