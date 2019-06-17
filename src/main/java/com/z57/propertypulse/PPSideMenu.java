package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PPSideMenu extends Page{
	String sideMenu_xpath = "//ul[@class='nav nav-tabs']/descendant::a";
	
	PPSideMenu(){
		
	}
	public PPSideMenu(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean goToSubMenu(String pSubMenu) {
		boolean isClicked = false;
		List<WebElement> submenu_list = actionHelper.getListOfElementByXpath(sideMenu_xpath);
		
		for(WebElement element: submenu_list) {
			if(element.getText().trim().contains(pSubMenu)) {
				isClicked = actionHelper.Click(element);
				break;
			}
		}
		return isClicked;	
	}
}
