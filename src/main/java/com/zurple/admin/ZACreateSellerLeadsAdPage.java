/**
 * 
 */
package com.zurple.admin;

import java.util.ArrayList;
import java.util.List;

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
	
	@FindBy(xpath="//form[@id='sl_ad_form']/*/button[text()='Submit']")
	WebElement submit_button;
	
	@FindBy(xpath="//h1[text()='Seller Lead Ads']")
	WebElement seller_lead_ad_heading;
	
	@FindBy(xpath="//div/li[text()=' Ad Saved successfully ']")
	WebElement success_message;
	
	@FindBy(xpath="//input[@value='paused']")
	WebElement paused_radio_button;
	
	String carousel_image_xpath ="//input[@id='image_"+FrameworkConstants.DYNAMIC_VARIABLE+"_carousel']";
	@FindBy(xpath="//div[@id='customModal_carousel']/descendant::button[text()='select images']")
	WebElement select_image_carousel_button;
	
	String carousel_video_xpath ="//input[@id='image_"+FrameworkConstants.DYNAMIC_VARIABLE+"_video']";
	@FindBy(xpath="//div[@id='customModal_video']/descendant::button[text()='select images']")
	WebElement select_video_carousel_button;
	
	String carousel_carouselsearch_xpath ="//input[@id='image_"+FrameworkConstants.DYNAMIC_VARIABLE+"_carouselsearch']";
	@FindBy(xpath="//div[@id='customModal_carouselsearch']/descendant::button[text()='select images']")
	WebElement select_image_download_button;
	
	String idx_images_caraousel_xpath = "//div[@id='idxModal_carousel']/descendant::div[@class='thumbnail']/img";
	@FindBy(xpath="//div[@id='idxModal_carousel']/descendant::button[text()='select images']")
	WebElement select_image_idx_carousel_button;
	
	String idx_images_video_xpath = "//div[@id='idxModal_video']/descendant::div[@class='thumbnail']/img";
	@FindBy(xpath="//div[@id='idxModal_video']/descendant::button[text()='select images']")
	WebElement select_image_idx_video_button;
	
	String idx_images_download_xpath = "//div[@id='idxModal_carouselsearch']/descendant::div[@class='thumbnail']/img";
	@FindBy(xpath="//div[@id='idxModal_carouselsearch']/descendant::button[text()='select images']")
	WebElement select_image_idx_download_button;
	
	@FindBy(id="sl_ad_form")
	WebElement form;
	
	@FindBy(id="ad_naming_format")
	WebElement seller_lead_format;
	
	String images_alert_message = "//div[@class='alert alert-danger']/li[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
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
	public boolean clickAndSelectCarousel(String pCarousel, String pImagesPath) {
		boolean isSuccess = false;
		switch(pCarousel) {
		case "IDX images":
			ActionHelper.selectDropDownOption(driver, cma_carousel, "", pCarousel);
			ActionHelper.staticWait(5);
			isSuccess = selectIDXImages(idx_images_caraousel_xpath) && ActionHelper.Click(driver, select_image_idx_carousel_button);
			break;
		case "Custom Images":
			ActionHelper.selectDropDownOption(driver, cma_carousel, "", pCarousel);
			ActionHelper.staticWait(5);
			isSuccess = uploadImages(carousel_image_xpath, pImagesPath) && ActionHelper.Click(driver, select_image_carousel_button);
			break;
		case "Default Images":
			isSuccess = ActionHelper.selectDropDownOption(driver, cma_carousel, "", pCarousel);
			break;
			
			
		}
		return isSuccess;
	}
	public boolean clickAndSelectCMAVideo(String pCarousel,String pImagesPath) {
		boolean isSuccess = false;
		switch(pCarousel) {
		case "IDX images":
			ActionHelper.selectDropDownOption(driver, cma_video, "", pCarousel);
			ActionHelper.staticWait(5);
			isSuccess = selectIDXImages(idx_images_video_xpath) && ActionHelper.Click(driver, select_image_idx_video_button);;
			break;
		case "Custom Images":
			ActionHelper.selectDropDownOption(driver, cma_video, "", pCarousel);
			ActionHelper.staticWait(5);
			isSuccess = uploadImages(carousel_video_xpath, pImagesPath) && ActionHelper.Click(driver, select_video_carousel_button);
			break;
		case "Default Images":
			isSuccess = ActionHelper.selectDropDownOption(driver, cma_video, "", pCarousel);
			break;
		case "Use CMA Carousel Images":
			isSuccess = ActionHelper.selectDropDownOption(driver, cma_video, "", pCarousel);
			break;
		}
		return isSuccess;
	}
	public boolean clickAndSelectDownloadCarousel(String pCarousel,String pImagesPath) {
		boolean isSuccess = false;
		switch(pCarousel) {
		case "IDX images":
			ActionHelper.selectDropDownOption(driver, download_carousel, "", pCarousel);
			ActionHelper.staticWait(5);
			isSuccess = selectIDXImages(idx_images_download_xpath) && ActionHelper.Click(driver, select_image_idx_download_button);
			break;
		case "Custom Images":
			ActionHelper.selectDropDownOption(driver, download_carousel, "", pCarousel);
			ActionHelper.staticWait(5);
			isSuccess = uploadImages(carousel_carouselsearch_xpath, pImagesPath) && ActionHelper.Click(driver, select_image_download_button);
			break;
		case "Default Images":
			isSuccess = ActionHelper.selectDropDownOption(driver, download_carousel, "", pCarousel);
			break;
		case "Use CMA Carousel Images":
			isSuccess = ActionHelper.selectDropDownOption(driver, download_carousel, "", pCarousel);
			break;
		}
		return isSuccess;
	}
	public boolean clickOnSubmitButton() {
		ActionHelper.staticWait(5);
		ActionHelper.MoveToElement(driver, submit_button);
		return ActionHelper.Type(driver, submit_button, Keys.ENTER);

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
	public boolean isPackageAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isAdminAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isBudgetLessThanHundredAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isBudgetAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isZipCodeAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isCityAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isAdTypeAlertVisible(String pMessageToVerify) {
		return verifyAndHandleAlert(pMessageToVerify);	
	}
	public boolean isAlertMessageVisible(String pAlertMessage) {
		return ActionHelper.getDynamicElement(driver, images_alert_message, pAlertMessage)!=null;
	}
	private boolean verifyAndHandleAlert(String pTextToVerify) {
		boolean isAlertVerified = false;
		if(ActionHelper.getAlertText(driver).equalsIgnoreCase(pTextToVerify)) {
			isAlertVerified = true;
		}
		ActionHelper.handleDisableAdAlert(driver);
		ActionHelper.staticWait(3);
		return isAlertVerified;
	}
	public boolean selectAdFormat(String pFormat) {
		return ActionHelper.selectDropDownOption(driver, seller_lead_format, "", pFormat);
	}
	private boolean uploadImages(String pXpath,String pImagesPath) {
		boolean isSuccess = true;
		String [] images_path = pImagesPath.split(",");
		for(int i=0;i<images_path.length;i++) {
			pImagesPath = images_path[i];
//			String l_xpath = pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, String.valueOf(i+1));
			if(!ActionHelper.Type(driver, ActionHelper.getDynamicElement(driver, pXpath, String.valueOf(i+1)), pImagesPath)){
				isSuccess = false;
				break;
			}
		}
		return isSuccess;
	}
	private List<Integer> getRandomNumbersList(int pUpperLimit) {
		List<Integer> l_random_num_list = new ArrayList<Integer>();
		for(int i=0;i<5;i++) {
			int l_rand = generateRandomInt(pUpperLimit);
			l_random_num_list.add(i, l_rand);
		}
		return l_random_num_list;
	}
	private boolean selectIDXImages(String pImageXpath) {
		boolean isClicked = true;
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, pImageXpath);
		List<Integer> l_rand_list = getRandomNumbersList(list_of_elements.size());
		for(int i=0;i<l_rand_list.size();i++) {
			if(!ActionHelper.Click(driver, list_of_elements.get(l_rand_list.get(i)))) {
				isClicked = false;
				break;
			}
		}
		return isClicked;
	}
	
}
