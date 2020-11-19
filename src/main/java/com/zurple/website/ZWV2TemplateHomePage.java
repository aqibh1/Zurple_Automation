package com.zurple.website;

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
	
	@FindBy(xpath="//div[@class='container']/descendant::h1[contains(text(),'Start  Your Home Search')]")
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
	
	@FindBy(xpath="//div[@id='footer_links']/descendant::a[@href='san_diego-real-estate']")
	WebElement footer_link;
	
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
	
	public boolean fbIconExists() {
		return ActionHelper.waitForElementToBeClickAble(driver, fb_icon);
	}
	
	public boolean twitterIconExists() {
		return ActionHelper.waitForElementToBeClickAble(driver, tw_icon);
	}
	
	public boolean youtubeIconExists() {
		return ActionHelper.waitForElementToBeClickAble(driver, yt_icon);
	}
	
	public String getSearchLabel() {
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
		return ActionHelper.waitForElementToBeClickAble(driver, footer_link);
	}

}
