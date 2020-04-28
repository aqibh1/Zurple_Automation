/**
 * 
 */
package com.z57.site.v2;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class SEMIDXAndNonIDXPage extends Page{

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
	
	@FindBy(id="price_max_select")
	WebElement max_price_select;
	
	String viewButton = "//button[@href and contains(text(),'View')]";
	
	@FindBy(id="main_search_submit")
	WebElement search_button;
	
	@FindBy(id="search_button")
	WebElement search_button2;
	
	public SEMIDXAndNonIDXPage() {
		
	}
	public SEMIDXAndNonIDXPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isSEMNonIDXPageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, max_price_select, 20);
	}
	public boolean isSEMNonIDXPageIsVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, search_button2, 10);
	}
	public boolean isSEMIDXPageIsVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, search_button, 10);
	}
	public boolean clickOnViewButton() {
		boolean isClicked = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, viewButton);
		if(list_element.size()>=1) {
			isClicked = ActionHelper.Click(driver, list_element.get(0));
		}
		return isClicked;
	}
}
