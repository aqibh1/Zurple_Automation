/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAdsOverviewPage extends Page{
	
	String adOverviewPageTitle = "//h2[@class='z57-theme-page-topic' and text()='Paid Ads']";
	String tableDataContent = "//table[@id='campaign_table']/descendant::td";
	private ActionHelper actionHelper;
	
	public PPAdsOverviewPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isAdsOverviewPage() {
		return actionHelper.waitForElementToBeLocated(adOverviewPageTitle, 60);
	}
	
	public boolean isAdPlacedSuccessfully(String pListingTitle) {
		int index = 0;
		boolean isFound = false;
		List<WebElement> list_of_table_contents = actionHelper.getListOfElementByXpath(tableDataContent);
		for(WebElement element: list_of_table_contents) {
			if(element.getText().trim().contains(pListingTitle)) {
//				element.findElement(By.xpath("/descendant::span[text()='Live (Paused)']"));
				isFound = waitForElementToVisibleAfterRegularIntervals(driver, "following-sibling::td/span[text()='Live (Paused)']", 100, 20,index);
				break;
			}
			index++;
		}
		return isFound;
	}
	private boolean waitForElementToVisibleAfterRegularIntervals(WebDriver pWebDriver, String pXpathToAppend, long pWaitIntervalInSeconds, int pTotalAttempts,int pIndex) {
		List<WebElement> list_of_table_contents;

		boolean displayed = false;
		int counter = 0;
		while (!displayed && counter<pTotalAttempts) {
			list_of_table_contents = actionHelper.getListOfElementByXpath(tableDataContent);
			try {
				WebElement elementFund = list_of_table_contents.get(pIndex).findElement(By.xpath(pXpathToAppend));
				if (elementFund!=null) {
					// Element is found so set the boolean as true
					displayed = actionHelper.isElementVisible(elementFund);
					break;
				} 
			}catch(Exception ex) {
				try {
					actionHelper.RefreshPage();
					Thread.sleep(pWaitIntervalInSeconds*1000);
					counter++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return displayed;

	}
}
