package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import us.zengtest1.Page;
import resources.alerts.zurple.backoffice.ZBONewListingCreateAdAlert;
import resources.utility.ActionHelper;

public class ZWVariableLeadCapturePage extends Page {
	
		@FindBy(id="update")
		WebElement submit_button;
		
		String close_button = "close";
		
		@FindBy(className="btn-zurple-tinted")
		WebElement case0;
		
		String case1 = "//div[@class='row property-photo-grid']/descendant::img"; 
		
		@FindBy(xpath="//a[@title='next property']")
		WebElement case2;
		
		@FindBy(xpath="//div[@class='modal-header']/descendant::h4[@class='modal-title']")
		WebElement lc_title;
		
		@FindBy(xpath="//div[@class='form-element-input']/descendant::select[@id='pageviews_before_lead_capture']")
		WebElement select_pageviews;
		
		@FindBy(xpath="//div[@class='form-element-input']/descendant::select[@id='lead_capture_method']")
		WebElement select_method;
		
		private ZBONewListingCreateAdAlert createAdAlert;
		
		@FindBy(id="username")
		WebElement username_input;

		@FindBy(id="passwd")
		WebElement password_input;
		
		@FindBy(xpath="//button[contains(text(),'Login')]")
		WebElement login_button;
		
		String searchLead_input = "//input[@name='name']";

		@FindBy(xpath="//h3[contains(text(),' Zurple Log In')]")
		WebElement zurpleLoginHeading;
		
		@FindBy(className="left")
		WebElement VLC_header;
		
		@FindBy(className="register-form-header")
		WebElement register_header;
		
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

		@Override
		public WebElement getTopMenu() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public ZWVariableLeadCapturePage() {
			
		}
		
		public ZWVariableLeadCapturePage(WebDriver pWebDriver) {
			driver = pWebDriver;
			PageFactory.initElements(driver, this);
		}
				
		public boolean checkViewsAndMethod(int index) {
			if(ActionHelper.ClickByIndex(driver, close_button, index)) {
				return true;
			} else {
				return false; 
			}
		}
		
		public boolean pageViews(String views) {
			boolean isSuccessful = false;
			switch(views) {
				case "0":
					ActionHelper.Click(driver, case0);
					if(ActionHelper.getText(driver, lc_title).contains("Register")) {
						isSuccessful = true;
					}
					break;
				case "1":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.waitForElementToBeVisible(driver, lc_title, 30);
					if(ActionHelper.getText(driver, lc_title).contains("Register")) {
						isSuccessful = true;
					}
					break;
				case "2":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, lc_title, 30);
					if(ActionHelper.getText(driver, lc_title).contains("Register")) {
						isSuccessful = true;
					}
					break;
				case "3":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, lc_title, 30);
					if(ActionHelper.getText(driver, lc_title).contains("Register")) {
						isSuccessful = true;
					}
					break;
				case "4":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, lc_title, 30);
					if(ActionHelper.getText(driver, lc_title).contains("Register")) {
						isSuccessful = true;
					}
					break;
				case "5":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, lc_title, 30);
					if(ActionHelper.getText(driver, lc_title).contains("Register")) {
						isSuccessful = true;
					}
					break;
			}
			return isSuccessful;
		}
		
		public boolean pageViewsForRegister(String views) {
			boolean isSuccessful = false;
			switch(views) {
				case "0":
					if(ActionHelper.getText(driver, register_header).contains("ENGINE")) {
						isSuccessful = true;
					}
					break;
				case "1":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.waitForElementToBeVisible(driver, register_header, 30);
					if(ActionHelper.getText(driver, register_header).contains("ENGINE")) {
						isSuccessful = true;
					}
					break;
				case "2":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, register_header, 30);
					if(ActionHelper.getText(driver, register_header).contains("ENGINE")) {
						isSuccessful = true;
					}
					break;
				case "3":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, register_header, 30);
					if(ActionHelper.getText(driver, register_header).contains("ENGINE")) {
						isSuccessful = true;
					}
					break;
				case "4":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, register_header, 30);
					if(ActionHelper.getText(driver, register_header).contains("ENGINE")) {
						isSuccessful = true;
					}
					break;
				case "5":
					ActionHelper.Click(driver, case0);
					ActionHelper.ClickByXpathIndex(driver, case1, 0);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.Click(driver, case2);
					ActionHelper.waitForElementToBeVisible(driver, register_header, 30);
					if(ActionHelper.getText(driver, register_header).contains("ENGINE")) {
						isSuccessful = true;
					}
					break;
			}
			return isSuccessful;
		}
		
		public boolean VLCSetup(String pageViews, String methodType) {
			try {
				ActionHelper.clickAndSelectFromDropdownByValue(driver, select_pageviews, pageViews);
				ActionHelper.clickAndSelectFromDropdownByValue(driver, select_method, methodType);
				ActionHelper.Click(driver, submit_button);
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean isLoginPage() {
			return ActionHelper.waitForElementToBeVisible(driver, zurpleLoginHeading, 30);
		}
		public boolean typeUserName(String pUsername) {
			return ActionHelper.Type(driver, username_input, pUsername);
		}
		public boolean typePassword(String pPassword) {
			return ActionHelper.Type(driver, password_input, pPassword);
		}
		
		public boolean clickLoginButton() {
			return ActionHelper.Click(driver, login_button);
		}
		
		public boolean isLoginSuccessful() {
			return !ActionHelper.getText(driver, VLC_header).isEmpty();
		}
		
		public ZBONewListingCreateAdAlert getAdAlert() {
			return createAdAlert;
		}
		
		public boolean doLogin(String pUsername, String pPassword) {
			boolean isLoginSuccessful = false;
			if(isLoginPage()) {
				//		assertTrue(page.isLoginPage(),"Zurple Back office login page is not visible..");
				typeUserName(pUsername);
				typePassword(pPassword);
				clickLoginButton();
				isLoginSuccessful = isLoginSuccessful();				
				}
			return isLoginSuccessful;
		}		
	}
		
		

