package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

public class NewPageCreatedFromPP extends Page{

	@FindBy(id="menu-top-navigation")
	WebElement topMenuNavigation;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageHeading;
	
	@FindBy(xpath="//DIV[@class='single-content']/descendant::p")
	WebElement pageBody;
	
	@FindBy(id="calendar_wrap")
	WebElement calendarWidget;
	
	public NewPageCreatedFromPP() {
		
	}
	public NewPageCreatedFromPP(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPageLoaded() {
		return ActionHelper.waitForElementToBeVisible(driver, pageHeading, 30);
	}
	
	public boolean verifyPageHeading(String pHeadingToVerify) {
		return ActionHelper.getText(driver, pageHeading).trim().equalsIgnoreCase(pHeadingToVerify);
	}
	
	public boolean verifyPageBody(String pBodyToVerify) {
		return ActionHelper.getText(driver, pageBody).trim().equalsIgnoreCase(pBodyToVerify);
	}
	public boolean isCalendarWidgetVisible() {
		ActionHelper.ScrollDownByPixels(driver,"1500");
		return ActionHelper.waitForElementToBeVisible(driver, calendarWidget, 10);
	}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
