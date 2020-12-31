package com.zurple.website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

public class ZWV2TemplateHomePage extends Page{

	@FindBy(xpath="//li[@class='tx_socialicons']/descendant::span[contains(text(),'facebook')]")
	WebElement fb_icon;
	
	@FindBy(xpath="//li[@class='tx_socialicons']/descendant::span[contains(text(),'twitter')]")
	WebElement tw_icon;
	
	@FindBy(xpath="//li[@class='tx_socialicons']/descendant::span[contains(text(),'youtube')]")
	WebElement yt_icon;
	
	@FindBy(xpath="//div[@class='container']/descendant::h1")
	WebElement search_label;
	
	@FindBy(id="basic_city")
	WebElement search_text;
	
	@FindBy(xpath="//input[@value='SEARCH']")
	WebElement search_button;
	
	@FindBy(xpath="//input[@value='Advance Search']")
	WebElement advance_search;
	
	@FindBy(className="blurb-label")
	WebElement blurb_title;
	
	@FindBy(className="blurb-text")
	WebElement blurb_text;
	
	@FindBy(className="close")
	WebElement close_modal;
	
	@FindBy(className="top-listing-counter-block")
	WebElement listings_page;
	
	String footer_link = "footer_links";
	
	String social_icons = "sr-only";
	
	@FindBy(xpath="//div[@class='modal-footer']/descendant::button[@type='submit']")
	WebElement search_from_modal;
	
	@FindBy(xpath="//li[@class='open']/descendant::a[contains(text(),'Custom Search')]")
	WebElement custom_search;
	
	@FindBy(className="dropdown-toggle")
	WebElement dropdown_button;
	
	@FindBy(xpath="//div[@class='row']/descendant::div[@class='col-md-12']")
	WebElement banner_text;
	
	List<WebElement> socialIcons;
	
	public ZWV2TemplateHomePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public ZWV2TemplateHomePage() {
	
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

	@Override
	public WebElement getTopMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String fbIconExists() {
		socialIcons = ActionHelper.getListOfElementByClassName(driver, social_icons);
		return ActionHelper.getText(driver, socialIcons.get(1));
	}
	
	public String twitterIconExists() {
		socialIcons = ActionHelper.getListOfElementByClassName(driver, social_icons);
		return ActionHelper.getText(driver, socialIcons.get(2));
	}
	
	public String youtubeIconExists() {
		socialIcons = ActionHelper.getListOfElementByClassName(driver, social_icons);
		return ActionHelper.getText(driver, socialIcons.get(3));
	}
	
	public String getSearchLabel() {
		String jaflkjd = ActionHelper.getText(driver, search_label);
		return ActionHelper.getText(driver, search_label);
	}
	
	public boolean enterSearchText(String pStringToType) {
		return ActionHelper.ClearAndType(driver, search_text, pStringToType);
	}
	
	public boolean blurbTitle() {
		if(!ActionHelper.getText(driver, blurb_title).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean blurbDescription() {
		if(!ActionHelper.getText(driver, blurb_text).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean footerLink() {
		List<WebElement> links = ActionHelper.getListOfElementById(driver, footer_link);
		if(links.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean clickSearchButton() {
		return ActionHelper.Click(driver, search_button);
	}
	
	public boolean closeModalAndListingsPage() {
		if(ActionHelper.Click(driver, close_modal)==true) {
			return ActionHelper.waitForElementToBeVisible(driver, listings_page,30);
		} else {
			return ActionHelper.waitForElementToBeVisible(driver, listings_page,30);
		}
	}
	
	public boolean clickCustomSearch() {
		ActionHelper.Click(driver, dropdown_button);
		return ActionHelper.Click(driver, custom_search);
	}
	
	public boolean clickAdvanceSearchButton() {
		return ActionHelper.Click(driver, advance_search);
	}
	
	public boolean searchFromAdvanceSearchModal() {
		return ActionHelper.Click(driver, search_from_modal);
	}
	
	public boolean titleText() {
		return ActionHelper.waitForElementToBeVisible(driver, banner_text, 30);
	}

}
