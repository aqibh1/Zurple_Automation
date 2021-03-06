/**
 * 
 */
package com.z57.site.v1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.z57.v1.SaveSearchForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class HomeSearchPage extends Page{
	
	private SaveSearchForm saveSearchForm;

	@FindBy(xpath="//a[@class='save_search_button']")
	WebElement saveSearch_button;
	
	@FindBy(xpath="//h1[@class='page-heading']")
	WebElement page_title;
	
	@FindBy(id="user-account-button")
	WebElement myAccount_button;
	
	@FindBy(id="register-modal-button")
	WebElement signin_button;
	
	public HomeSearchPage() {
		
	}
	public HomeSearchPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSaveSearchForm();
		PageFactory.initElements(driver, this);
	}
	
	public SaveSearchForm getSaveSearchForm() {
		return saveSearchForm;
	}
	public void setSaveSearchForm() {
		this.saveSearchForm = new SaveSearchForm(driver);
	}
	
	public boolean clickOnSaveSearch() {
		return ActionHelper.Click(driver, saveSearch_button);
	}
	
	public boolean isHomeSearchPage() {
		return ActionHelper.getText(driver, page_title).contains(FrameworkConstants.HomeSearchPageTitle);
	}
	
	public boolean isLeadLoggedIn() {
		return ActionHelper.waitForElementToBeVisible(driver, myAccount_button, 5);
	}
	public boolean clickOnSignInButton() {
		return ActionHelper.Click(driver, signin_button);
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
