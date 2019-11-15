package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.LeadDetailPage;
import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ZBOLeadPage extends Page{
	
	@FindBy(xpath="//h3[@class='header-title' and contains(text(),'Leads')]")
	WebElement lead_heading;
	
	@FindBy(id="leadsInputName")
	WebElement lead_input;
	
	@FindBy(id="leads-grid-filter-button")
	WebElement lead_search_button;
	
	@FindBy(id="DataTables_Table_0_processing")
	WebElement procession_notfication;
	
	String lead_row = "//div[@id='DataTables_Table_0_wrapper']/descendant::td/a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	//Sorting Headers
	
	String sorting_column_ascending_xpath = "//th[@aria-label='"+FrameworkConstants.DYNAMIC_VARIABLE+": activate to sort column ascending']";
	
	String sorting_column_descending_xpath = "//th[@aria-label='"+FrameworkConstants.DYNAMIC_VARIABLE+": activate to sort column descending']";

	@FindBy(xpath="//div[@id='leads-grid']/descendant::div[text()='Processing...']")
	WebElement processing_alert;
	
	@FindBy(xpath="//select[@id='location-parent-1']")
	WebElement filter_dropdown;
	
	@FindBy(xpath="//select[@id='location-child-1']")
	WebElement filter_child_dropdown;
	
	@FindBy(id="leads-grid-filter-button")
	WebElement search_button;
	
	@FindBy(id="lead-search-reset-button")
	WebElement reset_button;
	
	@FindBy(xpath="//table[@id='DataTables_Table_0']/descendant::tr")
	WebElement lead_rows;
	String lead_rows_xpath="//table[@id='DataTables_Table_0']/descendant::tbody/descendant::tr";
	
	String lZurpleBOUrl = "";
	
	private Map<Integer, HashMap<String,String>> rowDataMap = new HashMap<Integer, HashMap<String,String>>(); 
	
	public ZBOLeadPage() {
		
	}
	public ZBOLeadPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public void setBOUrl(String pUrl) {
		lZurpleBOUrl = pUrl;
	}
	public String getBOUrl() {
		return lZurpleBOUrl;
	}
	public boolean isLeadPage() {
		return ActionHelper.waitForElementToBeVisible(driver, lead_heading, 30);
	}
	
	public boolean typeLeadNameToSearch(String pName) {
		return ActionHelper.Type(driver, lead_input, pName);
	}

	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, lead_search_button);
	}
	
	public boolean isLeadExist(String pLeadName) {
		boolean isLeadExist= false;
		pLeadName = WordUtils.capitalizeFully(pLeadName);
		if(!typeLeadNameToSearch(pLeadName)) {
			return false;
		}
		if(!clickOnSearchButton()) {
			return false;
		}
		if(ActionHelper.waitForElementToBeDisappeared(driver, procession_notfication,30)) {
			ActionHelper.staticWait(5);
			isLeadExist = ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_row,pLeadName));
		}
		return isLeadExist;
	}
	public boolean verifyNameSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Name");
	}
	public boolean verifyEmailSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Email");
	}
	public boolean verifySearchLocationSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Search Location");
	}
	public boolean verifyMaxPriceSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Max Price");
	}
	public boolean verifyDateCreatedSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Date Created");
	}
	public boolean verifyAgentSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Agent");
	}
	public boolean verifyLastModifiedSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Last Modified");
	}
	public boolean verifyLastVisitSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Last Visit");
	}
	public boolean verifyPriorityRankSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Priority Rank");
	}
	public boolean isProcessingComplete() {
		return ActionHelper.waitForElementToBeDisappeared(driver, procession_notfication,30);
	}
	
	public boolean clickAndSelectFilterName(String pFilterName) {
		return ActionHelper.selectDropDownOption(driver, filter_dropdown, "", pFilterName);
		
	}
	public boolean clickAndSelectFilterValue(String pFilterValue) {
		return ActionHelper.selectDropDownOption(driver, filter_child_dropdown, "", pFilterValue);	
	}
	
	private boolean isSortingWorking(String pXpathAscending, String pXpathDescending,String pDynamicVariable) {
		boolean isWorking = false;
		ActionHelper.staticWait(3);
		if(ActionHelper.Click(driver, ActionHelper.waitAndGetDynamicElement(driver,pXpathAscending,pDynamicVariable))) {
			isWorking = ActionHelper.waitForElementToBeVisible(driver, ActionHelper.waitAndGetDynamicElement(driver, pXpathDescending, pDynamicVariable), 15);		
		}
		return isWorking;
	}
	
	public boolean verifyFilter(String pFilterName, String pFilterValue) throws ParseException {
		boolean isVerified = false;
		isProcessingComplete();
		populateResultsMap();
		switch(pFilterName) {
		case "By Priority Ranking":
			isVerified = verifyFromPopulatedData("Priority Rank",pFilterValue);
			break;
		case "By Agent":
			isVerified = verifyFromPopulatedData("Agent",pFilterValue);
			break;
		case "By City":
			isVerified = verifyCity("Search Location",pFilterValue);
			break;
		case "By Hot Behavior Alerts":
			isVerified = verifyHotBehavior(pFilterValue);
			break;
		case "By Date Created":
			isVerified = verifyDate("Date Created", pFilterValue);
			break;
		case "By Last Login Date":
			isVerified = verifyDate("Last Visit", pFilterValue);
			break;
		case "By Lead Source":
			isVerified = verifyLeadSource("Social");
			break;
		case "By Last Email Sent":
			break;
		case "By Last Lead Reply":
			break;
		case "By Phone Number Provided":
			isVerified = verifyLeadPhone();
			break;
		case "By Website":
			isVerified = !new ZBOLeadDetailPage(driver).getWebSite().isEmpty();
			break;
		case "By Email Preferences":
			isVerified = new ZBOLeadDetailPage(driver).verifyEmailPreferences(pFilterValue.split(":")[0], pFilterValue.split(":")[1]);
			break;
		case "By Transaction Goal":
			isVerified = verifyTransactionGoals(pFilterValue);
			break;
		case "By Lead Group":
			break;
		case "By Email Verification":
			isVerified = verifyEmailStatus("Email Status",pFilterValue);
			break;
		default:
			break;
		}
		return isVerified;
	}

	private boolean verifyFromPopulatedData(String pFilterName, String pFilterValue) {
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		return dataRowMap.get(pFilterName).equalsIgnoreCase(pFilterValue);
	}
	private boolean verifyCity(String pFilterName, String pFilterValue) {
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		return pFilterValue.contains(dataRowMap.get(pFilterName));
	}
	private boolean verifyDate(String pFilterName, String pFilterValue) throws ParseException {
		int days = 0;
		switch(pFilterValue) {
		case "Today":
			days = 0;
			break;
		case "last 7 days":
			days = 7;
			break;
		case "last 30 days":
			days = 30;
			break;
		case "last 60 days":
			days = 60;
			break;
		case "last 90 days":
			days = 90;
			break;
		}
		
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		String lDateOnPage = dataRowMap.get(pFilterName).split("at")[0].trim();
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dateOnPage = null;
		Date curretDate = null;
		dateOnPage = format.parse(lDateOnPage);
		String currentDateStr = format.format(new Date());
		curretDate = format.parse(currentDateStr);
		
		long diff = curretDate.getTime() - dateOnPage.getTime();
		long lDiffInDays = diff / (24 * 60 * 60 * 1000);
		
		return lDiffInDays<=days;
	}
	
	public boolean selectLead(String pLeadName) {
		boolean isLeadExist= false;
		pLeadName = WordUtils.capitalizeFully(pLeadName);
		if(!typeLeadNameToSearch(pLeadName)) {
			return false;
		}
		if(!clickOnSearchButton()) {
			return false;
		}
		if(ActionHelper.waitForElementToBeDisappeared(driver, procession_notfication,30)) {
			ActionHelper.staticWait(5);
			WebElement element = ActionHelper.getDynamicElement(driver, lead_row,pLeadName);
			isLeadExist = ActionHelper.isElementVisible(driver, element);
			ActionHelper.Click(driver, element);
		}
		return isLeadExist;
	}
	
	private boolean verifyEmailStatus(String pFilterName, String pFilterValue) {
		boolean isVerified = false;
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		if(pFilterValue.equalsIgnoreCase("Valid Emails") && Boolean.parseBoolean(dataRowMap.get(pFilterName))) {
			isVerified = true;
		}else if(pFilterValue.equalsIgnoreCase("Invalid Emails") && Boolean.parseBoolean(dataRowMap.get(pFilterName))){
			isVerified= false;
		}
		return isVerified;
	}
	
	private boolean verifyLeadSource(String pFilterValue) {
		boolean isVerified = false;
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		String lLeadDetailId = dataRowMap.get("Lead Id");
		String lUrl = getBOUrl()+lLeadDetailId;
		driver.navigate().to(lUrl);
		String lSource = new ZBOLeadDetailPage(driver).getLeadDetails("Lead Source:");
		isVerified =  lSource.equalsIgnoreCase(pFilterValue);
		driver.navigate().back();
		return isVerified;
		
	}
	
	private boolean verifyTransactionGoals(String pFilterValue) {
		boolean isVerified = false;
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		String lLeadDetailId = dataRowMap.get("Lead Id");
		String lUrl = getBOUrl()+lLeadDetailId;
		driver.navigate().to(lUrl);
		String lSource = new ZBOLeadDetailPage(driver).getLeadDetails("Transaction Goals:");
		isVerified =  lSource.contains(pFilterValue);
		driver.navigate().back();
		return isVerified;
		
	}
	
	private boolean verifyLeadPhone() {
		boolean isVerified = false;
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		String lLeadDetailId = dataRowMap.get("Lead Id");
		String lUrl = getBOUrl()+lLeadDetailId;
		driver.navigate().to(lUrl);
		String lPhoneNum = new ZBOLeadDetailPage(driver).getPhoneNum();
		isVerified =  !lPhoneNum.isEmpty();
		driver.navigate().back();
		return isVerified;
		
	}
	
	private boolean verifyHotBehavior(String pFilterValue) {
		String lHotBehavior = "";
		boolean isVerified = false;
		int randomInt = (int)(rowDataMap.size() * Math.random());
		HashMap<String,String> dataRowMap = rowDataMap.get(randomInt);
		switch(pFilterValue) {
		case "High Activity: Frequent Returns":
			lHotBehavior = dataRowMap.get("hot_behavior_preffered");
			isVerified = !lHotBehavior.contains("nbsp");
			break;
			default:
				break;
		}
		return isVerified;
	}
	private void populateResultsMap() {
		int counter = 0;
		HashMap<String,String> dataSingleRowMap = new HashMap<String,String>();
		List<WebElement> list_of_data = ActionHelper.getListOfElementByXpath(driver, lead_rows_xpath);
		for(WebElement element: list_of_data) {
			List<WebElement> list_of_single_row = element.findElements(By.tagName("td"));
			dataSingleRowMap.put("Name", list_of_single_row.get(2).findElement(By.tagName("a")).getText());
			dataSingleRowMap.put("Email", list_of_single_row.get(3).findElement(By.tagName("a")).getText());
			dataSingleRowMap.put("Lead Id", list_of_single_row.get(3).findElement(By.tagName("a")).getAttribute("href"));
			List<WebElement> list_of_email_status = list_of_single_row.get(3).findElements(By.tagName("span"));
			String isValidEmail = list_of_email_status.size()>0?"false":"true";
			dataSingleRowMap.put("Email Status",isValidEmail);
			dataSingleRowMap.put("Search Location", list_of_single_row.get(4).getText());
			dataSingleRowMap.put("Max Price", list_of_single_row.get(5).getText());
			dataSingleRowMap.put("Date Created", list_of_single_row.get(6).getText());
			dataSingleRowMap.put("Agent", list_of_single_row.get(7).getText());
			dataSingleRowMap.put("Last Modified", list_of_single_row.get(8).getText());
			dataSingleRowMap.put("Last Visit", list_of_single_row.get(9).getText());
			List<WebElement> lHotBehaviors = list_of_single_row.get(10).findElements(By.tagName("li"));
			dataSingleRowMap.put("hot_behavior_preffered", lHotBehaviors.get(0).getText());
			dataSingleRowMap.put("hot_behavior_browsing", lHotBehaviors.get(1).getText());
			dataSingleRowMap.put("hot_behavior_expensive", lHotBehaviors.get(2).getText());
			dataSingleRowMap.put("hot_behavior_favorites", lHotBehaviors.get(3).getText());
			dataSingleRowMap.put("hot_behavior_return", lHotBehaviors.get(4).getText());
			dataSingleRowMap.put("Priority Rank", list_of_single_row.get(11).findElement(By.tagName("span")).getText());
			rowDataMap.put(counter, dataSingleRowMap);
			counter++;
		}
	}
	
	
}
