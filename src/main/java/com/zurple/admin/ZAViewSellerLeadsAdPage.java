/**
 * 
 */
package com.zurple.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author darrraqi
 *
 */
public class ZAViewSellerLeadsAdPage extends Page{
	
	String ads_count = "//table[@id='view_sellerleadads']/tbody/tr";
	
	String ad_date = "//table[@id='view_sellerleadads']/tbody/tr["+FrameworkConstants.DYNAMIC_VARIABLE+"]/td[@class=' dtr-control']";
	
	String package_id = "//table[@id='view_sellerleadads']/tbody/tr["+FrameworkConstants.DYNAMIC_VARIABLE+"]/td/a[contains(@href,'package_id')]";

	String admin_id = "//table[@id='view_sellerleadads']/tbody/tr["+FrameworkConstants.DYNAMIC_VARIABLE+"]/td/a[contains(@href,'admin_id')]";
	
	String data_to_verify = "//table[@id='view_sellerleadads']/tbody/tr["+FrameworkConstants.DYNAMIC_VARIABLE+"]/td";
	
	public ZAViewSellerLeadsAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public int getAdsCount() {
		return ActionHelper.getListOfElementByXpath(driver, ads_count).size();
	}
	public boolean verifyAdDate(String pDate, String pAdIndex) {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, pDate, pAdIndex)).contains(pDate);
	}
	public boolean verifyAdType(String pAdType, String pAdIndex) {
		return verifyData(pAdType, pAdIndex);
	}
	public boolean verifyAdStatus(String pAdStatus, String pAdIndex) {
		return verifyData(pAdStatus, pAdIndex);
	}
	public boolean verifyAdBudget(String pAdBudget, String pAdIndex) {
		return verifyData(pAdBudget, pAdIndex);
	}
	public boolean verifyCarouselStatus(String pCarouselStatus, String pAdIndex) {
		return verifyData(pCarouselStatus, pAdIndex);
	}
	public boolean verifyCarouselDownloadStatus(String pCarouselStatus, String pAdIndex) {
		return verifyData(pCarouselStatus, pAdIndex);
	}
	public boolean verifyCarouselVideoStatus(String pCarouselVideo, String pAdIndex) {
		return verifyData(pCarouselVideo, pAdIndex);
	}
	private boolean verifyData(String pDataToVerify,String pIndex) {
		boolean isFound = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver,getDynamicXpath(data_to_verify, pIndex));
		for(WebElement element: list) {
			if(ActionHelper.getText(driver, element).contains(pDataToVerify)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	private WebElement getDynamicWebElement(String pXpath, String pIndex) {
		return ActionHelper.getDynamicElement(driver, pXpath, pIndex);
	}
	private String getDynamicXpath(String pXpath, String pIndex) {
		return ActionHelper.getDynamicElementXpath(driver, pXpath, pIndex);
	}
}
