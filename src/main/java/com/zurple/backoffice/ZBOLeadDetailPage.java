package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ZBOLeadDetailPage extends Page{
	
	@FindBy(xpath="//div[@class='row']/descendant::h3[text()='Lead Detail']")
	WebElement leadDetailHeading;
	
	String propertyViewed = "//div[@id='property-views-stats']/descendant::td";
	
	@FindBy(xpath="//div[@id='property-views-stats']/descendant::td/div[text()='Loading...']")
	WebElement loading;
	
	String lead_name = "//div[@id='lead-details-main']/descendant::h2";
	
	String lead_email_xpath = "//span[@class='lead-details-detail']/descendant::a[@title='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//span[@class='lead-details-detail wrap']")
	WebElement lead_address;
	
	String prop_details_xpath = "//span[@class='lead-details-detail']";
	
	@FindBy(xpath="//div[@id='z-lead-notes']/descendant::td[@headers='yui-dt0-th-note ']/div[@class='yui-dt-liner']")
	WebElement notes_text;
	
	public ZBOLeadDetailPage() {
		
	}
	
	public ZBOLeadDetailPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLeadDetailPage() {
		return ActionHelper.waitForElementToBeVisible(driver, leadDetailHeading, 30);
	}
	
	public boolean isPropertyTracked(String pPropTitle) {
		isLoading();
		boolean isPropTracked = false;
		List<WebElement> list_of_props = ActionHelper.getListOfElementByXpath(driver, propertyViewed);
		for(WebElement element: list_of_props) {
			if(!element.getText().trim().isEmpty() && pPropTitle.contains(element.getText().trim())) {
				isPropTracked = true;
				break;
			}
		}
		return isPropTracked;
		
	}
	private boolean isLoading() {
		return ActionHelper.waitForElementToBeDisappeared(driver, loading);
	}
	public boolean isLeadNameExist(String pName) {
		boolean isLeadExist = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, lead_name);
		for(WebElement element: list_element) {
			if(element.getText().trim().equalsIgnoreCase(pName)) {
				isLeadExist = true;		
				break;
			}
		}
		return isLeadExist;
	}
	public boolean verifyLeadAddress(String pStreet, String pCity, String pState, String pZip) {
		boolean isSuccess = true;
		String lAddress = ActionHelper.getText(driver, lead_address);
		String lAddress_array[] = lAddress.split(",");
		if(!pStreet.equalsIgnoreCase(lAddress_array[0])) {
			isSuccess = false;
		}if(!pCity.equalsIgnoreCase(lAddress_array[1].trim())) {
			isSuccess = false;
		}if(!pState.equalsIgnoreCase(lAddress_array[2].trim())) {
			isSuccess = false;
		}if(!pZip.equalsIgnoreCase(lAddress_array[3].trim())) {
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public boolean verifyProp(String pPropToVerify, String pPropValue) {
		boolean isVerified = false;
		List<WebElement> list_pf_element = ActionHelper.getListOfElementByXpath(driver, prop_details_xpath);
		for(WebElement element: list_pf_element) {
			List<WebElement> list_of_tags_i = element.findElements(By.tagName("i"));
			for(WebElement i_element: list_of_tags_i) {
				if(ActionHelper.getAttribute(i_element, "title").equalsIgnoreCase(pPropToVerify)) {
					if(element.getText().trim().replace(",", "").equalsIgnoreCase(pPropValue)) {
						isVerified = true;
						break;
					}
				}
			}
			if(isVerified) {
				break;
			}
		}
		return isVerified;
	}
	public boolean verifyMinPrice(String pMinPrice) {
		return verifyPropFromNotes("minimum price", pMinPrice.replace(",", "").replace("$", ""));
	}
	public boolean verifyMaxPrice(String pMaxPrice) {
		return verifyPropFromNotes("maximum price", pMaxPrice.replace(",", "").replace("$", ""));
	}
	public boolean verifyMinBeds(String pBeds) {
		return verifyPropFromNotes("minimum bedrooms", pBeds.replace("+", ""));
	}
	public boolean verifyMinBathrooms(String pBaths) {
		return verifyPropFromNotes("minimum bathrooms", pBaths.replace("+", ""));
	}
	public boolean verifyMinSqFeet(String pSqFeet) {
		return verifyPropFromNotes("minimum sqft", pSqFeet);
	}
	public boolean verifyLotSize(String pLotSize) {
		return verifyPropFromNotes("Added lot size", pLotSize);
	}
	public boolean verifyNeighborhood(String pNeighborhood) {
		return verifyPropFromNotes("Added neighborhood", pNeighborhood);
	}
	public boolean verifySchoolDistrict(String pSchoolDistrict) {
		return verifyPropFromNotes("Added school district", pSchoolDistrict);
	}
	public boolean verifyZipCode(String pZipCide) {
		return verifyPropFromNotes("Added zip code", pZipCide);
	}
	public boolean verifyCounty(String pCounty) {
		return verifyPropFromNotes("Added county", pCounty);
	}
	public boolean verifyFeatures(String pFeatures) {
		return verifyPropFromNotes("Added features", pFeatures);
	}
	public boolean verifyPropView(String pPropView) {
		return verifyPropFromNotes("Added property view", pPropView);
	}
	public boolean verifyPropStyle(String pPropStyle) {
		return verifyPropFromNotes("Added property style", pPropStyle);
	}
	
	private boolean verifyPropFromNotes(String pPropToVerify, String pValue) {
		boolean isVerified = false;
		List<WebElement> list_of_notes = ActionHelper.getListOfElementByXpath(driver, "//div[@id='z-lead-notes']/descendant::td[@headers='yui-dt0-th-note ']/div[@class='yui-dt-liner']");
		for(WebElement element_notes: list_of_notes) {
			String lNotes[] = ActionHelper.getText(driver, element_notes).split("\n");
			for (String lNote: lNotes) {
				if(lNote.contains(pPropToVerify)) {
					if(lNote.split(":")[1].trim().equalsIgnoreCase(pValue)) {
						isVerified = true;
						break;
					}
				}
			}
			if(isVerified) {
				break;
			}
		}
		return isVerified;
	}
}
