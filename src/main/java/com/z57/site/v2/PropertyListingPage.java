package com.z57.site.v2;

import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.z57.EmailListingForm;
import resources.utility.ActionHelper;

public class PropertyListingPage extends Page{
	WebDriverWait wait;
	EmailListingForm emailListingForm;

	@FindBy(xpath="//div[@class=' col-md-9 rightmargin ']/descendant::h2[@class='entry-title entry-prop']")
	WebElement lPropertyTitleInHeader;
	
	@FindBy(xpath="//div[@class=' col-md-9 rightmargin ']/descendant::span[@class='price_area']")
	WebElement lPropertyPriceTitleInHeader;
	
	@FindBy(xpath="//div[@class='notice_area']/descendant::div[@class='property_categs']")
	WebElement lPropertyStatusInHeader;
	
	@FindBy(xpath="//div[@class='property_location']/span[@class='inforoom']")
	WebElement lNumberOfBeds;
	
	@FindBy(xpath="//div[@class='property_location']/span[@class='infobath']")
	WebElement lNumberOfBaths;
	
	@FindBy(xpath="//div[@class='property_location']/span[@class='infosize']")
	WebElement lLotSize;
	String lLotSizeInAcres_xpath="//div[@id='listing-features']/descendant::strong[text()='Lot Size in Acres:']/following::td[1]";
	String lLotSizeInSqFeet_xpath="//div[@id='listing-features']/descendant::strong[text()='Total Square Footage:']/following::td[1]";
	
//	@FindBy(xpath="//div[@id='collapse_prop_addr']/descendant::div[@class='panel-body']")
	String lPropertyAddress_xpath="//div[@id='collapse_prop_addr']/descendant::div[@class='panel-body']";
	
	String lPropertyInterior_xpath ="//table[@class='table table-condensed table-striped']/descendant::tr";
	
	@FindBy(xpath="//div[@id='tab_prpg']/descendant::a[text()='Map']")
	WebElement mapNavigationBarLink;
	
	@FindBy(xpath="//div[@id='listing-map']")
	WebElement googleMaps;
	
	@FindBy(xpath="//div[@id='tab_prpg']/descendant::a[text()='Community Stats']")
	WebElement communityStatsBarLink;
	
	@FindBy(xpath="//div[@id='z57_population_demographic_chart']/descendant::caption[text()='Population Demographic']")
	WebElement communityStats;
	
	@FindBy(xpath="//div[@id='tab_prpg']/descendant::a[text()='Schools']")
	WebElement schoolsBarLink;
	
	@FindBy(xpath="//div[@id='z57_schools_map_canvas']")
	WebElement schoolMap;
	
	@FindBy(xpath="//div[@id='tab_prpg']/descendant::a[text()=\"What's Nearby\"]")
	WebElement whatsNearbyBarLink;
	
	@FindBy(xpath="//div[@id='z57_poi_map_canvas']")
	WebElement whatsNearByMap;
	
	@FindBy(xpath="//table[@id='z57_schools_table']/descendant::label[text()='Schools Nearby']")
	WebElement schoolsNearbyLabel;
	
	@FindBy(xpath="//table[@id='z57_poi_table']/descendant::label[text()='Points of Interest']")
	WebElement pointsOfIntrestLabel;
	
	@FindBy(xpath="//div[@id='tab_prpg']/descendant::li[@class='active']/a[text()='Features']")
	WebElement featuresBarLink;
	
	@FindBy(xpath="//div[@id='z57_listing_map_canvas']/descendant::map/parent::div/img")
	WebElement googleMapPin;
	
	@FindBy(xpath="//div[@id='z57_schools_map_canvas']/descendant::div[@title]/img")
	WebElement schoolPins;
	String schoolPins_xpath="//div[@id='z57_schools_map_canvas']/descendant::div[@title]/img";
	
	@FindBy(xpath="//div[@id='z57_schools_table_info']")
	WebElement totalSchoolCount;
	
	@FindBy(xpath="//div[@id='z57_poi_table_info']")
	WebElement totalPoiCount;
	
	String totalPoiPins_xpath="//div[@id='z57_poi_map_canvas']/descendant::div[@title]";
	
	@FindBy(xpath="//div[@id='z57_population_demographic_chart']")
	WebElement communityStatsdemograpgicChart;
	
	@FindBy(xpath="//div[@id='z57_housing_chart']")
	WebElement communityStatsHousingChart;
	
	@FindBy(xpath="//div[@id='z57_population_age_range_chart']")
	WebElement communityStatsPopulationRangeChart;
	
	@FindBy(xpath="//div[@id='z57_households_chart']")
	WebElement communityStatsHouseHoldChart;
	
	@FindBy(xpath="//div[@id='z57_education_level_chart']")
	WebElement communityStatsEducationLevelStats;
	
