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
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class PPAdsOverviewPage extends Page{
	
	String adOverviewPageTitle = "//h2[@class='z57-theme-page-topic' and text()='Paid Ads']";
	String tableDataContent = "//table[@id='campaign_table']/descendant::td";
	String disable_button = "//table[@id='campaign_table']/descendant::td/button[text()='Disable Ad']";
	
	public PPAdsOverviewPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isAdsOverviewPage() {
		return ActionHelper.waitForElementToBeLocated(driver, adOverviewPageTitle, 60);
	}
	
	public boolean isAdPlacedSuccessfully(String pListingTitle) {
		int index = 0;
		boolean isFound = false;
		List<WebElement> list_of_table_contents = ActionHelper.getListOfElementByXpath(driver, tableDataContent);
		for(WebElement element: list_of_table_contents) {
			if(element.getText().trim().contains(pListingTitle)) {
//				element.findElement(By.xpath("/descendant::span[text()='Live (Paused)']"));
				isFound = waitForElementToVisibleAfterRegularIntervals(driver, "following-sibling::td/span[text()='Live (Paused)']","following-sibling::td/span[text()='FAILED']", 50, 40,index);
				break;
			}
			index++;
		}
		return isFound;
	}
	
	public boolean disableAllTheAds() {
		boolean isSuccess = false;
		List<WebElement> list_of_disable_button = ActionHelper.getListOfElementByXpath(driver, disable_button);
		for(WebElement disableButton: list_of_disable_button) {
			isSuccess = disableAd();
			if(!isSuccess) {
				AutomationLogger.error("Unable to disable the ad..");
				break;
			}
		}
		return isSuccess;
	}
	
	private boolean disableAd() {
		boolean isSuccess = false;
		List<WebElement> list_of_disable_button = ActionHelper.getListOfElementByXpath(driver, disable_button);
		if(list_of_disable_button.size()>0) {
			isSuccess = ActionHelper.Click(driver, list_of_disable_button.get(0));
			ActionHelper.staticWait(3);
			isSuccess = ActionHelper.handleDisableAdAlert(driver);
			ActionHelper.staticWait(10);
		}else {
			AutomationLogger.info("No ad to disable...");
			isSuccess = true;
		}
		return isSuccess;
	}
	private boolean waitForElementToVisibleAfterRegularIntervals(WebDriver pWebDriver, String pXpathToAppend,String pXpathToAppend2, long pWaitIntervalInSeconds, int pTotalAttempts,int pIndex) {
		List<WebElement> list_of_table_contents;
		pXpathToAppend2 = "following-sibling::td/span[text()='FAILED']";
		boolean displayed = false;
		int counter = 0;
		while (!displayed && counter<pTotalAttempts) {
			list_of_table_contents = ActionHelper.getListOfElementByXpath(driver, tableDataContent);
			try {
				List<WebElement> elementFundList = list_of_table_contents.get(pIndex).findElements(By.xpath(pXpathToAppend));
				List<WebElement> postingFailedList = list_of_table_contents.get(pIndex).findElements(By.xpath(pXpathToAppend2));
				if (elementFundList.size()>0) {
					// Element is found so set the boolean as true
					displayed = ActionHelper.isElementVisible(driver, elementFundList.get(0));
					break;
				} else if(postingFailedList.size()>0) {
					if(ActionHelper.isElementVisible(driver, postingFailedList.get(0))) {
						AutomationLogger.info("Unable to post ad. Posting Failed.");
						break;
					}
				}
				try {
					Thread.sleep(pWaitIntervalInSeconds*1000);
					ActionHelper.RefreshPage(pWebDriver);
					AutomationLogger.info("Attempt number "+counter+" of "+pTotalAttempts);
					counter++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception ex) {
				
			}
		}
		return displayed;

	}
}
