/**
 * 
 */
package com.zurple.website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZurpleWebsiteHeader extends Page{
	
	String top_navigation_bar="//ul[@class='nav navbar-nav pull-right']/descendant::a[@title]";
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement login_link;
	
	String[] top_header = {"Search","Real Estate Notes","Agents","Sold Homes"};
	
	@FindBy(xpath="//li[@class='dropdown']/descendant::b[@class='caret']")
	WebElement userDropdown;
	
	@FindBy(xpath="//ul[@class='dropdown-menu pull-right']/descendant::a[@href='/my']")
	WebElement myAccount_dropdown;
	
	@FindBy(xpath="//a[@title='Search']")
	WebElement search_dropdown_button;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[text()='Custom Search']")
	WebElement custom_search_button;
	
	@FindBy(xpath="//a[@title='Local Info']")
	WebElement local_info_dropdown;
	
	@FindBy(xpath="//a[text()='Community']")
	WebElement community_dropdown;
	
	
	public ZurpleWebsiteHeader(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isTopNavigationBarValid() {
		boolean isFound = false;
		List<WebElement> list_elements = driver.findElements(By.xpath(top_navigation_bar));
		for(String header:top_header) {
			isFound = false;
			for(WebElement element: list_elements) {
				if(header.equalsIgnoreCase(element.getText().trim())) {
					isFound = true;
					break;
				}
			}
			if(!isFound) {
				AutomationLogger.error(header+ "not found in the header..");
				break;
			}
		}
		return isFound;
	}
	
	public boolean isLeadLoggedIn() {
		return !ActionHelper.isElementVisible(driver, login_link);
	}
	
	public boolean goToMyAccount() {
		boolean isClick = false;
		if(ActionHelper.Click(driver, userDropdown)) {
			isClick = ActionHelper.Click(driver, myAccount_dropdown);
		}
		return isClick;
	}
	public boolean clickOnCustomSearch() {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, search_dropdown_button, 30) &&
				ActionHelper.Click(driver, search_dropdown_button)) {
			if(ActionHelper.waitForElementToBeVisible(driver, custom_search_button, 5)) {
				isClicked = ActionHelper.Click(driver, custom_search_button);
			}
			
		}
		return isClicked;
	}
	public boolean goToCommunityReportsPage() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, local_info_dropdown)) {
			isSuccessful = ActionHelper.Click(driver, community_dropdown);
		}
		return isSuccessful;
	}

}
