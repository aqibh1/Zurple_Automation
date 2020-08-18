package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

public class ZWSiteMapPage extends Page{

	@FindBy(className="html-attribute-name")
	WebElement tag_name; 
	
	@FindBy(className="html-attribute-value")
	WebElement tag_value; 
	
	public ZWSiteMapPage() {
		
	}
	
	public ZWSiteMapPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTagName(String sitemap) {
		return ActionHelper.getText(driver, tag_name);
	}
	
	public String getTagValue(String sitemap) {
		return ActionHelper.getText(driver, tag_value);
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
