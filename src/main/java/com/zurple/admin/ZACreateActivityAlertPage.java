/**
 * 
 */
package com.zurple.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZACreateActivityAlertPage extends Page{
	
	@FindBy(xpath="//h1[text()='Create Activity Alert']")
	WebElement activity_alert_heading;
	
	@FindBy(id="alerttype")
	WebElement alertType_dropdown_button;
	
	@FindBy(id="package")
	WebElement package_dropdown_button;
	
	@FindBy(id="admin")
	WebElement admin_dropdown_button;
	
	@FindBy(id="user")
	WebElement user_dropdown_button;
	
	@FindBy(id="triggerdate")
	WebElement triggerdate_input;
	
	@FindBy(id="submit")
	WebElement create_alert_button;
	
	@FindBy(id="property")
	WebElement property_dropdown;
	
	@FindBy(id="city")
	WebElement city_dropdown;
	
	@FindBy(xpath="//h2[text()='Alert Successfully created']")
	WebElement successful_alert;
	
	public ZACreateActivityAlertPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isActivityAlertPage() {
		return ActionHelper.waitForElementToBeVisible(driver, activity_alert_heading, 30);
	}
	public boolean selectAlertType(String pAlertType) {
		return ActionHelper.selectDropDownOption(driver, alertType_dropdown_button, "", pAlertType);
	}
	public boolean selectPackage(String pPackageId) {
		return ActionHelper.clickAndSelectFromDropdownByValue(driver, package_dropdown_button, pPackageId);
	}
	public boolean selectAdmin(String pAdminId) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, admin_dropdown_button, 10)) {
			isSelected = ActionHelper.clickAndSelectFromDropdownByValue(driver,admin_dropdown_button, pAdminId);
		}
		return isSelected;
	}
	public boolean selectUser(String pUserID) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, user_dropdown_button, 10)) {
			isSelected = ActionHelper.clickAndSelectFromDropdownByValue(driver,user_dropdown_button, pUserID);
		}
		return isSelected;
	}
	public boolean selectCity(String pCityName) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, city_dropdown, 10)) {
			isSelected = ActionHelper.selectDropDownOption(driver, city_dropdown, "", pCityName);
		}
		return isSelected;
	}
	public boolean typeTriggerDate(String pTriggerDate) {
		return ActionHelper.Type(driver, triggerdate_input, pTriggerDate);
	}
	public boolean clickOnCreateAlertButton() {
		return ActionHelper.Click(driver, create_alert_button);
	}
	public HashMap<String,String> selectAndGetPropertyIndexAndValues() {
		HashMap<String,String> keyValuePair = new HashMap<String,String>();
		if(ActionHelper.waitforDropdownToBePopulated(driver, property_dropdown, 10)) {
			List<WebElement> list_of_options = new ArrayList<WebElement>();
			list_of_options = property_dropdown.findElements(By.tagName("option"));
			int index = generateRandomInt(list_of_options.size());
			WebElement element = list_of_options.get(index);
			keyValuePair.put("property-address", element.getAttribute("property-address"));
			keyValuePair.put("property-city_name", element.getAttribute("property-city_name"));
			keyValuePair.put("property-price", element.getAttribute("property-price"));
			keyValuePair.put("property-city_state", element.getAttribute("property-city_state"));
			keyValuePair.put("index", String.valueOf(index));
			index = index==0?index+1:index;
			if(!ActionHelper.clickAndSelectByIndex(driver, property_dropdown, "//select[@id='property']/option", index)) {
				return null;
			}
		}
		return keyValuePair;
}
	public boolean isSuccessMessageDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, successful_alert, 30);
	}
}