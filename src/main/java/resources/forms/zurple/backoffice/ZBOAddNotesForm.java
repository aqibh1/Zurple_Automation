/**
 * 
 */
package resources.forms.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOAddNotesForm extends AbstractForm{
	@FindBy(xpath="//div[@id='add-note-lead-modal']/descendant::h4[text()='Add Note']")
	WebElement addNote_heading;
	
	@FindBy(id="text_area_note")
	WebElement comment_input;
	
	@FindBy(id="submit_save_note")
	WebElement save_button;
	
	String existing_crm_xpath="//div[@id='z-lead-notes']/descendant::div[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String existing_crm_date_xpath="//div[@id='z-lead-notes']/descendant::div[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
 
	@FindBy(xpath="//div[@id='z-lead-notes']/descendant::a[text()='Delete']")
	WebElement delete_button;
	
	@FindBy(xpath="//div[@id='z-lead-notes']/descendant::a[text()='Delete']")
	WebElement edit_button;	
	
	private ZBOSucessAlert successAlert;
	
	public ZBOAddNotesForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSuccessAlert();
		PageFactory.initElements(driver, this);
	}
	public ZBOSucessAlert getSuccessAlert() {
		return successAlert;
	}
	public void setSuccessAlert() {
		this.successAlert = new ZBOSucessAlert(driver);
	}
	public boolean isAddNoteForm() {
		return ActionHelper.waitForElementToBeVisible(driver, addNote_heading, 30);
	}
	public boolean typeComment(String pComment) {
		return ActionHelper.ClearAndType(driver, comment_input, pComment);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
	public boolean isCommentAddedSuccessfully(String pComment) {
		ActionHelper.staticWait(15);
		return ActionHelper.waitForElementToBeVisible(driver, ActionHelper.getDynamicElement(driver, existing_crm_xpath, pComment), 30);
	}
	public boolean isDateCorrect(String pDate) {
		ActionHelper.staticWait(15);
		return ActionHelper.waitForElementToBeVisible(driver, ActionHelper.getDynamicElement(driver, existing_crm_date_xpath, pDate), 30);
	}
	public boolean clickOnDeleteButton() {
		return ActionHelper.Click(driver, delete_button);
	}
	public boolean clickOnEditButton() {
		return ActionHelper.Click(driver, edit_button);
	}
	public boolean confirmDeleteAlert() {
		boolean isDeleted = false;
		if(ActionHelper.isAlertPresent(driver)) {
			isDeleted = ActionHelper.handleDisableAdAlert(driver);
		}
		return isDeleted;
	}
}
