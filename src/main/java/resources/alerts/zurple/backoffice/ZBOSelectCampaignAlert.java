/**
 * 
 */
package resources.alerts.zurple.backoffice;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.AbstractAlert;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOSelectCampaignAlert extends AbstractAlert{
	@FindBy(xpath="//button[text()='Enroll']")
	WebElement Enroll_button;
	
	@FindBy(xpath="//h2[text()='Enroll in Campaign']")
	WebElement enroll_in_campaign_headings;
	
	@FindBy(xpath="//select[@id='campaign']")
	WebElement select_listing_dropdown;
	
	String listing_dropdown_options = "//select[@id='campaign']/option";
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement ok_button;
	
	public ZBOSelectCampaignAlert() {
		
	}
	public ZBOSelectCampaignAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSelectCampaignAlert() {
		return ActionHelper.waitForElementToBeVisible(driver, enroll_in_campaign_headings, 30);
	}
	public boolean selectCampaignFromDropdown(String pCampaignName) {
		return ActionHelper.selectDropDownOption(driver, select_listing_dropdown, listing_dropdown_options, pCampaignName);
	}
	public boolean clickOnOkButton() {
		return ActionHelper.Click(driver, ok_button);
	}
	public boolean clickOnEnrollButton() {
		return ActionHelper.Click(driver, Enroll_button);
	}
	public List<WebElement> clickOnCampaignDropdownAndFetchCampaigns() {
		List<WebElement> list = new ArrayList<WebElement>();
		if(ActionHelper.Click(driver, select_listing_dropdown)) {
			list = ActionHelper.getListOfElementByXpath(driver, listing_dropdown_options);
		}
		return list;
	}
	public boolean clickOnCmapiagnName() {
		boolean isClicked = false;
		List<WebElement> list = new ArrayList<WebElement>();
		String l_campaign_name = "";
		if(ActionHelper.Click(driver, select_listing_dropdown)) {
			list = ActionHelper.getListOfElementByXpath(driver, listing_dropdown_options);
			l_campaign_name = ActionHelper.getText(driver, list.get(1));
			isClicked = ActionHelper.Click(driver, list.get(1));
		}
		return isClicked;
	}
	public boolean clickOnCmapiagnName(String pCampaignName) {
		boolean isClicked = false;
		List<WebElement> list = new ArrayList<WebElement>();
		String l_campaign_name = "";
		if(ActionHelper.Click(driver, select_listing_dropdown)) {
			list = ActionHelper.getListOfElementByXpath(driver, listing_dropdown_options);
			for(WebElement element: list) {
				l_campaign_name = ActionHelper.getText(driver, element);
				if(l_campaign_name.equalsIgnoreCase(pCampaignName)) {
					isClicked = ActionHelper.Click(driver, element);
				}
			}	
		}
		return isClicked;
	}
}
