/**
 * 
 */
package com.zurple.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author darrraqi
 *
 */
public class ZASellerLeadsAdPage extends Page{
	
	@FindBy(id="select2-ad_package-container")
	WebElement package_dropdown;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	WebElement package_input_search;
	
	@FindBy(id="select2-ad_admin-container")
	WebElement admin_dropdown;
	
	@FindBy(id="ad_budget")
	WebElement ad_budget;
	
	@FindBy(id="ad_zipcode")
	WebElement ad_zipcode;
	
	@FindBy(id="ad_city")
	WebElement ad_city;
	
	@FindBy(id="ad_type")
	WebElement ad_type;
	
	@FindBy(id="carousel")
	WebElement cma_carousel;
	
	@FindBy(id="video")
	WebElement cma_video;
	
	@FindBy(id="carouselsearch")
	WebElement download_carousel;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement submit_button;
	
	public ZASellerLeadsAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeAndSelectPackage(String pPackage) {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, package_dropdown) && ActionHelper.Type(driver, package_input_search, pPackage)) {
			isSuccessful = ActionHelper.Type(driver, package_input_search, Keys.ENTER);
		}
		return isSuccessful;
	}
	public boolean typeAndSelectAdmin(String pPackage) {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, admin_dropdown) && ActionHelper.Type(driver, package_input_search, pPackage)) {
			isSuccessful = ActionHelper.Type(driver, package_input_search, Keys.ENTER);
		}
		return isSuccessful;
	}
	public boolean typeBudget(String pAdBudget) {
		return ActionHelper.ClearAndType(driver, ad_budget, pAdBudget);
	}
	public boolean typeZipCode(String pAdZipCode) {
		return ActionHelper.ClearAndType(driver, ad_zipcode, pAdZipCode);
	}
	public boolean typeAdCity(String pAdCity) {
		return ActionHelper.ClearAndType(driver, ad_city, pAdCity);
	}
	public boolean clickAndSelectAdType(String pAdType) {
		return ActionHelper.selectDropDownOption(driver, ad_type, "", pAdType);
	}
	public boolean clickAndSelectCarousel(String pCarousel) {
		return ActionHelper.selectDropDownOption(driver, cma_carousel, "", pCarousel);
	}
	public boolean clickAndSelectCMAVideo(String pCarousel) {
		return ActionHelper.selectDropDownOption(driver, cma_video, "", pCarousel);
	}
	public boolean clickAndSelectDownloadCarousel(String pImageType) {
		return ActionHelper.selectDropDownOption(driver, download_carousel, "", pImageType);
	}
	public boolean clickOnSubmitButton() {
		return ActionHelper.Click(driver, submit_button);
	}
}
