/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPForceExecuteSchedulePost extends Page{
	
	String result_xpath = "//h2[text()='EXECUTING SCHEDULE']/following::div[contains(text(),'["+FrameworkConstants.DYNAMIC_VARIABLE+"] =>')]";
	
	@FindBy(xpath="//h2[text()='EXECUTING SCHEDULE']")
	WebElement executingSchedule;
	
//	private actionHelper actionHelper;
	public PPForceExecuteSchedulePost() {
		// TODO Auto-generated constructor stub
	}
	public PPForceExecuteSchedulePost(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
	}
	
	public boolean isExecutingSchedulePage() {
		return actionHelper.waitForElementToBeVisible(executingSchedule, 30);
	}
	public String getResultMessage() {
		String resultMessage = actionHelper.getText(actionHelper.getDynamicElement(result_xpath, "result"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
	
	public String getStatus() {
		String resultMessage = actionHelper.getText(actionHelper.getDynamicElement(result_xpath, "status"));
		resultMessage = resultMessage.split("=>")[1].trim();
		return resultMessage;
	}
}
