/**
 * 
 */
package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOExportLeadsPage extends Page{
	
	@FindBy(xpath="//div[@id='agents-grid']/descendant::h3[text()='Lead Export Report']")
	WebElement leadExport_xpath;

	String agentLeads_input = "//div[@id='DataTables_Table_0_wrapper']/descendant::input";
	
	@FindBy(id="export_selected")
	WebElement export_selected_button;
	
	public ZBOExportLeadsPage() {
		
	}
	public ZBOExportLeadsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isExportLeadsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, leadExport_xpath, 30);
	}
	public boolean selectAgentLeads() {
		boolean isSelected = false;
		ActionHelper.waitForProcessingToEnd(driver);
		List<WebElement> list_of_input = ActionHelper.getListOfElementByXpath(driver, agentLeads_input);
		if(list_of_input.size()>2) {
			isSelected = ActionHelper.Click(driver, list_of_input.get(2));
		}else {
			isSelected = ActionHelper.Click(driver, list_of_input.get(1));
		}
		return isSelected;
	}
	public boolean clickOnExportSelectedButton() {
		return ActionHelper.Click(driver, export_selected_button);
	}
	
}
