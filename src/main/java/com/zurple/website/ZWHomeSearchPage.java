/**
 * 
 */
package com.zurple.website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZWHomeSearchPage extends Page{
	
	@FindBy(id="city")
	WebElement search_input;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement login_link;

	@FindBy(xpath="//div[@class='page-header text-center']/h3[contains(text(),'Homes For Sale in')]")
	WebElement homesForSale_heading;
	
	@FindBy(xpath="//h3[text()='Featured Homes']")
	WebElement featuredHomes_heading;
	
	String logos_xpath = "//img[@src]";
	
	public boolean verifyLogos() {
		boolean isFound = false;
		String [] logos_names = {"zurple_logo_64.png","79d4f98829243c758a1b869e5d0085d7.png"};
		List<WebElement> list_elements = driver.findElements(By.xpath(logos_xpath));
		for(String logo:logos_names) {
			isFound = false;
			for(WebElement element: list_elements) {
				if(element.getAttribute("src").contains(logo)) {
					isFound = true;
					break;
				}
			}
			if(!isFound) {
				break;
			}
		}
		return isFound;
	}
	
	public boolean isFeaturedhomeHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, featuredHomes_heading, 30);
	}
	
	public boolean isHomesForSaleHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, homesForSale_heading, 30);
	}
}
