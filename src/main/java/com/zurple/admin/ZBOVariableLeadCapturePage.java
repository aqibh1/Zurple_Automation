package com.zurple.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOVariableLeadCapturePage extends Page {
	
	@FindBy(xpath="//div[@class='form-element-input']/descendant::select[@id='pageviews_before_lead_capture']")
	WebElement select_pageviews;
	
	@FindBy(xpath="//div[@class='form-element-input']/descendant::select[@id='lead_capture_method']")
	WebElement select_method;
		
	@FindBy(id="update")
	WebElement submit_button;
	
	@FindBy(className="close")
	WebElement close_button;
	
	@FindBy(className="btn-zurple-tinted")
	WebElement case0;
		
	String case1 = "property-photo-grid";
	
	public ZBOVariableLeadCapturePage() {
		
	}
	
	public ZBOVariableLeadCapturePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean selectPageViews(String pageViews) {
		return ActionHelper.clickAndSelectFromDropdownByValue(driver, select_pageviews, pageViews);
	}
	
	public boolean selectMethod(String methodType) {
		return ActionHelper.clickAndSelectFromDropdownByValue(driver, select_method, methodType);
	}
	
	public boolean SubmitVLCSettings() {
		return ActionHelper.Click(driver, submit_button);
	}
	
}
	
	