	@FindBy(xpath="//div[@id='z57_employment_chart']")
	WebElement communityStatsEmploymentChart;
	
	@FindBy(xpath="//div[@id='z57_crime_chart']")
	WebElement communityStatsCrimeChart;
	
	@FindBy(xpath="//div[@id='z57_climate_chart']")
	WebElement communityStatsClimateChart;
	
	@FindBy(xpath="//div[@id='z57_area_ranking_chart']")
	WebElement communityStatsAreaRankingChart;
	
	@FindBy(xpath="//div[@id='collapse_prop_details']/descendant::div")
	WebElement lisOfPropertydetails;
	
	@FindBy(xpath="//button[@class='wpb_button wpb_btn-info wpb_btn-large email-listing-start']")
	WebElement emailListing_button;
	
	public PropertyListingPage(WebDriver pWebDriver){
		driver=pWebDriver;
		wait=new WebDriverWait(driver, 20);
		setEmailListingForm(driver);
		PageFactory.initElements(driver, this);
	}
	public PropertyListingPage(WebDriver pWebDriver,String pSourceUrl){
		url=pSourceUrl;
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public EmailListingForm getEmailListingForm() {
		return emailListingForm;
	}
	public void setEmailListingForm(WebDriver pWebDriver) {
		emailListingForm = new EmailListingForm(pWebDriver);
	}
	public String getPropertyTitleFromTheHeader() {
		return lPropertyTitleInHeader.getText();
	}
	
	public int getPropertValueFromHeader() {
		String propertyPrice=lPropertyPriceTitleInHeader.getText().replace(",","");
		propertyPrice=propertyPrice.replace("$","").trim();
		return Integer.parseInt(propertyPrice);
	}
	
	public String getPropertyStatusFromHeader() {
		String lPropStatus=lPropertyStatusInHeader.getText().split("Status:")[1].split("\n")[0].trim();
		return lPropStatus;
	}
	
	public String getAddress(String pTarget) {
		lPropertyAddress_xpath="//div[@id='collapse_prop_addr']/descendant::div[@class='panel-body']/div";
		List<WebElement> list_of_address = driver.findElements(By.xpath(lPropertyAddress_xpath));
		for(WebElement element:list_of_address) {
			if(element.getText().contains(pTarget+":")) {
				return element.getText().split(pTarget+":")[1].trim();
			}
		}
		return "";
	}
	
	public double getNumberOfBeds() {
		if(!lNumberOfBeds.getText().isEmpty()) {
			return Double.parseDouble(lNumberOfBeds.getText());
		}else {
			return 0;
		}
	}
	
	public double getNumberOfBaths() {
		if(!lNumberOfBaths.getText().isEmpty()) {	
			return Double.parseDouble(lNumberOfBaths.getText());
		}else {
			return 0;
		}
	}
	public double getLotSize() {
		double lotSizeInSqFeet=0;
		//Logic for in Acres and in Sq feet
		if(driver.findElement(By.xpath(lLotSizeInAcres_xpath))!=null) {
			lotSizeInSqFeet = Double.parseDouble(driver.findElement(By.xpath(lLotSizeInAcres_xpath)).getText())*43560;
		}else if(driver.findElement(By.xpath(lLotSizeInSqFeet_xpath))!=null) {
			lotSizeInSqFeet=Double.parseDouble(driver.findElement(By.xpath(lLotSizeInSqFeet_xpath)).getText());
		}else {
			return lotSizeInSqFeet;
		}
		return lotSizeInSqFeet;
	}
	public boolean propertyInteriorVerification(String pFeatureType) {
		boolean doesFeatureExist=false;
		List<WebElement> list_of_webelements=driver.findElements(By.xpath(lPropertyInterior_xpath));
		for(WebElement element: list_of_webelements) {
			System.out.println(element.getText());
			if(element.getText().contains(pFeatureType)) {
				String feat=element.getText().split(pFeatureType+":")[1].trim();
				if(feat.startsWith("Yes") || !feat.isEmpty()) {
					doesFeatureExist=true;
					break;
				}
			}
		}
		return doesFeatureExist;
	}
	public boolean clickOnMapBar() {
		pageScrollDown(mapNavigationBarLink);
		return clickOnElement(mapNavigationBarLink);
	}
	public boolean isGoogleMapDisplayed(){
		return isElementDisplayed(googleMaps);
	}
	public boolean clickOnCommunityStats() {
		return clickOnElement(communityStatsBarLink);
	}
	public boolean isCommunityStatsDisplayed() {
		
		return isElementDisplayed(communityStats);
	}
	public boolean clickOnSchools() {
		return clickOnElement(schoolsBarLink);
	}
	public boolean isSchoolMapsDisplayed() {
		return isElementDisplayed(schoolMap) && isElementDisplayed(schoolsNearbyLabel);
	}
	public boolean clickOnWhatsNearBy() {
		return clickOnElement(whatsNearbyBarLink);
	}
	public boolean isWhatsNearbyDisplayed() {
		return isElementDisplayed(whatsNearByMap) && isElementDisplayed(pointsOfIntrestLabel);
	}
	public boolean isFeatureTabExpanded() {
		return isElementDisplayed(featuresBarLink);
	}
	public void pageScrollDown(WebElement pScrollDownToElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,1200)");
	}
	public boolean isPinDsiplayedOnGoogleMaps() {
		boolean isPinDisplayed=false;
		try {
			isPinDisplayed = wait.until(ExpectedConditions.attributeContains(By.xpath("//map[@id='gmimap0']/parent::div/img"), "src", ".png"));
		}catch(Exception ex) {
			System.out.println("No Pin is displayed on Google MAPS");
			return isPinDisplayed;
		}
		return isPinDisplayed;
	}
	public boolean verifySchoolPins() {
		int counter=0;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("z57_schools_map_canvas")));
		List<WebElement> schoolPins = driver.findElements(By.xpath(schoolPins_xpath));
		for(WebElement element: schoolPins) {
			System.out.println(element.getAttribute("src"));
			if(element.getAttribute("src")!=null && !element.getAttribute("src").isEmpty()) {
				if(element.getAttribute("src").endsWith("school-2.png")) {
					counter++;
				}
				
			}
		}
		String schoolCount=totalSchoolCount.getText().split("of")[1].split("enteries")[0].trim();
		int schoolCountint=Integer.parseInt(schoolCount.split(" ")[0].trim());
		return counter==schoolCountint;
		
	}
	
	public boolean verifyPOIPins() {
		int counter=0;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("z57_poi_map_canvas")));
		List<WebElement> poiPins = driver.findElements(By.xpath("//div[@id='z57_poi_map_canvas']/descendant::div[@title]/img"));
		for(WebElement element: poiPins) {
			if(element.getAttribute("src")!=null && !element.getAttribute("src").isEmpty()) {
				if(element.getAttribute("src").endsWith(".png")) {
					counter++;
				}
				
			}
		}
		String poiCount=totalPoiCount.getText().split("of")[1].split("enteries")[0].trim();
		int poiCountint=Integer.parseInt(poiCount.split(" ")[0].trim());
		return counter==poiCountint;
		
	}
	
	public boolean isPopulationDemographicChartVisible() {
		return isElementDisplayed(communityStatsdemograpgicChart);
	}
	public boolean isPopulationRangeChartVisible() {
		return isElementDisplayed(communityStatsPopulationRangeChart);
	}
	public boolean isHouseholdsChartVisible() {
		return isElementDisplayed(communityStatsHouseHoldChart);
	}
	public boolean isEducationLevelChartVisible() {
		return isElementDisplayed(communityStatsEducationLevelStats);
	}
	public boolean isEmploymentChartVisible() {
		return isElementDisplayed(communityStatsEmploymentChart);
	}
	public boolean isCrimeChartVisible() {
		return isElementDisplayed(communityStatsCrimeChart);
	}
	public boolean isClimateChartVisible() {
		return isElementDisplayed(communityStatsClimateChart);
	}	
	public boolean isAreaRankingChartVisible() {
		return isElementDisplayed(communityStatsAreaRankingChart);
	}	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPropertyType(String pPropertyType) {
		boolean isFound=false;
		String xpath="//div[@id='collapse_prop_details']/descendant::div";
		List<WebElement> list_of_prop_details = driver.findElements(By.xpath(xpath));
		for(WebElement element:list_of_prop_details) {
			if(element.getText().contains(pPropertyType)) {
				isFound=true;
				break;
			}
		}
		return isFound;
	}
	public boolean verifyPropertyStyle(String pPropertyStyle) {
		boolean isFound=false;
		String xpath="//div[@id='listing-features']/descendant::strong[text()='Building Style:']/following::td[1]";
		List<WebElement> list_of_prop_details = driver.findElements(By.xpath(xpath));
		for(WebElement element:list_of_prop_details) {
			if(element.getText().contains(pPropertyStyle)) {
				isFound=true;
				break;
			}
		}
		return isFound;
	}
	
	public boolean clickOnEmailListing() {
		ActionHelper.ScrollPixels(driver);
		ActionHelper.MoveToElement(driver, emailListing_button);
		return ActionHelper.Click(driver, emailListing_button);
	}
	public boolean isPropertyTitleVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, lPropertyTitleInHeader, 15);
	}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean clickOnElement(WebElement pElementToBeClicked) {

		return ActionHelper.Click(driver, pElementToBeClicked);
	
	}
	
	private boolean isElementDisplayed(WebElement pElement) {
		return ActionHelper.isElementVisible(driver, pElement);

	}
}
