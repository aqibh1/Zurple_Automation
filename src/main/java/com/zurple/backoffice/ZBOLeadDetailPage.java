package com.zurple.backoffice;

import java.util.List;

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
}
