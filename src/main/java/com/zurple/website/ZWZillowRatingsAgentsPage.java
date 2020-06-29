package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;


/**
 * @author aaqib
 *
 */

public class ZWZillowRatingsAgentsPage extends Page{
	
	@FindBy(className="page-header")
	WebElement page_header;
	
	String agent_name = "media-heading";
	
	@FindBy(className="rating_container rating_4_75")
	WebElement zillow_rating;
		
	public ZWZillowRatingsAgentsPage() {
		
	}
	
	public ZWZillowRatingsAgentsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
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

	@Override
	public WebElement getTopMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String headerText() {
	ActionHelper.waitForElementToBeVisible(driver, page_header,30);	
	return ActionHelper.getText(driver, page_header);	
	}
	
	public boolean clickAgentName() {
		ActionHelper.waitForStringClassNameToBeVisible(driver, agent_name,30);
		return ActionHelper.ClickByIndex(driver, agent_name, 0);
	}
	
	public boolean zillowRating() {
		return ActionHelper.isElementVisible(driver, zillow_rating);
	}
	
	
	
	
}
