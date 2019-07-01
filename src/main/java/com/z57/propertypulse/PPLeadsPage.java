package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.forms.pp.PPAddLeadForm;
import resources.utility.FrameworkConstants;

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
	
	@FindBy(xpath="//i[@class='icon-arrow-down pull-right']")
	WebElement searchFilter_arrow;
	
	@FindBy(xpath="//ul[@class='select2-results']/descendant::div[text()='Manual Entry']")
	WebElement manualEntry;
	
	@FindBy(xpath="//div[@id='s2id_filterSources']/descendant::li[@class='select2-search-field']/input")
	WebElement selectSources;
	
	@FindBy(xpath="//div[@id='s2id_filterStatus']/descendant::li[@class='select2-search-field']/input")
	WebElement selectStatus;
	
	String list_status = "//div[@id='select2-drop']/descendant::ul[@class='select2-results']/*/div";
	
	@FindBy(xpath="//button[@id='apply_filter']")
	WebElement applyFilter_button;
	
	String leadExistInLeadsTable = "//div[@id='leadsTable_wrapper']/descendant::strong[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//button[@id='reset_filter']")
	WebElement resetFilter_button;
	
	@FindBy(xpath="//div[@id='leadsTable_filter']/descendant::input[@type='search']")
	WebElement search_input;
	
	@FindBy(xpath="//a[@title='Edit Lead']/i")
	WebElement editLead_button;
	
	public PPLeadsPage() {
		// TODO Auto-generated constructor stub
	}
	public PPLeadsPage(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
		setAddNewLeadForm();
	}
	
	public PPAddLeadForm getAddNewLeadForm() {
		return addNewLeadForm;
	}
	public void setAddNewLeadForm() {
		addNewLeadForm = new PPAddLeadForm(driver);
	}
	
	public boolean clickOnManualEntryDropDown() {
		boolean isSuccessful=false;
		if(actionHelper.Click( addimport_button)) {
			isSuccessful = actionHelper.Click( manaulEntry_button);
		}
		return isSuccessful;
	}
	
	public boolean clickOnImportCSVDropDown() {
		boolean isSuccessful=false;
		if(actionHelper.Click( addimport_button)) {
			isSuccessful = actionHelper.Click( importCsv_button);
		}
		return isSuccessful;
	}
	
	public boolean clickOnImportEmailContactsDropDown() {
		boolean isSuccessful=false;
		if(actionHelper.Click( addimport_button)) {
			isSuccessful = actionHelper.Click( importEmailContacts_button);
		}
		return isSuccessful;
	}
	public boolean isLeadPage() {
		return actionHelper.isElementVisible( leads_title);
	}
	
	public boolean clickOnFilterArrow() {
		return actionHelper.Click(searchFilter_arrow );
	}
	
	public boolean selectSourcesOption() {
		boolean isSuccess= false;
		if(actionHelper.Click( selectSources)) {
			isSuccess = actionHelper.Click( manualEntry);
		}
		return isSuccess;
	}
	
	public boolean selectStatus(String pStatus) {
		boolean isSuccess= false;
		if(actionHelper.Click( selectStatus)) {
			List<WebElement> list = driver.findElements(By.xpath(list_status));
			for(WebElement element: list) {
				if(actionHelper.getText( element).equalsIgnoreCase(pStatus)) {
					isSuccess=actionHelper.Click( element);
				}
			}
		}
		return isSuccess;
	}
	
	public boolean clickOnApplyFilterButton() {
		return actionHelper.Click( applyFilter_button);
	}
	
	public boolean isLeadExistInTable(String pLead) {
		return actionHelper.waitForElementToBeLocated( actionHelper.getDynamicElementXpath( leadExistInLeadsTable, pLead),15);
	}
	
	public boolean clickOnResetFilterButton() {
		return actionHelper.Click( resetFilter_button);
	}
	public boolean typeInSearch(String pStringToSearch) {
		boolean result = actionHelper.waitForElementToBeVisible( search_input, 10);
		if(result) {
			result= actionHelper.ClearAndType( search_input, pStringToSearch);
		}
		return result;
	}
	public boolean clickOnEditButton() {
		return actionHelper.Click( editLead_button);
	}
	

}
