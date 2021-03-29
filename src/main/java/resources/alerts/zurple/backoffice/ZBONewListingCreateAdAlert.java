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
import resources.utility.AutomationLogger;

/**
 * @author darrraqi
 *
 */
public class ZBONewListingCreateAdAlert extends AbstractAlert{
	
	@FindBy(xpath="//h2/strong[text()=' Reach More Prospects']")
	WebElement reach_more_prospects_heading;
	
	@FindBy(xpath="//div[@class='modal-header']/button[@aria-label='Close']")
	WebElement close_button;
	
	public ZBONewListingCreateAdAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean closeCreateAdModal() {
		boolean isClosed = false;
		if(ActionHelper.isElementVisible(driver, reach_more_prospects_heading)) {
			isClosed = ActionHelper.Click(driver, close_button);
		}else {
			AutomationLogger.info("Create Ad Alert is not visible..");
			isClosed = true;
		}
		return isClosed;
	}
}
