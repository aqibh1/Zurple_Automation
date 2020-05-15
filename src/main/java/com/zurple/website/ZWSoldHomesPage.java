/**
 * 
 */
package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.zurple.website.ZWSoldHomesModifySearchForm;
import resources.utility.ActionHelper;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWSoldHomesPage extends Page{

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
	public ZWSoldHomesPage() {
	}
	public ZWSoldHomesPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setModifySearchForm();
		PageFactory.initElements(driver, this);
	}
	
	private ZWSoldHomesModifySearchForm modifySearchForm;
	
	@FindBy(xpath="//h2[contains(text(),'Sold Homes')]")
	WebElement soldHomes_heading;
	
	@FindBy(xpath="//button[text()='Modify Search' and not(contains(@class,'modify-search-mobile'))]")
	WebElement modify_search_button;

	public ZWSoldHomesModifySearchForm getModifySearchForm() {
		return modifySearchForm;
	}

	public void setModifySearchForm() {
		this.modifySearchForm = new ZWSoldHomesModifySearchForm(driver);
	}

	public boolean isSoldHomesPageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, soldHomes_heading, 30);
	}
	public boolean clickOnModifySearchButton() {
		return ActionHelper.Click(driver, modify_search_button);
	}
}
