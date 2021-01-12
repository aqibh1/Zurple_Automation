/**
 * 
 */
package resources.forms.zurple.backoffice;

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
public class ZBOAddTemplateForm extends AbstractForm{
	
	@FindBy(xpath="//h2[text()='Add Template']")
	WebElement addTemplate_heading;
	
	@FindBy(id="templates-select")
	WebElement template_dropdown;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateButton;
	
	public ZBOAddTemplateForm() {
		
	}
	public ZBOAddTemplateForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isCampaignAddTemplateFormVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, addTemplate_heading, 15);
	}
	public boolean isTemplateExist(String pTemplateName) {
		return ActionHelper.selectDropDownOption(driver, template_dropdown, "", pTemplateName);
	}
	public boolean clickOnUpdateButton() {
		return ActionHelper.Click(driver, updateButton);
	}
	
}
