/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.WebElement;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAdsOverviewPage extends Page{
	
	String adOverviewPageTitle = "//h2[@class='z57-theme-page-topic' and text()='Paid Ads']";
	String tableDataContent = "//table[@id='campaign_table']/descendant::td";
	
	public boolean isAdsOverviewPage() {
		return ActionHelper.waitForElementToBeLocated(driver, adOverviewPageTitle, 60);
	}
	
	public boolean isAdPlacedSuccessfully(String pListingTitle) {
		boolean isFound = false;
		List<WebElement> list_of_table_contents = ActionHelper.getListOfElementByXpath(driver, tableDataContent);
		for(WebElement element: list_of_table_contents) {
			if(element.getText().trim().contains(pListingTitle)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
}
