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
	
	@FindBy(xpath="//div[@id='lead-groups']/descendant::input[@value]")
	WebElement typeTagName;
	
	@FindBy(xpath="//div[@class='lead-tag-group-controls']/button[contains(@class,'plus-button') and not(contains(@style,'display: none'))]")
	WebElement plusTag;
	
	@FindBy(id="save-lead-groups")
	WebElement saveTag;
		
	@FindBy(xpath="//button[contains(text(),'Close')]")
	WebElement closeModal;
	
	@FindBy(id="lead-groups")
	WebElement modalMessage;
	
	@FindBy(className="lead-tag")
	WebElement leadTag;
	
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
		ActionHelper.waitForElementToBeVisible(driver, plusTag, 30);
		return ActionHelper.Click(driver, plusTag);
	}
	
	public boolean justTesting3() {
		ActionHelper.waitForElementToBeVisible(driver, typeTagName, 30);		
		return ActionHelper.ClearAndType(driver, typeTagName, "Auto-Tag");
	}
		
	public boolean justTesting4() {
		ActionHelper.waitForElementToBeVisible(driver, selectTag, 30);
		return ActionHelper.Click(driver, selectTag);
	}
	
	public boolean justTesting5() {
		ActionHelper.waitForElementToBeVisible(driver, saveTag, 30);
		return ActionHelper.Click(driver, saveTag);
	}
	
	public String justTesting6() {
		ActionHelper.staticWait(5);
		return ActionHelper.getText(driver, modalMessage).trim();
	}
	
	public boolean justTesting7() {
		ActionHelper.staticWait(5);
		return ActionHelper.waitForElementToBeVisible(driver, closeModal, 30);					
	}
	
	public String justTesting8() {
		ActionHelper.waitForElementToBeVisible(driver, saveTag, 30);
		return ActionHelper.getText(driver, leadTag).trim();
	}
}
