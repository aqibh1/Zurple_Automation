/**
 * 
 */
package com.zurple.backoffice.ads;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.blocks.zurple.ZBOHeadersBlock;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOAdsOverviewPage extends Page{
	private int row_index = 0;
	String listing_ad_rows = "//table[@id='ads_overview_zurple']/descendant::tr";
	String listing_address = "//table[@id='ads_overview_zurple']/descendant::tr/td/span[@class='listing_addr']";
	String listing_type = "//table[@id='ads_overview_zurple']/descendant::tr/td/h4[@class='listad_headline']";
	String ad_budget = "//table[@id='ads_overview_zurple']/descendant::span[@class='adprice_cap']";
	
	@FindBy(xpath="//h2[text()='Ads Overview']")
	WebElement adsOverviewPage_heading;
	
	@FindBy(xpath="//table[@id='ads_overview_zurple']/descendant::th[contains(text(),'Type')]")
	WebElement type_column_heading;
	
	String listing_Ad_icon = "//table[@id='ads_overview_zurple']/descendant::i[@class='fa fa-home']";
	
	
	private ZBOHeadersBlock header;
	
	public ZBOAdsOverviewPage(WebDriver pDriver) {
		driver = pDriver;
		setBackOfficeHeader();
		PageFactory.initElements(driver, this);
	}
	private void setBackOfficeHeader() {
		header = new ZBOHeadersBlock(driver);
	}
	public ZBOHeadersBlock getHeader() {
		return header;
	}
	private int getListingAdIndex(String pListingAddress) {
		int count = 0;
		List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_ad_rows);
		for(WebElement element: elements_list) {
			if(ActionHelper.getText(driver,element.findElement(By.xpath("/td/span[@class='listing_addr']"))).contains(pListingAddress)) {
				row_index = count;
				break;
			}
			count++;
		}
		return count;
	}
	private boolean verifyAdBudget(String pBudget) {
		boolean isBudget_verified = false;
		List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_ad_rows);
		if(ActionHelper.getText(driver, elements_list.get(row_index).findElement(By.xpath("/descendant::span[@class='adprice_cap']"))).contains(pBudget)) {
			isBudget_verified = true;
		}
		return isBudget_verified;
	}
	private boolean verifyRenewalDate(int pDays) {
		boolean isBudget_verified = false;
		String l_date =getTodaysDate(pDays, "MM/dd/YYYY");
		List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_ad_rows);
		if(ActionHelper.getText(driver, elements_list.get(row_index).findElement(By.xpath("/descendant::span[@class='addate_cap']"))).contains(l_date)) {
			isBudget_verified = true;
		}
		return isBudget_verified;
	}
	private boolean verifyAdLocation(String pLocation) {
		boolean isBudget_verified = false;
		List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_ad_rows);
		if(ActionHelper.getText(driver, elements_list.get(row_index).findElement(By.xpath("/descendant::span[@class='adlocation_cap']"))).contains(pLocation)) {
			isBudget_verified = true;
		}
		return isBudget_verified;
	}
	 protected String getTodaysDate(int pDays, String pFormat) {
	    	Date date = new Date();
	    	SimpleDateFormat df  = new SimpleDateFormat(pFormat);
	    	Calendar c1 = Calendar.getInstance();
	    	String currentDate = df.format(date);// get current date here
	    	if(pDays>0) {
	        	c1.add(Calendar.DAY_OF_YEAR, pDays);
	        	df = new SimpleDateFormat("MM/dd/YYYY");
	        	Date resultDate = c1.getTime();
	        	currentDate = df.format(resultDate);
	    	}
	    	return currentDate;
	    }
	 public boolean verifyAdsAreDisplayed() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_ad_rows);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = true;
		 }
		 return isAdsDisplayed;
	 }
	 public boolean isAdsOverviewPage() {
		 return ActionHelper.waitForElementToBeVisible(driver, adsOverviewPage_heading, 30);
	 }
	 public boolean isTypeColumnVisible() {
		 return ActionHelper.isElementVisible(driver, type_column_heading);
	 }
	 public boolean isListingAdHeadingVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_type);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = ActionHelper.isElementVisible(driver, elements_list.get(0));
		 }
		 return isAdsDisplayed;
	 }
	 public boolean isListingAdIconIsVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_Ad_icon);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = ActionHelper.isElementVisible(driver, elements_list.get(0));
		 }
		 return isAdsDisplayed;
	 }
}
