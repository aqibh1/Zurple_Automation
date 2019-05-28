/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPWidgetsPage extends Page{
	
	String widget_xpath = "//div[@class='widget-title ui-draggable-handle']/h4[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="default")
	WebElement default_sidebar;
	
	String defaultWidgets_xpath = "//div[@id='default']/descendant::h4[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="available-widgets")
	WebElement availableWidgets;
	
	@FindBy(xpath="//div[@id='wpbody-content']")
	WebElement widgetsHeading;
	
	public PPWidgetsPage() {
		
	}
	public PPWidgetsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		
	}
	public boolean dragTheWidgetToDeafultSidebar(String pWidget) {
		WebElement lFrom = ActionHelper.getDynamicElement(driver, widget_xpath, pWidget);
		return ActionHelper.dragAndDrop(driver, lFrom, default_sidebar);
	}
	public boolean dragTheWidgetFromDefaultSidebar(String pWidget) {
		WebElement lFrom = ActionHelper.getDynamicElement(driver, defaultWidgets_xpath, pWidget);
		return ActionHelper.dragAndDrop(driver, lFrom, availableWidgets);
	}
	public boolean isWidgetsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, widgetsHeading, 30);
	}
}
