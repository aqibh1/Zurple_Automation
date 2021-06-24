/**
 * 
 */
package com.zurple.backoffice.marketing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOCampaignPage extends Page{
	
	@FindBy(xpath="//h3[text()='Campaign Manager']")
	WebElement campaign_manager_heading;
	
	@FindBy(id="campaign-create-button")
	WebElement create_button;
	
	String campaign_list = "//div[@id='campaigns_table_wrapper']/descendant::tr[@class='odd']/td";
//	String campaign_list = "odd";
	
	@FindBy(xpath="//label[text()='Delete Campaign']")
	WebElement deleteCampaign_button;
	
	public ZBOCampaignPage() {
		
	}
	public ZBOCampaignPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isCampaignPage() {
		return ActionHelper.waitForElementToBeVisible(driver, campaign_manager_heading, 15);
	}
	public boolean clickOnCreateCampaignButton() {
		return ActionHelper.Click(driver, create_button);
	}
	public boolean isLeadAddedInCampaign(String pCampaignName) {
		boolean isSuccess = false;
		List<WebElement> list_of_data = ActionHelper.getListOfElementByXpath(driver, "//table[@id='campaigns_table']/descendant::tbody/tr[@class]");
		for(WebElement row_data: list_of_data) {
//			row_data.findElement(By.xpath("td/a[not(contains(@class,'z-btn-inactive'))]"));
			WebElement campaign_name = row_data.findElement(By.xpath("td/a[not(contains(@class,'z-btn-inactive'))]"));
			if(ActionHelper.getText(driver, campaign_name).equalsIgnoreCase(pCampaignName)) {
				WebElement lead_count = row_data.findElement(By.xpath("td[@class=' view_recipients_button']"));
				if(ActionHelper.getText(driver, lead_count).equalsIgnoreCase("1 Lead")) {
					isSuccess = true;
				}else {
					break;
				}
			}
		}
		return isSuccess;
		
	}
	public boolean deleteCampaign() {
		boolean isDeleted = false;
		if(ActionHelper.Click(driver, deleteCampaign_button)) {
			isDeleted = new ZBOSucessAlert(driver).clickOnConfirmButton();
		}
		if(isDeleted) {
			ActionHelper.staticWait(3);
			isDeleted = driver.getCurrentUrl().contains("campaigns");
		}
		return isDeleted;
	}
	public boolean isCampaignDetailPage() {
		return ActionHelper.waitForElementToBeVisible(driver, deleteCampaign_button, 20);
	}
}
