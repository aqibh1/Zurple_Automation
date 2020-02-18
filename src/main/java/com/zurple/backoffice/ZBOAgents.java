package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOAgents extends Page{

	@FindBy(className="col-sm-4")
	WebElement manage_agents_label;
	
	public ZBOAgents() {
		
	}
	
	public ZBOAgents(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle(){
		ActionHelper.waitForElementToBeVisible(driver, manage_agents_label, 30);
		return ActionHelper.getText(driver, manage_agents_label).trim();
	}
}
