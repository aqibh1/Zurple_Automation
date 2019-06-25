/**
 * 
 */
package com.z57.propertypulse;

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
public class PPForceExecuteSchedulePost extends Page{
	
	String result_xpath = "//h2[text()='EXECUTING SCHEDULE']/following::div[contains(text(),'["+FrameworkConstants.DYNAMIC_VARIABLE+"] =>')]";
	
	@FindBy(xpath="//h2[text()='EXECUTING SCHEDULE']")
	WebElement executingSchedule;
	
//	private ActionHelper actionHelper;
	public PPForceExecuteSchedulePost() {
		// TODO Auto-generated constructor stub
	}
	public PPForceExecuteSchedulePost(WebDriver pWebDriver) {
		driver = pWebDriver;
//		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isExecutingSchedulePage() {
		return ActionHelper.waitForElementToBeVisible(driver, executingSchedule, 30);
	}
	public String getResultMessage() {
		String resultMessage = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, result_xpath, "result"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
	
	public String getStatus() {
		String resultMessage = ActionHelper.getText(driver,ActionHelper.getDynamicElement(driver, result_xpath, "status"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
}
