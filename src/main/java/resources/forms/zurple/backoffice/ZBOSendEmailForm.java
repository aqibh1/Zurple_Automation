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
	private int generateRandomInt(int pUpperRange){
    	Random random = new Random();
    	int lNum = random.nextInt(pUpperRange);
    	if(lNum==pUpperRange) {
    		lNum = lNum - 1;
    	}
    	return lNum;
}
}
