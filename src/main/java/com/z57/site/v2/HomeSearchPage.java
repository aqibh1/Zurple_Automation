package com.z57.site.v2;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.z57.EmailSearchForm;
import resources.forms.z57.SaveSearchForm;
import resources.forms.z57.SearchForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class HomeSearchPage extends Page{

	private WebDriver localWebDriver;
	private SearchForm searchForm;
	private EmailSearchForm emailSearchForm;
	private SaveSearchForm saveSearchForm;

	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement page_title;
	
	@FindBy(xpath="//div[@id='google_map_prop_list_sidebar']/descendant::button[@data-target='#email_search_modal']")
	WebElement email_search_button;

	@FindBy(xpath="//div[@id='google_map_prop_list_sidebar']/descendant::button[@data-target='#save_search_modal']")
	WebElement save_search_button;

	public HomeSearchPage(WebDriver pWebDriver) {
		driver=pWebDriver;
		searchForm = new SearchForm(pWebDriver);
		setEmailSearchForm();
		setSaveSearchForm();
		PageFactory.initElements(driver, this);
	}
	
	public SearchForm getSearchForm() {
		if(searchForm!=null) {
			return searchForm;
		}else {
			searchForm=new SearchForm(driver);
			return searchForm;
		}
	}

	public HomeSearchPage(WebDriver pWebDriver, String pSourceUrl) {
		driver=pWebDriver;
		url=pSourceUrl;
		setEmailSearchForm();
		setSaveSearchForm();
		localWebDriver=pWebDriver;
		PageFactory.initElements(localWebDriver, this);
	}
	
	public EmailSearchForm getEmailSearchForm() {
		return emailSearchForm;
	}

	public void setEmailSearchForm() {
		emailSearchForm = new EmailSearchForm(driver);
	}

	public SaveSearchForm getSaveSearchForm() {
		return saveSearchForm;
	}

	public void setSaveSearchForm() {
		saveSearchForm = new SaveSearchForm(driver);
	}

	public boolean isHomeSearchPage() {
		return ActionHelper.getText(driver, page_title).contains(FrameworkConstants.HomeSearchPageTitle);
	}
	
	public void goBack() {
		ActionHelper.BackPage(driver);
	}
	public boolean clickOnEmailSearchButton() {
		return ActionHelper.Click(driver, email_search_button);
	}

	public boolean clickOnSaveSearchButton() {
		return ActionHelper.Click(driver, save_search_button);
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
	

}
