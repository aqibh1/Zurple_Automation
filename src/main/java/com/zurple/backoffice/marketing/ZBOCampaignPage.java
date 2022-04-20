/**
 * 
 */
package com.zurple.backoffice.marketing;

import java.util.ArrayList;
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
	
	@FindBy(xpath="//select[@name='campaigns_table_length']")
	WebElement number_of_records;
	
	@FindBy(xpath="//li[@id='campaigns_table_next']/a")
	WebElement next_button;
	
	@FindBy(xpath="//li[@class='paginate_button next disabled']/a")
	WebElement next_disabled_button;
	
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
		boolean isClickable = false;
		ActionHelper.selectDropDownOption(driver, number_of_records, "", "100");
		do {
			isClickable = false;
			List<WebElement> list_of_data = ActionHelper.getListOfElementByXpath(driver, "//table[@id='campaigns_table']/descendant::tbody/tr[@class]");
			for(WebElement row_data: list_of_data) {
				//			row_data.findElement(By.xpath("td/a[not(contains(@class,'z-btn-inactive'))]"));
				WebElement campaign_name = row_data.findElement(By.xpath("td/a[not(contains(@class,'z-btn-inactive'))]"));
				if(ActionHelper.getText(driver, campaign_name).equalsIgnoreCase(pCampaignName)) {
					WebElement lead_count = row_data.findElement(By.xpath("td[@class=' view_recipients_button']"));
					if(ActionHelper.getText(driver, lead_count).equalsIgnoreCase("1 Lead")) {
						isSuccess = true;
						break;
					}else {
						break;
					}
				}
			}
			if(!ActionHelper.isElementVisible(driver, next_disabled_button)) {
				isClickable = ActionHelper.Click(driver, next_button);
			}
		}while(!isSuccess && isClickable);
		return isSuccess;

	}
	public boolean deleteAllAutoCampaigns(String pCampaignName) {
		boolean isSuccess = false;
		boolean isClickable = false;
		List<String> list_of_campaigns_ids = new ArrayList<String>();
		ActionHelper.selectDropDownOption(driver, number_of_records, "", "100");
		do {
			isClickable = false;
			List<WebElement> list_of_data = ActionHelper.getListOfElementByXpath(driver, "//table[@id='campaigns_table']/descendant::tbody/tr[@class]");
			for(WebElement row_data: list_of_data) {
				//			row_data.findElement(By.xpath("td/a[not(contains(@class,'z-btn-inactive'))]"));
				WebElement campaign_name = row_data.findElement(By.xpath("td/a[not(contains(@class,'z-btn-inactive'))]"));
				
				if(ActionHelper.getText(driver, campaign_name).contains(pCampaignName)) {
					WebElement campaign_ids = row_data.findElement(By.xpath("td/a"));
					String campaignIds = ActionHelper.getAttribute(campaign_ids, "href");
//					String campaign_id = campaignIds.split("/campaigns")[1];
					list_of_campaigns_ids.add(campaignIds);
				}
			}
			if(!ActionHelper.isElementVisible(driver, next_disabled_button)) {
				isClickable = ActionHelper.Click(driver, next_button);
			}
		}while(isClickable);
		
		for(String ids: list_of_campaigns_ids) {
//			String l_current_url = driver.getCurrentUrl();
			driver.navigate().to(ids);
			deleteCampaign();
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
