package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class IDXPropertyListingPage extends Page{
	
	@FindBy(xpath="//div[@id='listing-top-details']/strong")
	WebElement title_price;

	@FindBy(xpath="//div[@class='span3 listing-properties']/descendant::div[@class='span12']/descendant::br")
	WebElement details_in_title_block;
	
	@FindBy(xpath="//td[@class='feature-column']/small/strong[text()='Number of Baths:']/ancestor::td/following-sibling::td")
	WebElement number_of_baths;
	
	@FindBy(xpath="//div[@id='listing-top-details']")
	WebElement property_top_details;
	
	@FindBy(xpath="//a[text()='Map']")
	WebElement mapNavigationBarLink;
	
	@FindBy(xpath="//div[@id='listing-map']")
	WebElement googleMaps;
	
	@FindBy(xpath="//a[text()='Community Stats']")
	WebElement communityStatsBarLink;
	
	@FindBy(xpath="//a[text()='Schools']")
	WebElement schoolsBarLink;
	
	@FindBy(xpath="//a[text()=\"What's Nearby\"]")
	WebElement whatsNearbyBarLink;
	
	public IDXPropertyListingPage(WebDriver pWebDriver){
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPropertyprice() {
		return ActionHelper.getText(driver, title_price);
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

	public int getPropertValueFromHeader() {
		String propertyVal=ActionHelper.getText(driver, title_price).replace("$", "").replace(",", "");
		return Integer.parseInt(propertyVal);
	}

	public double getNumberOfBeds() {
		String lTitleText[] =driver.findElement(By.xpath("//div[@id='listing-top-details']/descendant::div[@class='row-fluid']")).getText().split("\n");
		String lNumofBeds="";
		for(String element:lTitleText) {
			if(element.contains("beds")) {
				lNumofBeds = element.trim().split("beds")[0].trim();
				break;
			}
		}
		return Double.parseDouble(lNumofBeds);
	}

	public double getNumberOfBaths() {
		return Double.parseDouble(ActionHelper.getText(driver, number_of_baths));
	}
	
	public String getPropertyAddress() {
		return ActionHelper.getAttribute(property_top_details, "data-listing-address").trim();
	}

	public String getMLSNum() {
		return ActionHelper.getAttribute(property_top_details, "data-mls-num");
	}

	public boolean clickOnMapBar() {
		return ActionHelper.Click(driver, mapNavigationBarLink);
	}

	public boolean isGoogleMapDisplayed(){
		return ActionHelper.Click(driver, googleMaps);
	}
	
	public boolean isPinDsiplayedOnGoogleMaps() {
		boolean isPinDisplayed=false;
		wait=new WebDriverWait(driver, 20);
		try {
			isPinDisplayed = wait.until(ExpectedConditions.attributeContains(By.xpath("//map[@id='gmimap0']/parent::div/img"), "src", ".png"));
		}catch(Exception ex) {
			System.out.println("No Pin is displayed on Google MAPS");
			AutomationLogger.info("No Pin is displayed on Google MAPS");
			return isPinDisplayed;
		}
		return isPinDisplayed;
	}

	public boolean clickOnCommunityStats() {
		return ActionHelper.Click(driver, communityStatsBarLink);
	}

	public boolean clickOnSchools() {
		return ActionHelper.Click(driver, schoolsBarLink);
	}

	public boolean clickOnWhatsNearBy() {
		return ActionHelper.Click(driver, whatsNearbyBarLink);
	}
}
