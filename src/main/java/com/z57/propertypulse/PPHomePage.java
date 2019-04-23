package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

public class PPHomePage extends Page{
	
	@FindBy(xpath="//div[@id='member-nav']/descendant::ul[@class='dropdown-menu']")
	WebElement settings_dropdown;
	String settings_dropdown_xpath = "//div[@id='member-nav']/descendant::ul[@class='dropdown-menu']";
	
	@FindBy(id="menu-signed-in-as")
	WebElement userSignedIn;
	
	@FindBy(xpath="//div[@id='member-nav']/descendant::a[@class='btn dropdown-toggle']")
	WebElement settings_button;
	
	public PPHomePage() {
		// TODO Auto-generated constructor stub
	}
	PPHomePage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		
	}
	public boolean isLoginSuccessful(String pUsername) {
		boolean isUserLoggedIn = false;
//		if(ActionHelper.waitForElementsToBeFound(driver, settings_dropdown_xpath)) {
////			if(userSignedIn.getText().trim().equalsIgnoreCase(pUsername)) {
//				isUserLoggedIn = true;
////			}
//		}	
		
		if(ActionHelper.waitForElementToBeVisible(driver, settings_button, 10)) {
			isUserLoggedIn = true;
		}
		return isUserLoggedIn;
	}

}