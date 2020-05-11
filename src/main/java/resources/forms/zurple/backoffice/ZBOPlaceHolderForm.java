/**
 * 
 */
package resources.forms.zurple.backoffice;

import java.util.List;

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
public class ZBOPlaceHolderForm extends AbstractForm{
	
	@FindBy(xpath="//h4[text()='Placeholders']")
	WebElement placeholder_heading;
	
	String list_all_placeholder = "//div[@class='modal-content']/descendant::td";
	
	@FindBy(xpath="//button[text()='Close']")
	WebElement close_button;

	public ZBOPlaceHolderForm() {
		
	}
	public ZBOPlaceHolderForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		
	}
	public boolean isPlaceHolderFromVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, placeholder_heading, 15);
	}
	public boolean countAllThePlaceHolders() {
		List<WebElement> list_of_placeholders = ActionHelper.getListOfElementByXpath(driver, list_all_placeholder);
		return list_of_placeholders.size()>0;
	}
	public boolean closePlaceHolder() {
		return ActionHelper.Click(driver, close_button);
	}
}
