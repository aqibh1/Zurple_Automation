/**
 * 
 */
package resources.forms.zurple.backoffice;

import java.util.List;
import java.util.Random;

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
public class ZBOSendEmailForm extends AbstractForm{
	
	@FindBy(xpath="//h4[text()='Send Email']")
	WebElement send_email_heading;
	
	@FindBy(id="campaign_template")
	WebElement select_campaign_dropdown;
	
	String select_template_option = "//select[@id='campaign_template']/option";
	
	@FindBy(id="send_email_button")
	WebElement send_email_button;
	
	@FindBy(id="subject")
	WebElement subject;
	
	@FindBy(id="toemail")
	WebElement toemail_input;
	
	@FindBy(id="mls_id")
	WebElement mls_id_input;
	
	@FindBy(id="find_listing_button")
	WebElement find_listing_button;
	
	@FindBy(xpath="//div[@id='listing_template_block']/descendant::h1[text()='New Home on the Market']")
	WebElement heading_new_home;
	
	@FindBy(id="email-send-alert")
	WebElement email_sent_alert;
	
	@FindBy(id="send_email_listing_flyer")
	WebElement send_email_listing_flyer;
	
	public ZBOSendEmailForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSendEmailForm() {
		return ActionHelper.waitForElementToBeVisible(driver, send_email_heading, 30);
	}
	public boolean selectRandomTemplate() {
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, select_template_option);
		int l_random_index = generateRandomInt(list_element.size());
		l_random_index = l_random_index==0?l_random_index+1:l_random_index;
		return ActionHelper.clickAndSelectByIndex(driver, select_campaign_dropdown, select_template_option, l_random_index);
	}
	public boolean selectTemplate(String pTemplateName) {
		int l_random_index = 0;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, select_template_option);
		for(int i=0;i<list_element.size();i++) {
			if(ActionHelper.getText(driver, list_element.get(i)).contains(pTemplateName)) {
				l_random_index = i;
				break;
			}
		}
		l_random_index = l_random_index==0?l_random_index+1:l_random_index;
		return ActionHelper.clickAndSelectByIndex(driver, select_campaign_dropdown, select_template_option, l_random_index);
	}
	
	public boolean clickOnSendEmailButton() {
		return ActionHelper.Click(driver, send_email_button);
	}
	public String getSubject() {
		return ActionHelper.getTextByValue(driver, ActionHelper.getElementByXpath(driver, "//input[@id='subject']"));
	}
	public String getToEamil() {
		return ActionHelper.getValue(driver, toemail_input);
	}
	public boolean typeAndSearchListingByMLS(String pMLSID) {
		boolean isSuccessful = false;
		if(ActionHelper.ClearAndType(driver, mls_id_input, pMLSID)) {
			isSuccessful = ActionHelper.Click(driver, find_listing_button);
		}
		return isSuccessful;
	}
	public boolean isListingHeadingFetched() {
		return ActionHelper.waitForElementToBeVisible(driver, heading_new_home,20);
	}
	public boolean typeEmailListingSubject(String pSubject) {
		return ActionHelper.Type(driver,subject, pSubject);
	}
	public boolean isSuccessMessageDisplayed() {
		return ActionHelper.getText(driver, email_sent_alert).equalsIgnoreCase("Email will be sent in 5 minutes");
	}
	public boolean isEmailCannotBeSentErrorDisplayed() {
		return ActionHelper.getText(driver, email_sent_alert).contains("You cannot send an email to this lead because it is assigned to another agent.");
	}
	public boolean clickOnSendListingButton() {
		return ActionHelper.Click(driver, send_email_listing_flyer);
	}
	private int generateRandomInt(int pUpperRange){
    	Random random = new Random();
    	int lNum = random.nextInt(pUpperRange);
    	if(lNum==pUpperRange) {
    		lNum = lNum - 1;
    	}
    	return lNum;
}
}
