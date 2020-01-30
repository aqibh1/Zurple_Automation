/**
 * 
 */
package resources.forms.zurple.backoffice;

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
	
	@FindBy(id="cke_50_textInput")
	WebElement url_input;
	
	@FindBy(id="cke_89_label")
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
}
