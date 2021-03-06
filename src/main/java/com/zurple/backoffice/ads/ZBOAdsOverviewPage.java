/**
 * 
 */
package com.zurple.backoffice.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.blocks.zurple.ZBOHeadersBlock;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

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
	
	String first_row_ad_starting_ending_date = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox'][1]";
	String first_row_ad_starting_day = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_date'][1]";
	String first_row_ad_starting_month = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_month'][1]";
	String first_row_ad_starting_year = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_yaer'][1]";
	@FindBy(xpath="//table[@id='ads_overview_zurple']/descendant::tr[3]/*/span[@class='addate_cap']") 
	WebElement first_row_ad_starting_date;
	
	String first_row_ad_ending_date = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox'][2]";
	String first_row_ad_ending_day = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_date'][2]";
	String first_row_ad_ending_month = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_month'][2]";
	String first_row_ad_ending_year = "//table[@id='ads_overview_zurple']/descendant::div[@class='datebox_cont'][1]/div[@class='ad_datebox']/span[@class='ad_yaer'][2]";

	String adView_count = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewcount']";
	String adView_icon = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewicon']";
	String adClick_count = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewcount']";
	String adClick_icon = "//table[@id='ads_overview_zurple']/descendant::div[@title='Views']/span[@class='adviewicon']";
	
	String price_xpath = "//table[@id='ads_overview_zurple']/descendant::span[@class='adprice_cap']";
	String location_xpath = "//table[@id='ads_overview_zurple']/descendant::span[@class='adlocation_cap']";
	String adddate_xpath = "//table[@id='ads_overview_zurple']/descendant::span[@class='addate_cap']";
	String status_xpath = "//table[@id='ads_overview_zurple']/descendant::td[@class='td_center addetails_cont']/following-sibling::td[1]";
	String preview_button_xpath = "//table[@id='ads_overview_zurple']/descendant::a[text()=' Preview']";
	String ad_preview_box_xpath = "//table[@id='ads_overview_zurple']/descendant::div[@class='adpreview_box']";
	
	String play_icon_preview = "//table[@id='ads_overview_zurple']/descendant::div[@class='playicon_slide']/i";
	String slide_show_image ="//table[@id='ads_overview_zurple']/descendant::div[@class='fb_ad_preview_slideshow']/descendant::div[@class='slide-image current']/img";
	String first_row_status = "//table[@id='ads_overview_zurple']/descendant::td[@class='td_center' and contains(text(),'Paused')]";
	
	String buyer_lead_ad_icon = "//table[@id='ads_overview_zurple']/descendant::tr/td/div[@class='adshomeicon']/img";
	
	String ad_to_find = "//div[@class='adpreview_box']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String preview_button = "btn-pro";
	String learn_more_button_list = "//table[@id='ads_overview_zurple']/descendant::div[@class='ad_details_wrapper']/*/a[@href]";
	
	String ad_slider = "//div[@class='adpreview_box']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String ad_date = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='addate_cap']";
	String ad_price = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='adprice_cap']";
	String ad_location = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='adlocation_cap']";
	String ad_listingtype = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='listing_addr']";

	
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
		
	private boolean verifyAdBudget(String pBudget,String ad_id) {
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
//	 public boolean isListingAddressIsVisible() {
//		 return !getListingAddressFirstRow().isEmpty();
//	 }
	 public String getListingAddressFirstRow(String pAdId) {
		 String l_listingAddress = "";
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_listingtype, pAdId));
		 if(elements_list.size()>0) {
			 l_listingAddress = ActionHelper.getText(driver, elements_list.get(0));
		 }
		 return l_listingAddress;
	 }
	 public boolean isStartEndDateVisible() {
//		 List<WebElement> starting_day_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_day);
//		 List<WebElement> starting_month_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_month);
//		 List<WebElement> starting_year_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_year);
//		 List<WebElement> ending_day_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_ending_date);
//		 List<WebElement> ending_month_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_ending_date);
//		 List<WebElement> ending_year_list = ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_ending_date);
//		 
//		 String l_StartDay = ActionHelper.getText(driver, starting_day_list.get(0));
//		 String l_StartMonth = ActionHelper.getText(driver,starting_month_list.get(0));
//		 String l_StartYear = ActionHelper.getText(driver,starting_year_list.get(0));
//		 String l_EndDay = ActionHelper.getText(driver,starting_day_list.get(1));
//		 String l_EndMonth = ActionHelper.getText(driver,starting_month_list.get(1));
//		 String l_EndYear = ActionHelper.getText(driver,starting_year_list.get(1));
//		 return !l_StartDay.isEmpty() && !l_StartMonth.isEmpty() && !l_StartYear.isEmpty() && !l_EndDay.isEmpty() && !l_EndMonth.isEmpty() && !l_EndYear.isEmpty(); 
		 return !ActionHelper.getText(driver, first_row_ad_starting_date).isEmpty();
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
	 public boolean verifyAdPriceIsDisplayed() {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, price_xpath);
		 if(elements_list.size()>0) {
			 String ad_price = ActionHelper.getText(driver, elements_list.get(0));
			 isAdsDisplayed = ad_price.contains("120") || ad_price.contains("160") || ad_price.contains("240") || ad_price.contains("320") || ad_price.contains("400");
		 }
		 return isAdsDisplayed;
	 }
	 public boolean verifyAdPriceIsDisplayed(String pAdBudget) {
		 boolean isAdsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, price_xpath);
		 if(elements_list.size()>0) {
			 String ad_price = ActionHelper.getText(driver, elements_list.get(0));
			 isAdsDisplayed = ad_price.equalsIgnoreCase(pAdBudget+" Per Month");
		 }
		 return isAdsDisplayed;
	 }
	 public boolean verifyAdLocationIsDisplayed() {
		 return !getAdLocation().isEmpty();
	 }
	 public String getAdLocation() {
		 String l_adLocation = "";
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, location_xpath);
		 if(elements_list.size()>0) {
			 l_adLocation = ActionHelper.getText(driver, elements_list.get(0));
		 }
		 return l_adLocation;
	 }
	 public boolean verifyAdRecurringDateIsDisplayed() {
		 return !getRenewalDate().isEmpty();
	 }
	 
	 private String getRenewalDate() {
		 String l_renewalDATE = "";
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, adddate_xpath);
		 if(elements_list.size()>0) {
			 l_renewalDATE = ActionHelper.getText(driver, elements_list.get(0));
		 }
		 return l_renewalDATE;
	 }
	 public boolean verifyAdStatusIsDisplayed() {
		 boolean isAdsDateIsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, status_xpath );
		 if(elements_list.size()>0) {
			 isAdsDateIsDisplayed = !ActionHelper.getText(driver, elements_list.get(0)).isEmpty();
		 }
		 return isAdsDateIsDisplayed;
	 }
	 public boolean isPreviewDisplayed() {
		 boolean isAdsDateIsDisplayed = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, preview_button_xpath);
		 List<WebElement> elements_list2 = ActionHelper.getListOfElementByXpath(driver, ad_preview_box_xpath);
		 if(elements_list.size()>0) {
			 isAdsDateIsDisplayed = ActionHelper.Click(driver, elements_list.get(0));
			 if(isAdsDateIsDisplayed) {
				 isAdsDateIsDisplayed = ActionHelper.waitForElementToBeVisible(driver, elements_list2.get(0), 15);
			 }
		 }
		 return isAdsDateIsDisplayed; 
	 }
	
	 public boolean verifyAdSlideShowIsWorking() {
		 boolean isSlideShowWorking = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, play_icon_preview);
		 List<WebElement> elements_list2 = ActionHelper.getListOfElementByXpath(driver, slide_show_image);
		 if(elements_list.size()>0) {
			 isSlideShowWorking = ActionHelper.MouseHoverOnElement(driver, elements_list.get(0));
			 if(isSlideShowWorking) {
				String image_path_01 = ActionHelper.getAttribute(elements_list2.get(0), "src");
				ActionHelper.staticWait(2);
				elements_list2 = ActionHelper.getListOfElementByXpath(driver, slide_show_image);
				String image_path_02 = ActionHelper.getAttribute(elements_list2.get(0), "src");
				isSlideShowWorking = !image_path_01.equalsIgnoreCase(image_path_02);
			 }
		 }
		 return isSlideShowWorking; 
	 }
	 public boolean verifyStartingEndingDate(String pAdId){
		 String l_consolidatedStartingDate = getStartingEndingDate(true,pAdId);
		 String l_consolidatedEndingDate = getStartingEndingDate(false,pAdId);

		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Date startDate = null;
		 Date endDate = null;
		 try {
			 startDate = (Date) sdf.parseObject(l_consolidatedStartingDate);
			 endDate = (Date) sdf.parseObject(l_consolidatedEndingDate);
		 } catch (ParseException e) {
			 // TODO Auto-generated catch block
			 AutomationLogger.error("Error in date format");
			 AutomationLogger.error(e.getLocalizedMessage());
		 }
		 long diff = endDate.getTime() - startDate.getTime();
		 long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		 return lDays==30;	
	 }
	 public boolean verifyRenewalDate(String pAdId){
		 String l_consolidatedStartingDate = getStartingEndingDate(true,pAdId);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Date renewalDate = null, startDate = null;
		 try {
			 renewalDate = (Date) sdf.parseObject(getStartingEndingDate(false,pAdId));
			 startDate = (Date) sdf.parseObject(l_consolidatedStartingDate);
		 } catch (ParseException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }


		 long diff = renewalDate.getTime() - startDate.getTime();
		 long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		 return lDays==31;
	 }
	 public String getAdStatus() {
		 return ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_status).get(0));
	 }
	 public boolean verifyStartDate(String pAdId){
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Date startDate = null;
		 Date todaysDate = null;
		try {
			startDate = (Date) sdf.parseObject(getStartingEndingDate(true,pAdId));
			todaysDate = (Date) sdf.parseObject(getTodaysDate());
		} catch (ParseException e) {
			AutomationLogger.error("Error in date format");
			AutomationLogger.error(e.getLocalizedMessage());
		}
		 
		 long diff = todaysDate.getTime() - startDate.getTime();
		 long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		 return lDays==0;
	 }
	
	 public String getAdType() {
		 String l_adType = "";
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, listing_type);
		 if(elements_list.size()>0) {
			 l_adType = ActionHelper.getText(driver, elements_list.get(0));
		 }
		 return l_adType;
	 }
	 public boolean verifyBuyerLeadAdIcon() {
		 boolean isVisible = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, buyer_lead_ad_icon);
		 if(elements_list.size()>0) {
			 if(ActionHelper.isElementVisible(driver, elements_list.get(0))) {
				 isVisible = ActionHelper.getAttribute(elements_list.get(0), "src").contains("bl_zurple");
			 }
		 }
		 return isVisible;
	 }
	 public boolean clickOnLearnMoreButton() {
		 boolean isClickedOnLearnMoreButton = false;
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, preview_button_xpath);
		 List<WebElement> elements_list2 = ActionHelper.getListOfElementByXpath(driver, learn_more_button_list);
		 if(elements_list.size()>0) {
			 isClickedOnLearnMoreButton = ActionHelper.Click(driver, elements_list.get(0));
			 isClickedOnLearnMoreButton = ActionHelper.waitForElementToBeVisible(driver, elements_list2.get(0), 15);
			 if(isClickedOnLearnMoreButton) {
				 isClickedOnLearnMoreButton = ActionHelper.Click(driver, elements_list2.get(0));
			 }
		 }
		 return isClickedOnLearnMoreButton; 

	 }
	
	 public String getAdLocation(String pAdId) {
		 String l_adLocation = "";
		 List<WebElement> elements_list = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_location, pAdId));
		 if(elements_list.size()>0) {
			 l_adLocation = ActionHelper.getText(driver, elements_list.get(0));
		 }
		 return l_adLocation;
	 }
//	 private String getStartingEndingDate(boolean pIsStarting){
//		 String lDay = pIsStarting?ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_day).get(0)):ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_day).get(1));
//		 String lMonth = pIsStarting?ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_month).get(0)):ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_month).get(1));
//		 String lYear = pIsStarting?ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_year).get(0)):ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, first_row_ad_starting_year).get(1));
//		 String l_consolidatedStartingDate = getMonth(lMonth)+"/"+lDay+"/"+lYear;
//		 return l_consolidatedStartingDate;
//	 }
	 private int getMonth(String pMonth){
		 Date date = new Date();
		 try {
			 date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(pMonth);
		 } catch (ParseException e) {
			AutomationLogger.error("Error in date format");
			AutomationLogger.error(e.getLocalizedMessage());
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 int month = cal.get(Calendar.MONTH)+1;
		 return month;
	 }
	 private String getStartingEndingDate(boolean pIsStarting, String pAdId){
		 String l_consolidatedStartingDate = "";
		 if(pIsStarting) {
			 l_consolidatedStartingDate = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_date, pAdId)).get(0));
		 } else {
			 l_consolidatedStartingDate = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_date, pAdId)).get(1));
		 }
		 return l_consolidatedStartingDate;
	 }
}
