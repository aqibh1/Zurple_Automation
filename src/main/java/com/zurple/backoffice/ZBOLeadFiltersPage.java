package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;

public class ZBOLeadFiltersPage extends Page{

	@FindBy(xpath="//a[@href='/leads/index/ext/active1']")
	WebElement prospect_active_filter;
	
	@FindBy(xpath="//a[@href='/leads/index/ext/client']")
	WebElement client_active_filter;
	
	@FindBy(className="header-title")
	WebElement page_header;
	
	private ZBOLeadCRMPage leadCRMObject;
	
	public ZBOLeadFiltersPage() {
	}

	public ZBOLeadCRMPage getLeadsCRM() {
		return leadCRMObject;
	}
	public void setLeadsCRM() {
		this.leadCRMObject = new ZBOLeadCRMPage(driver);
	}
	
	public ZBOLeadFiltersPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setLeadsCRM();
		PageFactory.initElements(driver, this);
	}
	
	public boolean selectFilter(String filter) {
		if(filter.equalsIgnoreCase("prospect")) {
			return ActionHelper.Click(driver, prospect_active_filter);
		} else {
			return ActionHelper.Click(driver, client_active_filter);
		}	
	}

	public boolean searchStatusLead(String pNameEmail) {
		return leadCRMObject.searchLeadCustomizedList(pNameEmail);
	}
	
	public String pageTitle() {
		return ActionHelper.getText(driver, page_header);
	}
}
