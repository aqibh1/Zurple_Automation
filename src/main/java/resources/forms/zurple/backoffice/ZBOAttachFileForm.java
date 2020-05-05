/**
 * 
 */
package resources.forms.zurple.backoffice;

import static org.testng.Assert.assertTrue;

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
public class ZBOAttachFileForm extends AbstractForm{
	
	@FindBy(xpath="//div[@id='upload']/descendant::input[@name='upload[]']")
	WebElement upload_button;
	
	String all_file_xpath = "//div[@id='files']/descendant::div[@class='thumb']";
	
	public ZBOAttachFileForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isUploadFileFormVisible() {
		return true;//ActionHelper.waitForElementToBeVisible(driver, upload_button, 30);
	}
	
	public boolean clickAndSelectFile() {
		boolean isFileSelected = false;
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, all_file_xpath);
		if(list_of_elements.size()>0) {
			isFileSelected = ActionHelper.doubleClick(driver, list_of_elements.get(0));
		}
		return isFileSelected;
	}
	public void switchToBrowserToNewWindow() {
		ActionHelper.switchToSecondWindow(driver);
	}
	public void switchToOriginalWindow() {
		ActionHelper.switchToOriginalWindow(driver);
	}
	
	public void uploadAndVerifyFileIsAttached(String pFilePath) {
		if(pFilePath!=null && !pFilePath.isEmpty()) {
			switchToBrowserToNewWindow();
			assertTrue(isUploadFileFormVisible(), "Upload file form is not visible..");
			assertTrue(clickAndSelectFile(), "Unable to select the file from upload form ..");
			switchToOriginalWindow();
		}
	}
}
