/**
 * 
 */
package com.zurple.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author darrraqi
 *
 */
public class ZAAdsManagerPage extends Page{
	
	@FindBy(xpath="//div[@id='main_body']/descendant::h1[text()='Admin Ads Manager']")
	WebElement ads_manager_heading;
	
	@FindBy(id="admin")
	WebElement admin;
	
	@FindBy(id="ad_id")
	WebElement ad_id;
	
	@FindBy(id="submit")
	WebElement submit;
	
	@FindBy(xpath="//span[@class='fb_status' and not(contains(@id,'carousel'))]")
	WebElement ad_status;
	
	@FindBy(xpath="//table[@id='adsmgrtable']/descendant::td[contains(text(),'Client Fee')]")
	WebElement client_fee;
	
	@FindBy(xpath="//table[@id='adsmgrtable']/descendant::td[@class='sorting_1']/following-sibling::td[1]")
	WebElement ending_date;
	
	@FindBy(xpath="//table[@id='adsmgrtable']/descendant::td[@class='sorting_1']")
	WebElement starting_date;
	
	@FindBy(xpath="//table[@id='adsmgrtable']/descendant::td[@class='location']")
	WebElement ad_location;
	
	public ZAAdsManagerPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isAdminAdsManagerPage() {
		return ActionHelper.waitForElementToBeVisible(driver, ads_manager_heading, 10);
	}
	public boolean typeAdminId(String pAdminId) {
		return ActionHelper.ClearAndType(driver, admin, pAdminId);
	}
	public boolean typeAdId(String pAdId) {
		return ActionHelper.ClearAndType(driver, ad_id, pAdId);
	}
	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, submit);
	}
	public String getAdStatus() {
		return ActionHelper.getText(driver, ad_status);
	}
	public String getAdStartingDate() {
		return ActionHelper.getText(driver, starting_date);
	}
	public String getAdEndingDate() {
		return ActionHelper.getText(driver, ending_date);
	}
	public String getClientFee() {
		return ActionHelper.getText(driver, client_fee);
	}
	public String getAdLocation() {
		return ActionHelper.getText(driver, ad_location);
	}
	
}
