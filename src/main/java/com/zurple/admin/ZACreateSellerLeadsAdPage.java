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
import resources.utility.FrameworkConstants;

/**
 * @author darrraqi
 *
 */
public class ZACreateSellerLeadsAdPage extends Page{
	
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
	
	@FindBy(xpath="//h1[text()='Seller Lead Ads']")
	WebElement seller_lead_ad_heading;
	
	@FindBy(xpath="//div/li[text()=' Ad Saved successfully ']")
	WebElement success_message;
	
	@FindBy(xpath="//input[@value='paused']")
	WebElement paused_radio_button;
	
	String carousel_image_xpath ="image_"+FrameworkConstants.DYNAMIC_VARIABLE+"_carousel";
	
	public ZACreateSellerLeadsAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeAndSelectPackage(String pPackage) {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, package_dropdown) && ActionHelper.Type(driver, package_input_search, pPackage)) {
			ActionHelper.staticWait(3);
			isSuccessful = ActionHelper.Type(driver, package_input_search, Keys.ENTER);
		}
		return isSuccessful;
	}
	public boolean typeAndSelectAdmin(String pPackage) {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, admin_dropdown) && ActionHelper.Type(driver, package_input_search, pPackage)) {
			ActionHelper.staticWait(3);
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
		boolean isSuccess = false;
		switch(pCarousel) {
		case "IDX images":
			break;
		case "Custom Images":
			
			break;
		case "Default Images":
			isSuccess = ActionHelper.selectDropDownOption(driver, cma_carousel, "", pCarousel);
			break;
		}
		return isSuccess;
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
	public boolean isSellerLeadAdPage() {
		return ActionHelper.waitForElementToBeVisible(driver, seller_lead_ad_heading, 15);
	}
	public boolean isSuccessMessageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, success_message, 10);
	}
	public boolean clickPausedRadioButton() {
		boolean isSelected = false;
		if(!ActionHelper.isElementSelected(driver, paused_radio_button)) {
			isSelected = ActionHelper.Click(driver, paused_radio_button);
		}else {
			isSelected = true;
		}
		return isSelected;
	}
	private boolean uploadCarouselImages(String pImagesPath) {
		boolean isSuccess = false;
		String [] images_path = pImagesPath.split(",");
		for(int i=1;i<images_path.length;i++) {
			
		}
		return isSuccess;
	}
}
