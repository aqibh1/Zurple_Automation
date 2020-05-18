/**
 * 
 */
package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWSchoolsReportsPage extends Page{
	
	@FindBy(xpath="//h2[contains(text(),'School Reports')]")
	WebElement schoolReports_heading;
	
	@FindBy(id="community")
	WebElement community_input;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement search_button;

	public ZWSchoolsReportsPage() {
		
	}
	public ZWSchoolsReportsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSchoolReportsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, schoolReports_heading, 20);
	}
	public boolean typeZip(String pZipCode) {
		return ActionHelper.ClearAndType(driver, community_input, pZipCode);
	}
	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, search_button);
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

}
