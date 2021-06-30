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
	
	public ZBOLeadListForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isLeadListForm() {
		return ActionHelper.waitForElementToBeVisible(driver, lead_list_heading, 30);
	}
	public int getLeadsListCount() {
		String[] list_count = ActionHelper.getText(driver, lead_list_count).split(" ");
		return Integer.parseInt(list_count[list_count.length-1]);
	}
	public boolean clickOnCancelButton() {
		return ActionHelper.Click(driver, cancel);
	}
}
