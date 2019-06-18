/**
 * 
 */
package resources.forms.pp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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
public class PPUploadImagesForm extends AbstractForm{
	private ActionHelper actionHelper;
	String lUploadImagesHeader = "//h3[@id='myModalLabel']";

	@FindBy(id="uploader_browse")
	WebElement addFilesButton;
	
	public PPUploadImagesForm() {
		// TODO Auto-generated constructor stub
	}
	public PPUploadImagesForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	public boolean isUploadImagesForm() {
		return actionHelper.waitForElementToBeLocated(lUploadImagesHeader, 15);
	}

	public boolean clickOnAddFilesButton() {
		return actionHelper.Click(addFilesButton);
	}

	public boolean uploadImage(String pPath) throws AWTException {
		uploadImageFile(pPath);
		
		return actionHelper.waitForElementToBeDisappeared(addFilesButton);
	}
	
	public void uploadFacebookImage(String pPath) throws AWTException {
		uploadImageFile(pPath);
	}

	private void uploadImageFile(String path)
			throws AWTException
	{
		setClipboard(path);
		Robot robot = new Robot();
		// Ctrl-V + Enter on Win
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);

	}

}
