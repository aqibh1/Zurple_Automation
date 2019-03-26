package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

public class PageHeader extends Page{
	
	@FindBy(xpath="//div[@class='header_wrapper header_type3']")
	WebElement header;
	
	@FindBy(xpath="//div[@class='logo']::/descendant/img")
	WebElement propertypulseLogo_img;
	
	@FindBy(xpath="//ul[@id='menu-top-navigation']/descendant::a[text()='Home']")
	WebElement home_menu;
	String header_menu_xpath="//ul[@id='menu-top-navigation']/descendant::a[text()='"+DYNAMIC_VARIABLE+"']";
	
	String sub_menu_xpath="//ul[@id='menu-top-navigation']/descendant::a[text()='Listings']/following::a[text()='"+DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@id='user_menu_u']/descendant::a[@class='navicon-button x']")
	WebElement hamburger_menu_button;
	
	String hamburger_menu_open_xpath="//ul[@id='user_menu_open']/descendant::a[@title='"+DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@class='menu_user_picture']")
	WebElement user_image_icon;
	
	public PageHeader(WebDriver pWebDriver){
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
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

	public boolean clickOnHomeButton() {
		return ActionHelper.Click(driver, getDynamicElement(header_menu_xpath, "Home"));
	}
	
	public boolean clickOnFeaturedProperties() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Listings"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Featured Properties"));
	}
	
	public boolean clickOnSoldListings() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Listings"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Sold Listings"));
	}
	
	public boolean clickOnSearchHomes() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Home Search"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Search Homes"));
	}
	
	public boolean clickOnLocalHomeValues() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Home Search"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Local Home Values"));
	}
	
	public boolean clickOnCommunityReports() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Our Community"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Community Reports"));
	}
	
	public boolean clickOnSchoolReports() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Our Community"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "School Reports"));
	}
	public boolean clickOnWhatsNearby() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Our Community"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "What’s Nearby"));
	}
	public boolean clickOnRealEstateUpdates() {
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Real Estate Updates"));
	}
	public boolean clickOnContact() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Services"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Contact Me"));
	}
	public boolean clickOnAbout() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Services"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "About Me"));
	}
	public boolean clickOnBuyers() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Services"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Buyers"));
	}
	public boolean clickOnSellers() {
		ActionHelper.MouseHoverOnElement(driver, getDynamicElement(header_menu_xpath, "Services"));
		return ActionHelper.Click(driver, getDynamicElement(sub_menu_xpath, "Sellers"));
	}
	public boolean clickOnSavedSearches() {
		boolean isClickSuccessful=false;
		if(ActionHelper.Click(driver, hamburger_menu_button)) {
			isClickSuccessful = ActionHelper.Click(driver, getDynamicElement(hamburger_menu_open_xpath, "Saved Searches"));
		}
		return isClickSuccessful;
	}
	public boolean clickOnSavedProperties() {
		boolean isClickSuccessful=false;
		if(ActionHelper.Click(driver, hamburger_menu_button)) {
			isClickSuccessful = ActionHelper.Click(driver, getDynamicElement(hamburger_menu_open_xpath, "Saved Properties"));
		}
		return isClickSuccessful;
	}
	public boolean clickOnLogout() {
		boolean isClickSuccessful=false;
		if(ActionHelper.Click(driver, hamburger_menu_button)) {
			isClickSuccessful = ActionHelper.Click(driver, getDynamicElement(hamburger_menu_open_xpath, "Saved Searches"));
		}
		return isClickSuccessful;
	}
	public boolean isLeadLoggedIn() {
		return ActionHelper.waitForElementToBeVisible(driver, user_image_icon,20);
	}
	public void refreshPage() {
		 ActionHelper.RefreshPage(driver);
	}
}

