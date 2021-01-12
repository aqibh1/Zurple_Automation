/**
 * 
 */
package resources.forms.pp;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

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
	
	public void uploadFacebookImage(String pPath) throws AWTException {
		uploadImageFile(pPath);
	}
	
	public boolean isUploadFormDisappeared() {
		return ActionHelper.waitForElementToBeDisappeared(driver, lUploadImagesHeader);
	}
//	private void uploadImageFile(String path)
//			throws AWTException
//	{
//		setClipboard(path);
//		Robot robot = new Robot();
//		// Ctrl-V + Enter on Win
//		robot.delay(3000);
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_ENTER);
//
//	}
	private void uploadImageFile(String path){
		try {
			ActionHelper.staticWait(3);
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\fileUpload.exe"+" "+path);
		}catch(Exception ex) {
			AutomationLogger.fatal("AutoIT error..Image upload failed..");
		}

	}

}
