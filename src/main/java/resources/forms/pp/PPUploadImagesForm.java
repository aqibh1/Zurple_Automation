/**
 * 
 */
package resources.forms.pp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
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

	String lUploadImagesHeader = "//h3[@id='myModalLabel']";

	@FindBy(id="uploader_browse")
	WebElement addFilesButton;
	
	public PPUploadImagesForm() {
		// TODO Auto-generated constructor stub
	}
	public PPUploadImagesForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isUploadImagesForm() {
		return ActionHelper.waitForElementToBeLocated(driver, lUploadImagesHeader, 15);
	}

	public boolean clickOnAddFilesButton() {
		return ActionHelper.Click(driver, addFilesButton);
	}

	public boolean uploadImage(String pPath) throws AWTException {
		uploadImageFile(pPath);
		
		return ActionHelper.waitForElementToBeDisappeared(driver, addFilesButton);
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