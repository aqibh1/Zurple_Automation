package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import com.zurple.my.Page;



public class ZBOGetMoreLeadsPage extends Page{
	
//	@FindBy(xpath="//ul[@class='dropdown-menu']/descendant::button[text()=' Get More Leads Today! ']")
//	WebElement get_more_leads_button;
	

//	
//	@FindBy(xpath="//h2[text()='Create Ad']")
//	WebElement create_ad_heading;
//	
	@FindBy(xpath = "//h3[contains(text(),'Get More Leads')]")
	WebElement get_more_leads_heading;
	
	@FindBy(xpath="//div[@class='logo_cont']/descendant::img[@class='zapier_logo']")
	WebElement zapier_logo;
	
	@FindBy(xpath="//div[@class='logo_cont zillow_logo']/descendant::img[@alt='zillowgroup']")
	WebElement zillow_group;
	
	@FindBy(xpath="//div[@class='logo_cont']/descendant::img[@alt='realtor']")
	WebElement realtor;
	
	@FindBy(xpath="//div[@class='logo_cont']/descendant::img[@alt='marketleader']")
	WebElement market_leader;
	
//	@FindBy(xpath="//a[@aria-label='Go to the Zapier Homepage']//*[name()='svg']")
//	WebElement zapier_sign;
	
//	@FindBy(xpath="//h2[@id=4]/strong[normalize-space()='Realtor.com Integration']")
//	WebElement realtor_integration;
	
	@FindBy(xpath="//h3[normalize-space()='Zurple Auto Leads']")
	WebElement zurple_auto_lead_heading;
	
	@FindBy(id="zurple_auto_leads_check_availability")
	WebElement zurple_auto_lead_check_availibity;
	
	@FindBy(id="swal2-title")
	WebElement request_received;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	WebElement ok_button;
	
	@FindBy(xpath="//h3[@xpath=1]")
	WebElement zurple_seller_plus;
	
	@FindBy(id="zurple_seller_check_availability")
	WebElement seller_plus_check_availability;
	
	// 48899,48907
	
	public ZBOGetMoreLeadsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	public boolean isGetMoreLeadHeadingVisible() {
		return ActionHelper.isElementVisible(driver, get_more_leads_heading);
	}
	
	// check elements are visible
	public boolean checkZapierlogoIsVisible() {
		return ActionHelper.isElementVisible(driver, zapier_logo);
	}
	public boolean checkZillowGrouplogoIsVisible() {
		return ActionHelper.isElementVisible(driver, zillow_group);
	}
	public boolean checkRealtorlogoIsVisible() {
		return ActionHelper.isElementVisible(driver, realtor);
	}
	public boolean checkMarketLeaderlogoIsVisible() {
		return ActionHelper.isElementVisible(driver, market_leader);
	}
	
	// Check elements are clickable
	public boolean verifyZapierlogoIsClickable() {
		boolean isLogoClickable = false;
		if(checkZapierlogoIsVisible()) {
			isLogoClickable =  ActionHelper.isElementToBeClickAble(driver, zapier_logo);
		}
		return isLogoClickable;	
	}
	public boolean verifyZillowGrouplogoIsClickable() {
		boolean isLogoClickable = false;
		if(checkZillowGrouplogoIsVisible()) {
			isLogoClickable = ActionHelper.isElementToBeClickAble(driver, zillow_group);
		}
		return isLogoClickable;
	}
	public boolean verifyRealtorlogoIsClickable() {
		boolean isLogoClickable = false;
		if(checkRealtorlogoIsVisible()) {
			isLogoClickable = ActionHelper.isElementToBeClickAble(driver, realtor);
		}
		return isLogoClickable;
	}
	public boolean checkMarketLeaderlogoIsClickable() {
		boolean isLogoClickable = false;
		if(checkMarketLeaderlogoIsVisible()) {
			isLogoClickable = ActionHelper.isElementToBeClickAble(driver, market_leader);
		}
		return isLogoClickable;
	}
}
