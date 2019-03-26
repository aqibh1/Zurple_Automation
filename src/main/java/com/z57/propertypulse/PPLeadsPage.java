package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.pp.PPAddLeadForm;
import resources.utility.ActionHelper;
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
	public PPLeadsPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		setAddNewLeadForm();
		PageFactory.initElements(driver, this);
	}
	
	public PPAddLeadForm getAddNewLeadForm() {
		return addNewLeadForm;
	}
	public void setAddNewLeadForm() {
		addNewLeadForm = new PPAddLeadForm(driver);
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
	
	public boolean clickOnFilterArrow() {
		return ActionHelper.Click(driver,searchFilter_arrow );
	}
	
	public boolean selectSourcesOption() {
		boolean isSuccess= false;
		if(ActionHelper.Click(driver, selectSources)) {
			isSuccess = ActionHelper.Click(driver, manualEntry);
		}
		return isSuccess;
	}
	
	public boolean selectStatus(String pStatus) {
		boolean isSuccess= false;
		if(ActionHelper.Click(driver, selectStatus)) {
			List<WebElement> list = driver.findElements(By.xpath(list_status));
			for(WebElement element: list) {
				if(ActionHelper.getText(driver, element).equalsIgnoreCase(pStatus)) {
					isSuccess=ActionHelper.Click(driver, element);
				}
			}
		}
		return isSuccess;
	}
	
	public boolean clickOnApplyFilterButton() {
		return ActionHelper.Click(driver, applyFilter_button);
	}
	
	public boolean isLeadExistInTable(String pLead) {
		return ActionHelper.waitForElementToBeLocated(driver, ActionHelper.getDynamicElementXpath(driver, leadExistInLeadsTable, pLead),15);
	}
	
	public boolean clickOnResetFilterButton() {
		return ActionHelper.Click(driver, resetFilter_button);
	}
	public boolean typeInSearch(String pStringToSearch) {
		boolean result = ActionHelper.waitForElementToBeVisible(driver, search_input, 10);
		if(result) {
			result= ActionHelper.ClearAndType(driver, search_input, pStringToSearch);
		}
		return result;
	}
	public boolean clickOnEditButton() {
		return ActionHelper.Click(driver, editLead_button);
	}
	

}
