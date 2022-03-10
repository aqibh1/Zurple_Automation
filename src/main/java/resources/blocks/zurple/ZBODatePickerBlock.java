/**
 * 
 */
package resources.blocks.zurple;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.blocks.AbstractBlock;
import resources.utility.ActionHelper;

/**
 * @author darrraqi
 *
 */
public class ZBODatePickerBlock extends AbstractBlock{
	
	@FindBy(id="ui-datepicker-div")
	WebElement datepicker_div;
	
	@FindBy(xpath="//dd[@class='ui_tpicker_timezone']/span")
	WebElement time_zone;
	
	@FindBy(xpath="//*[@id='ui-datepicker-div']/descendant::button[text()='Now']")
	WebElement now_button;
	
	@FindBy(xpath="//*[@id='ui-datepicker-div']/descendant::button[text()='Done']")
	WebElement done_button;

	public ZBODatePickerBlock(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);	
	}
	
	public boolean isDatePickerVisible() {
		return ActionHelper.isElementVisible(driver, datepicker_div);
	}
	public String getTimeZone() {
		return ActionHelper.getText(driver, time_zone);
	}
	public boolean clickOnNowButton() {
		return ActionHelper.Click(driver, now_button);
	}
	public boolean clickOnDoneButton() {
		return ActionHelper.Click(driver, done_button);
	}
	
}
