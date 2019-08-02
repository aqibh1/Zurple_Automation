/**
 * 
 */
package com.zurple.website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zurple.my.Page;

import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZurpleWebsiteHeader extends Page{
	
	String top_navigation_bar="//ul[@class='nav navbar-nav pull-right']/descendant::a[@title]";
	
	String[] top_header = {"Search","Real Estate Notes","Agents","Sold Homes","local-info0"};
	
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

}
