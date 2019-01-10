package com.z57.site.v2.helper;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionHelper {
	
	public void Click(WebDriver pWebDriver,WebElement pElement) {
		
//		assertTrue(pWebDriver.pElement.isDisplayed(), pElement+ " Element to click is not visible");
		pElement.click();
		
	}
	
	public void Type(WebElement pElement, String pTextToType) {
		assertTrue(pElement.isDisplayed(), pElement+ " Element to click is not visible");
		pElement.sendKeys(pTextToType);
	}
	
	public boolean WaitForElementToDisappear(WebElement pElement) {
		return true;
	}
}
