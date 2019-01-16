package com.z57.site.v2;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import resources.forms.z57.SearchForm;

public class HomeSearchPage extends Page{

	private WebDriver localWebDriver;
	private SearchForm searchForm;

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
