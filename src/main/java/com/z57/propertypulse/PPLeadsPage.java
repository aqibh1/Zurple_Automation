package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.pp.PPAddLeadForm;
import resources.utility.ActionHelper;

public class PPLeadsPage extends Page{
	
	PPAddLeadForm addNewLeadForm;
	
	@FindBy(xpath="//a/i[@class='icon-plus']")
	WebElement addimport_button;
	
	@FindBy(xpath="//li/a[text()='Manual Entry']")
	WebElement manaulEntry_button;
	
	@FindBy(xpath="//li/a[text()='Import CSV']")
	WebElement importCsv_button;
	
	@FindBy(xpath="//li/a[text()='Import Email Contacts']")
	WebElement importEmailContacts_button;
	
	@FindBy(xpath="//div[@class='tab-content']/h1[text()='Leads']")
	WebElement leads_title;
	
	public PPLeadsPage() {
		// TODO Auto-generated constructor stub
	}
	public PPLeadsPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		addNewLeadForm = new PPAddLeadForm(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean clickOnManualEntryDropDown() {
		boolean isSuccessful=false;
		if(ActionHelper.Click(driver, addimport_button)) {
			isSuccessful = ActionHelper.Click(driver, manaulEntry_button);
		}
		return isSuccessful;
	}
	
	public boolean clickOnImportCSVDropDown() {
		boolean isSuccessful=false;
		if(ActionHelper.Click(driver, addimport_button)) {
			isSuccessful = ActionHelper.Click(driver, importCsv_button);
		}
		return isSuccessful;
	}
	
	public boolean clickOnImportEmailContactsDropDown() {
		boolean isSuccessful=false;
		if(ActionHelper.Click(driver, addimport_button)) {
			isSuccessful = ActionHelper.Click(driver, importEmailContacts_button);
		}
		return isSuccessful;
	}
	public boolean isLeadPage() {
		return ActionHelper.isElementVisible(driver, leads_title);
	}

}
