/**
 * 
 */
package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class UserCustomPage extends Page{

	private PageHeader pageHeader;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	@FindBy(xpath="//button[text()='COLOR OPTIONS']")
	WebElement colorOptions_button;
	
	@FindBy(xpath="//button[text()='IMAGE OPTIONS']")
	WebElement imageOptions_button;
	
	public UserCustomPage() {
		// TODO Auto-generated constructor stub
	}
	UserCustomPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		pageHeader = new PageHeader(driver);
	}
	
	public boolean isPageTitleDisplayed() {
		boolean status = false;
		if(ActionHelper.isElementVisible(driver, pageTitle)) {
			status = ActionHelper.getText(driver, pageTitle).equalsIgnoreCase(FrameworkConstants.BrandingOptions);
		}
		return status;
	}
	public boolean isColorOptionsDisplayed() {
		return ActionHelper.isElementVisible(driver, colorOptions_button);
	}
	public boolean isImageOptionsDisplayed() {
		return ActionHelper.isElementVisible(driver, imageOptions_button);
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
