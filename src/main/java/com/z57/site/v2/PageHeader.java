package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		setPageObject(pWebDriver, this);
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
		return actionHelper.Click(actionHelper.getDynamicElement(header_menu_xpath, "Home"));
	}
	
	public boolean clickOnFeaturedProperties() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Listings"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Featured Properties"));
	}
	
	public boolean clickOnSoldListings() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Listings"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Sold Listings"));
	}
	
	public boolean clickOnSearchHomes() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Home Search"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Search Homes"));
	}
	
	public boolean clickOnLocalHomeValues() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Home Search"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Local Home Values"));
	}
	
	public boolean clickOnCommunityReports() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Our Community"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Community Reports"));
	}
	
	public boolean clickOnSchoolReports() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Our Community"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "School Reports"));
	}
	public boolean clickOnWhatsNearby() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Our Community"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "What’s Nearby"));
	}
	public boolean clickOnRealEstateUpdates() {
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Real Estate Updates"));
	}
	public boolean clickOnContact() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Services"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Contact Me"));
	}
	public boolean clickOnAbout() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Services"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "About Me"));
	}
	public boolean clickOnBuyers() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Services"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Buyers"));
	}
	public boolean clickOnSellers() {
		actionHelper.MouseHoverOnElement(actionHelper.getDynamicElement(header_menu_xpath, "Services"));
		return actionHelper.Click(actionHelper.getDynamicElement(sub_menu_xpath, "Sellers"));
	}
	public boolean clickOnSavedSearches() {
		boolean isClickSuccessful=false;
		if(actionHelper.Click(hamburger_menu_button)) {
			isClickSuccessful = actionHelper.Click(actionHelper.getDynamicElement(hamburger_menu_open_xpath, "Saved Searches"));
		}
		return isClickSuccessful;
	}
	public boolean clickOnSavedProperties() {
		boolean isClickSuccessful=false;
		if(actionHelper.Click(hamburger_menu_button)) {
			isClickSuccessful = actionHelper.Click(actionHelper.getDynamicElement(hamburger_menu_open_xpath, "Saved Properties"));
		}
		return isClickSuccessful;
	}
	public boolean clickOnLogout() {
		boolean isClickSuccessful=false;
		if(actionHelper.Click(hamburger_menu_button)) {
			isClickSuccessful = actionHelper.Click(actionHelper.getDynamicElement(hamburger_menu_open_xpath, "Saved Searches"));
		}
		return isClickSuccessful;
	}
	public boolean isLeadLoggedIn() {
		return actionHelper.waitForElementToBeVisible(user_image_icon,20);
	}
	public void refreshPage() {
		 actionHelper.RefreshPage();
	}
}

