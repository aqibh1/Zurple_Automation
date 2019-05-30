package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ContactMePage extends Page{
	
	@FindBy(xpath="//h1[@class='entry-title title_prop' and contains(text(),'Contact Me')]")
	WebElement pageTitle;
	
	public ContactMePage(WebDriver pWebDriver) {
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isContactMePage() {
//		boolean result = false;
//		if(ActionHelper.waitForElementToBeLocated(driver, "//h1[@class='entry-title title_prop']", 10)) {
//			result = FrameworkConstants.ContactMe.contains(driver.findElement(By.xpath("//h1[@class='entry-title title_prop']")).getText())?true:false;
//		}
		//ActionHelper.getText(driver, pageTitle).equalsIgnoreCase(FrameworkConstants.ContactMe)?true:false;
		return ActionHelper.waitForElementToBeLocated(driver, "//h1[@class='entry-title title_prop' and contains(text(),'Contact')]", 20);
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
