package com.z57.site.v2;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import resources.forms.z57.SearchForm;

public class HomeSearchPage extends Page{

	private WebDriver localWebDriver;
	private SearchForm searchForm;

	public HomeSearchPage(WebDriver pWebDriver) {
		localWebDriver=pWebDriver;
		searchForm = new SearchForm(pWebDriver);
		PageFactory.initElements(localWebDriver, this);
	}
	
	public SearchForm getSearchForm() {
		if(searchForm!=null) {
			return searchForm;
		}else {
			searchForm=new SearchForm(localWebDriver);
			return searchForm;
		}
	}

	public HomeSearchPage(WebDriver pWebDriver, String pSourceUrl) {
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
