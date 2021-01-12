package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOLeadStatusFiltersPage extends Page{

	@FindBy(xpath="//a[@href='/leads/index/ext/active1']")
	WebElement prospect_active_filter;
	
	@FindBy(xpath="//a[@href='/leads/index/ext/new']")
	WebElement prospect_new_filter; 
	
	@FindBy(xpath="//a[@href='/leads/index/ext/prospect1']")
	WebElement prospect_unresponsive_filter;
	
	@FindBy(xpath="//a[@href='/leads/index/ext/prospect2']")
	WebElement prospect_communicated_filter;
	
	@FindBy(xpath="//a[@href='/leads/index/ext/client']")
	WebElement client_active_filter;

	@FindBy(xpath="//a[@href='/leads/index/ext/client2']")
	WebElement client_sold_filter;
	
	@FindBy(xpath="//a[@href='/leads/index/ext/inactive']")
	WebElement client_inactive_filter;
		
	@FindBy(className="header-title")
	WebElement page_header;
	
	@FindBy(id="lead_status")
	WebElement lead_prospect_dropdown;
	
	private ZBOLeadCRMPage leadCRMObject;
	
	public ZBOLeadStatusFiltersPage() {
	}

	public ZBOLeadCRMPage getLeadsCRM() {
		return leadCRMObject;
	}
	public void setLeadsCRM() {
		this.leadCRMObject = new ZBOLeadCRMPage(driver);
	}
	
	public ZBOLeadStatusFiltersPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setLeadsCRM();
		PageFactory.initElements(driver, this);
	}
	
	public void selectFilter(String filter) {
		switch(filter) {
		case "new":
			ActionHelper.Click(driver, prospect_new_filter);
			break;
		case "unresponsive":
			ActionHelper.Click(driver, prospect_unresponsive_filter);
			break;
		case "active":
			ActionHelper.Click(driver, prospect_active_filter);
			break;
		case "communicated":
			ActionHelper.Click(driver, prospect_communicated_filter);
			break;
		case "opportunity":
			ActionHelper.Click(driver, client_active_filter);
			break;
		case "sold":
			ActionHelper.Click(driver, client_sold_filter);
			break;
		case "inactive":
			ActionHelper.Click(driver, client_inactive_filter);
			break;
		}
	}

	public boolean searchStatusLead(String pNameEmail) {
		return leadCRMObject.searchLeadCustomizedList(pNameEmail);
	}
	
	public String pageTitle() {
		return ActionHelper.getText(driver, page_header);
	}
	
	public String getLeadStatus() {
		return leadCRMObject.getLeadProsepctSelectedValue();
	}
}
