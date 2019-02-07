package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class RecentHomeSalesPage extends Page{
	
	@FindBy(xpath="//div[@id='googleMap']")
	WebElement googleMap;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	@FindBy(xpath="//span[@id='ic_results_container']")
	WebElement results_span;
	
	@FindBy(xpath="//a[@id='ic_print_results_button']")
	WebElement print_result_button;
	
	public RecentHomeSalesPage(WebDriver pWebDriver) {
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isGoogleMapDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, googleMap, 20);
	}
	
	public boolean isRecentHomeSalesPage() {
		boolean result = ActionHelper.getText(driver, pageTitle).equalsIgnoreCase(FrameworkConstants.RecentHomeSalesPageTitle)?true:false;
		return result;
	}

	public boolean isSearchSuccessful() {
		return ActionHelper.isElementVisible(driver, print_result_button);
		
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
