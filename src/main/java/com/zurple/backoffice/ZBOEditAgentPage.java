/**
 * 
 */
package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOEditAgentPage extends Page{
	
	@FindBy(id="delete-admin")
	WebElement delete_button;
	
	@FindBy(id="email")
	WebElement email_input;
	
	public ZBOEditAgentPage() {
		
	}
	public ZBOEditAgentPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isEditAgentPage() {
		return ActionHelper.waitForElementToBeVisible(driver, delete_button, 30);
	}
	public String getAgentEmail() {
		return ActionHelper.getTextByValue(driver, email_input);
	}
}
