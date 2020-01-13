package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.blocks.z57.Pagination;
import resources.forms.z57.EmailSearchForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class RecentHomeSalesPage extends Page{
	Pagination pagination;
	private EmailSearchForm emailSearchForm;
	
	@FindBy(xpath="//div[@id='googleMap']")
	WebElement googleMap;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	@FindBy(xpath="//span[@id='ic_results_container']")
	WebElement results_span;
	
	@FindBy(xpath="//a[@id='ic_print_results_button']")
	WebElement print_result_button;
	
	@FindBy(xpath="//span[@id='ic_results_container']/descendant::a[text()='Email Search']")
	WebElement email_search_button;
	
	public RecentHomeSalesPage(WebDriver pWebDriver) {
		driver=pWebDriver;
		setPagination();
		setEmailSearchForm();
		PageFactory.initElements(driver, this);
	}
	
	public EmailSearchForm getEmailSearchForm() {
		return emailSearchForm;
	}

	public void setEmailSearchForm() {
		this.emailSearchForm = new EmailSearchForm(driver);
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination() {
		this.pagination = new Pagination(driver);
	}

	public boolean isGoogleMapDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, googleMap, 20);
	}
	
	public boolean isRecentHomeSalesPage() {
		boolean result = ActionHelper.getText(driver, pageTitle).equalsIgnoreCase(FrameworkConstants.RecentHomeSalesPageTitle)?true:false;
		return result;
	}

	public boolean isSearchSuccessful() {
		return ActionHelper.isElementVisible(driver, print_result_button);
		
	}
	public boolean clickOnEmailSearchButton() {
		return ActionHelper.Click(driver, email_search_button);
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
