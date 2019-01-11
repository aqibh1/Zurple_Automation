package com.z57.site.v2.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.z57.site.v2.Page;

public class HomeSearchPage extends Page{

	private static String DYNAMIC_VARIABLE="[--Dynamic Variable--]";
	
	WebDriver localWebDriver;

	//Search Input Field
	@FindBy(xpath="//div[@class='input-group']/descendant::ul[@class='select2-choices']/descendant::input[@type='text']")
	WebElement searchField_input;

	//Drop down list when anything is entered in input field.
	@FindBy(xpath="//ul[@class='select2-choices']/descendant::input[@id='s2id_autogen1']")
	WebElement selectOption_dropDownList;

	//Search button
	@FindBy(xpath="//button[@id='main_search_submit']")
	WebElement search_button;

	//Search by dropdown
	@FindBy(xpath="//select[@id='search_location_type_main']")
	WebElement searchby_dropdown;

	//Select the search by option
	@FindBy(xpath="//option[@id='[--Dynamic Variable--]']")
	WebElement searchby_option;
	String searchByOption_xpath="//option[@id='"+DYNAMIC_VARIABLE+"']";

	//Price Low drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_low']")
	WebElement priceLow_dropDown;

	//Price Low option
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_low']/option[@value='[--Dynamic Variable--]']")
	WebElement priceLow_options;
	
	//Price Low drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_max']")
	WebElement priceMax_dropDown;

	//Price Low option
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_max']/option[@value='[--Dynamic Variable--]']")
	WebElement priceMax_options;
	
	//Beds drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bedrooms']")
	WebElement beds_dropDown;

	//Beds option
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bedrooms']/option[@value='[--Dynamic Variable--]']")
	WebElement beds_options;
	
	//Baths drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bathrooms']")
	WebElement bathrooms_dropDown;

	//Baths option
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bathrooms']/option[@value='[--Dynamic Variable--]']")
	WebElement bathrooms_options;

	public HomeSearchPage(WebDriver pWebDriver) {
		localWebDriver=pWebDriver;
		PageFactory.initElements(localWebDriver, this);
	}

	public HomeSearchPage(WebDriver pWebDriver, String pSourceUrl) {
		url=pSourceUrl;
		localWebDriver=pWebDriver;
		PageFactory.initElements(localWebDriver, this);
	}
	
	public boolean clickOnInputField() {
		if(searchField_input.isDisplayed()) {
			searchField_input.click();
			return true;
		}else {
			return false;
		}
	}
	public boolean clickOnSuggestedOptionsInInputField() {
		if(searchField_input.isDisplayed()) {
			searchField_input.sendKeys(Keys.ARROW_DOWN);
			searchField_input.sendKeys(Keys.ENTER);
//			searchField_input.click();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean typeInInputField(String pStringToType) {
		boolean isTypeSuccessful=false;
		try {
		
		if(searchField_input.isDisplayed()) {
			//searchField_input.clear();
			searchField_input.sendKeys(pStringToType);
			isTypeSuccessful= true;
		}
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isTypeSuccessful;
	}
	
	public boolean clickOnSearchButton() {
		if(search_button.isDisplayed()) {
			search_button.click();
			return true;
		}else {
			return false;
		}
	}

	public boolean clickOnSearchByOption(String pOption) {
		String pDynamicXpath="";
		boolean isClickSuccessful=false;
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		//Selects dynamically which option to select.
		switch(pOption) {

		case "Zip":
			searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_ZI_main");
			break;
			
		case "City":
			searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_LC_main");
			break;

		case "Address":
			searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_ADDRESS_main");
			break;

		case "Neighborhood":
			searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_NH_main");
			break;

		case "MLS":
			searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_MLSID_main");
			break;

		default:
			searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_ZI_main");
			break;
		}
	
		if(searchby_option!=null) {
			searchby_option.click();
			isClickSuccessful = true;
		}else {
			isClickSuccessful = false;
		}
		return isClickSuccessful;
	}
	
	public boolean clickOnPriceLowOption(String pPrice) {
		String pDynamicXpath="";
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		priceLow_dropDown.click();
		pDynamicXpath=priceLow_options.toString().replaceAll("[--Dynamic Variable--]",pPrice);
		priceLow_options= localWebDriver.findElement(By.xpath(pDynamicXpath));
		priceLow_options.click();
		return wait.until(ExpectedConditions.invisibilityOf(priceLow_options));
	}
	
	public boolean clickOnPriceMaxOption(String pPrice) {
		String pDynamicXpath="";
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		priceMax_dropDown.click();
		pDynamicXpath=priceMax_options.toString().replaceAll("[--Dynamic Variable--]",pPrice);
		priceMax_options= localWebDriver.findElement(By.xpath(pDynamicXpath));
		priceMax_options.click();
		return wait.until(ExpectedConditions.invisibilityOf(priceMax_options));
	}
	
	public boolean clickOnBedsOption(String pPrice) {
		String pDynamicXpath="";
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		beds_dropDown.click();
		pDynamicXpath=beds_options.toString().replaceAll("[--Dynamic Variable--]",pPrice);
		beds_options= localWebDriver.findElement(By.xpath(pDynamicXpath));
		beds_options.click();
		return wait.until(ExpectedConditions.invisibilityOf(beds_options));
	}

	public boolean clickOnBathsOption(String pPrice) {
		String pDynamicXpath="";
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		bathrooms_dropDown.click();
		pDynamicXpath=bathrooms_options.toString().replaceAll("[--Dynamic Variable--]",pPrice);
		bathrooms_options= localWebDriver.findElement(By.xpath(pDynamicXpath));
		bathrooms_options.click();
		return wait.until(ExpectedConditions.invisibilityOf(bathrooms_options));
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
	
	private WebElement getDynamicElement(String pXpath,String pDynamicVariable) {
		return localWebDriver.findElement(By.xpath(pXpath.replace(DYNAMIC_VARIABLE, pDynamicVariable)));
	}

}
