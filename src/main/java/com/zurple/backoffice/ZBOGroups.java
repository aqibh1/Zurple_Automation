package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOGroups extends Page{
	boolean isRefreshPageRequired = true;
	
	@FindBy(className="fa-tags")
	WebElement groupTag;
	
	@FindBy(className="lead-assign")
	WebElement selectTag;
	
	@FindBy(xpath="//input[@value='']")
	WebElement typeTagName;
	
	@FindBy(xpath="//div[@class='lead-tag-group-controls']/button[contains(@class,'plus-button') and not(contains(@style,'display: none'))]")
	WebElement plusTag;
		
	public ZBOGroups() {
	}
	
	public ZBOGroups(WebDriver pWebdriver) {
		driver = pWebdriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean justTesting1() {
		ActionHelper.waitForElementToBeVisible(driver, groupTag, 30);
		return ActionHelper.Click(driver, groupTag);
	}
	
	public boolean justTesting2() {
		// ActionHelper.waitForElementToBeVisible(driver, plusTag, 30);
		ActionHelper.staticWait(5);
		return ActionHelper.Click(driver, plusTag);
	}
	
	public boolean justTesting3() {
		// ActionHelper.waitForElementToBeVisible(driver, plusTag, 30);
		ActionHelper.staticWait(5);
		return ActionHelper.Type(driver, typeTagName, "AutoTag");
	}
		
	public boolean justTesting4() {
		//ActionHelper.waitForElementToBeVisible(driver, addTag, 30);
		ActionHelper.staticWait(5);
		return ActionHelper.Click(driver, selectTag);
	}
	
}
