/**
 * 
 */
package resources.forms.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOLeadListForm extends AbstractForm{
	
	@FindBy(xpath="//h4[text()='Lead List']")
	WebElement lead_list_heading;
	
	@FindBy(id="filtered-lead-list_info")
	WebElement lead_list_count;
	
	@FindBy(xpath="//span[text()='Cancel']")
	WebElement cancel;
	
	@FindBy(xpath="//h4[text()='Enrolled In Campaign']")
	WebElement enrolled_in_campain;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement save;
	
	public ZBOLeadListForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isLeadListForm() {
		return ActionHelper.waitForElementToBeVisible(driver, lead_list_heading, 30);
	}
	public int getLeadsListCount() {
		String[] list_count = ActionHelper.getText(driver, lead_list_count).split(" ");
		String lead_count = list_count[list_count.length-2];
		if(lead_count.contains(",")) {
			lead_count = list_count[list_count.length-2].replace(",", "");
		}
		return Integer.parseInt(lead_count);
	}
	public boolean clickOnCancelButton() {
		return ActionHelper.Click(driver, cancel);
	}
	public boolean isEnrolledInCampaignForm() {
		return ActionHelper.waitForElementToBeVisible(driver, enrolled_in_campain, 30);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save);
	}
}
