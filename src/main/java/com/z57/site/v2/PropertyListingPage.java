package com.z57.site.v2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertyListingPage extends Page{

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
	
//	@FindBy(xpath="//div[@id='collapse_prop_addr']/descendant::div[@class='panel-body']")
	String lPropertyAddress_xpath="//div[@id='collapse_prop_addr']/descendant::div[@class='panel-body']";
	
	String lPropertyInterior_xpath ="//table[@class='table table-condensed table-striped']/descendant::tr";
	
	public PropertyListingPage(WebDriver pWebDriver){
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public PropertyListingPage(WebDriver pWebDriver,String pSourceUrl){
		url=pSourceUrl;
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
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
			if(element.getText().startsWith(pTarget+":")) {
				return element.getText().split(pTarget+":")[1].trim();
			}
		}
		return "";
	}
	
	public int getNumberOfBeds() {
		if(!lNumberOfBeds.getText().isEmpty()) {
			return Integer.parseInt(lNumberOfBeds.getText());
		}else {
			return 0;
		}
	}
	
	public int getNumberOfBaths() {
		if(!lNumberOfBaths.getText().isEmpty()) {
			return Integer.parseInt(lNumberOfBeds.getText());
		}else {
			return 0;
		}
	}
	public int getLotSize() {
		if(!lLotSize.getText().isEmpty()) {
			return Integer.parseInt(lNumberOfBeds.getText());
		}else {
			return 0;
		}
	}
	public boolean propertyInteriorVerification(String pFeatureType) {
		boolean doesFeatureExist=false;
		List<WebElement> list_of_webelements=driver.findElements(By.xpath(lPropertyInterior_xpath));
		for(WebElement element: list_of_webelements) {
			System.out.println(element.getText());
			if(element.getText().contains(pFeatureType)) {
				String feat=element.getText().split(pFeatureType+":")[1].trim();
				if(feat.startsWith("Yes")) {
					doesFeatureExist=true;
					break;
				}
			}
		}
		return doesFeatureExist;
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

}
