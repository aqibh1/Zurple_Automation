/**
 * 
 */
package resources.forms.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOPreviewForm extends AbstractForm{
	
	@FindBy(id="preview_button")
	WebElement preview_button;
	
	@FindBy(id="preview-template")
	WebElement preview_template_button;
	
	@FindBy(xpath="//div[@id='preview']/descendant::h1[text()='New Home on the Market']")
	WebElement newHomeOnMarket_preview_heading;
	
	@FindBy(xpath="//div[@aria-describedby='preview_box']/descendant::button[@title='close' and @aria-disabled='false']")
	WebElement closePreview_button;
	
	String preview_subject = "//div[@id='preview']/descendant::h4[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	@FindBy(id="attachment_label")
	WebElement attachment_label;
	
	@FindBy(xpath="//div[@id='preview']/descendant::div[@style]")
	WebElement body_preview;
	
	@FindBy(xpath="//legend[contains(text(),'Preview')]")
	WebElement preview_heading;
	
	String image_preview = "//img[@src='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	public ZBOPreviewForm() {
		// TODO Auto-generated constructor stub
	}
	public ZBOPreviewForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean clickOnPreviewButton() {
		return ActionHelper.Click(driver, preview_button);
	}
	public boolean isPreviewHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, newHomeOnMarket_preview_heading, 30);
	}
	public boolean closePreviewWindow() {
		return ActionHelper.Click(driver, closePreview_button);
	}
	//If label text is expected pass 'TRUE' 
	public boolean getAttachmentLabel(boolean pExpectedValue) {
		if(pExpectedValue) {
			return !ActionHelper.getText(driver, attachment_label).isEmpty();
		}else {
			return ActionHelper.getText(driver, attachment_label).isEmpty();
		}
	}
	public void verificationOfPreviewContent(String pSubject, String pBody, boolean pAttchmentLabel, String pImageUrl, String pPlaceHolderValue) {
		ActionHelper.staticWait(5);
		assertTrue(getAttachmentLabel(pAttchmentLabel), "Unable to verify Attachment label in preview window..");
		assertTrue(ActionHelper.isElementVisible(driver,ActionHelper.getDynamicElement(driver, preview_subject, pSubject)),"Subject is not visible in preview");
		assertTrue(ActionHelper.isElementVisible(driver,ActionHelper.getDynamicElement(driver, image_preview, pImageUrl)),"Image preview is not visible ..");
		assertTrue(ActionHelper.getText(driver, body_preview).contains(pBody), "Unable to verify body content..");
		assertTrue(ActionHelper.getText(driver, body_preview).contains(pPlaceHolderValue), "Unable to verify place holder value..");
		
	}
	public boolean clickOnPreviewTemplateButton() {
		return ActionHelper.Click(driver, preview_template_button);
	}
	public boolean isPreviewHeadingVisibleTemplate() {
		return ActionHelper.waitForElementToBeVisible(driver, preview_heading, 30);
	}
}
