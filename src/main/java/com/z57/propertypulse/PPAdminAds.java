/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAdminAds extends Page{
	
	@FindBy(xpath="//table[@id='ads_list']/descendant::tr")
	WebElement table_enteries;
	
	@FindBy(id="edit_ad_state")
	WebElement edit_ad_state;
	
	@FindBy(id="edit_ad_submit")
	WebElement save_button;
	
	public PPAdminAds() {
		
	}
	public PPAdminAds(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean disableAllAds() {
		ActionHelper.staticWait(10);
		boolean isSuccess= false;
		List<WebElement> list_of_all_enteries = ActionHelper.getListOfElementByXpath(driver, "//table[@id='ads_list']/descendant::tr");
		
		for(int i = 0 ; i<list_of_all_enteries.size();i++) {
			List<WebElement> list_of_all = ActionHelper.getListOfElementByXpath(driver, "//table[@id='ads_list']/descendant::tr");
			
			
			List<WebElement> list_of_edit_buttons = list_of_all.get(1).findElements(By.tagName("a"));
			
			for(int j =0;j<list_of_edit_buttons.size();j++) {
				if(ActionHelper.getText(driver, list_of_edit_buttons.get(j)).equalsIgnoreCase("Edit")) {
					ActionHelper.Click(driver, list_of_edit_buttons.get(j));
					break;
				}
			}
			
			ActionHelper.waitForElementToBeVisible(driver, edit_ad_state, 30);
			ActionHelper.selectDropDownOption(driver, edit_ad_state, "", "Cancel Ad (run until end of billing cycle)");
			ActionHelper.Click(driver, save_button);
			
			isSuccess = ActionHelper.waitForElementToBeDisappeared(driver, save_button);
			ActionHelper.staticWait(10);
			

		}
		return isSuccess;
	}

}
