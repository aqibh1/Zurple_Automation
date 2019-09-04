package resources.forms.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

public class ZWLeadCaptureForm extends AbstractForm{
	
	@FindBy(xpath="//h4[@class='modal-title' and text()='Register Below']")
	WebElement registerBelow_heading;
	
	public ZWLeadCaptureForm() {
		
	}
	
	public ZWLeadCaptureForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLeadCaptureFormIsVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, registerBelow_heading, 30);
	}
	

}
