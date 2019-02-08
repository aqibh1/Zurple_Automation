package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class WhatsNearbyPage extends Page{


	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	
	public WhatsNearbyPage(WebDriver pWebDriver) {
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPointOfIntrestsPage() {
		boolean result = ActionHelper.getText(driver, pageTitle).equalsIgnoreCase(FrameworkConstants.PointOfIntrestsPageTitle)?true:false;
		return result;
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