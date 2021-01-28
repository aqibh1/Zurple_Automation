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
	
	String first_row_ad_starting_ending_date = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']";
	String first_row_ad_starting_day = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_date']";
	String first_row_ad_starting_month = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_month']";
	String first_row_ad_starting_year = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_yaer']";

	String adView_count = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewcount']";
	String adView_icon = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewicon']";
	String adClick_count = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewcount']";
	String adClick_icon = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewicon']";
	
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
	 public boolean isListingAddressIsVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_address);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = !ActionHelper.getText(driver, elements_list.get(0)).isEmpty();
		 }
		 return isAdsDisplayed;
	 }
	 public boolean isStartEndDateVisible() {
		 List<WebElement> starting_day_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_day);
		 List<WebElement> starting_month_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_month);
		 List<WebElement> starting_year_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_year);
//		 List<WebElement> ending_day_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_ending_date);
//		 List<WebElement> ending_month_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_ending_date);
//		 List<WebElement> ending_year_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_ending_date);
		 
		 String l_StartDay = ActionHelper.getText(driver, starting_day_list.get(0));
		 String l_StartMonth = ActionHelper.getText(driver,starting_month_list.get(0));
		 String l_StartYear = ActionHelper.getText(driver,starting_year_list.get(0));
		 String l_EndDay = ActionHelper.getText(driver,starting_day_list.get(1));
		 String l_EndMonth = ActionHelper.getText(driver,starting_month_list.get(1));
		 String l_EndYear = ActionHelper.getText(driver,starting_year_list.get(1));
		 return !l_StartDay.isEmpty() && !l_StartMonth.isEmpty() && !l_StartYear.isEmpty() && !l_EndDay.isEmpty() && !l_EndMonth.isEmpty() && !l_EndYear.isEmpty(); 
	 }
	 public boolean isAdViewCountVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, adView_count);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = !ActionHelper.getText(driver, elements_list.get(0)).isEmpty();
		 }
		 return isAdsDisplayed;
	 }
	 public boolean isAdViewIconVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, adView_icon);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = ActionHelper.isElementVisible(driver, elements_list.get(0));
		 }
		 return isAdsDisplayed;
	 }
	 public boolean isAdClickCountVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, adClick_count);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = !ActionHelper.getText(driver, elements_list.get(0)).isEmpty();
		 }
		 return isAdsDisplayed;
	 }
	 public boolean isAdClickIconVisible() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, adClick_icon);
		 if(elements_list.size()>0) {
			 isAdsDisplayed = ActionHelper.isElementVisible(driver, elements_list.get(0));
		 }
		 return isAdsDisplayed;
	 }
}
