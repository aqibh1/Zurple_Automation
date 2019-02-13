package com.z57.site.v2;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.forms.z57.SearchForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class HomeSearchPage extends Page{

	private WebDriver localWebDriver;
	private SearchForm searchForm;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement page_title;
	
	public HomeSearchPage(WebDriver pWebDriver) {
		driver=pWebDriver;
		searchForm = new SearchForm(pWebDriver);
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
		searchForm=new SearchForm(pWebDriver);
		localWebDriver=pWebDriver;
		PageFactory.initElements(localWebDriver, this);
	}
	
	public boolean isHomeSearchPage() {
		return ActionHelper.getText(driver, page_title).contains(FrameworkConstants.HomeSearchPageTitle);
	}
	
	public void goBack() {
		ActionHelper.BackPage(driver);
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
