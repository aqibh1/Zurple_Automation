/**
 * 
 */
package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class UserCustomPage extends Page{

	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	@FindBy(xpath="//button[text()='COLOR OPTIONS']")
	WebElement colorOptions_button;
	
	@FindBy(xpath="//button[text()='IMAGE OPTIONS']")
	WebElement imageOptions_button;

	private ActionHelper actionHelper;
	
	public UserCustomPage() {
		// TODO Auto-generated constructor stub
	}
	UserCustomPage(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	
	public boolean isPageTitleDisplayed() {
		boolean status = false;
		if(actionHelper.isElementVisible(pageTitle)) {
			status = actionHelper.getText(pageTitle).equalsIgnoreCase(FrameworkConstants.BrandingOptions);
		}
		return status;
	}
	public boolean isColorOptionsDisplayed() {
		return actionHelper.isElementVisible(colorOptions_button);
	}
	public boolean isImageOptionsDisplayed() {
		return actionHelper.isElementVisible(imageOptions_button);
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
