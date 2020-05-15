/**
 * 
 */
package resources.forms.zurple.website;

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
public class ZWSoldHomesModifySearchForm extends AbstractForm{
	
	@FindBy(id="myModalLabel")
	WebElement searchForm_heading;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement search_button;
	
	public ZWSoldHomesModifySearchForm() {
		
	}
	public ZWSoldHomesModifySearchForm(WebDriver pWebdriver) {
		driver = pWebdriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSearchFormVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, searchForm_heading, 20);
	}
	public boolean clickOnSearchButton() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, search_button)) {
			isSuccess = ActionHelper.waitForElementToBeDisappeared(driver, search_button, 30);
		}
		return isSuccess;
	}
}
