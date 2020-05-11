/**
 * 
 */
package resources.forms.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.awt.print.PageFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 * Insert image forms on mass email page
 * for standard emails
 */
public class ZBOInsertImageForm extends AbstractForm{

	@FindBy(xpath="//div[@class='cke_dialog_body']/descendant::div[text()='Image Properties']")
	WebElement image_prop_heading;
	
	@FindBy(xpath="//span[text()='Browse Images']")
	WebElement browseImages_button;
	
	@FindBy(xpath="//div[@class='cke_dialog_ui_input_text']/input[@aria-required='true']")
	WebElement url_input;
	
	@FindBy(xpath="//span[text()='OK']")
	WebElement ok_button;
	
	String image_xpath= "//img[@src='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	public ZBOInsertImageForm() {
		
	}
	public ZBOInsertImageForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isImageFormVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, image_prop_heading, 30);
	}
	public boolean typeImageUrl(String pUrl) {
		return ActionHelper.Type(driver, url_input, pUrl);
	}
	public boolean clickOkButton() {
		return ActionHelper.Click(driver, ok_button);
	}
	public void insertImage(String pImageUrl) {
		assertTrue(isImageFormVisible(), "Image form is not visible..");
		assertTrue(typeImageUrl(pImageUrl), "Unable to type image url..");
		assertTrue(clickOkButton(), "Unable to click on Ok button..");
	}
}
