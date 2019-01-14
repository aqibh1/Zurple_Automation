package com.z57.site.v2;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import resources.forms.z57.SearchForm;

public class HomeSearchPage extends Page{

	WebDriver localWebDriver;
	SearchForm searchForm;

	public HomeSearchPage(WebDriver pWebDriver) {
		localWebDriver=pWebDriver;
		searchForm = new SearchForm(pWebDriver);
		PageFactory.initElements(localWebDriver, this);
	}
	
	public SearchForm getSearchForm() {
		if(searchForm!=null) {
			return searchForm;
		}else {
			return new SearchForm(localWebDriver);
		}
	}

	public HomeSearchPage(WebDriver pWebDriver, String pSourceUrl) {
		url=pSourceUrl;
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
