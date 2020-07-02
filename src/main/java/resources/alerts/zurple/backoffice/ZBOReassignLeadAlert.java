/**
 * 
 */
package resources.alerts.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.AbstractAlert;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOReassignLeadAlert extends AbstractAlert{
	
	@FindBy(xpath="//h4[text()='Reassign Leads']")
	WebElement reassign_leads_heading;
	
	@FindBy(xpath="//select[@name='admin_id']")
	WebElement leads_dropdown;
	
	@FindBy(xpath="//button[@id='reassign_leads']")
	WebElement reassign_leads_button;
	
	public ZBOReassignLeadAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isReassignAlert() {
		return ActionHelper.waitForElementToBeVisible(driver, reassign_leads_heading, 30);
	}
	public boolean clickAndSelectAgent(String pAgentName) {
		return ActionHelper.selectDropDownOption(driver, leads_dropdown, "", pAgentName);
	}
	public boolean clickOnReassignLeadButton() {
		return ActionHelper.Click(driver, reassign_leads_button);
	}

}
