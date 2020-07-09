package com.zurple.backoffice;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ZBOEditDistributionPage extends Page{
		@FindBy(className="header")
		WebElement page_header;
		
		@FindBy(id="distribution_type-percentage")
		WebElement percentage_radio;
		
		String percentage_agent = "percentage_"+FrameworkConstants.DYNAMIC_VARIABLE;
		
		@FindBy(id="saveDist")
		WebElement save_edited;
		
		@FindBy(className="ui-dialog-title")
		WebElement confirmation_title;
		
		@FindBy(id="distribution_type-all")
		WebElement all_radio;
		
		@FindBy(xpath="//div[@class='ui-dialog-buttonset']/descendant::span[text()='Change Distribution Settings']")
		WebElement confirm_update;
						
		public ZBOEditDistributionPage(WebDriver pWebDriver) {
			driver = pWebDriver;
			PageFactory.initElements(driver, this);
		}
		
		public String editDistributionPageTitle() {
			ActionHelper.waitForElementToBeVisible(driver, page_header, 30);
			return ActionHelper.getText(driver, page_header).trim();
		}
		
		public boolean clickOnByPercentage() {
			ActionHelper.waitForElementToBeVisible(driver, percentage_radio, 30);
			if(ActionHelper.isElementSelected(driver, percentage_radio)) {			
				clickOnToMe();
				saveEditedDistribution();
				confirmationModalTitle();
				confrimUpdate();
				clickOnByPercentage();
				return false;
			}	
			return ActionHelper.Click(driver, percentage_radio);
		}
		
		public boolean clickOnToMe() {
			ActionHelper.waitForElementToBeVisible(driver, all_radio, 30);
			try {
			ActionHelper.Click(driver, all_radio);
			saveEditedDistribution();
			confirmationModalTitle();
			confrimUpdate();
			return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean typeDistributionPercentage(String appendedElement, String pStringToType) {
			ActionHelper.waitForStringIDToBeVisible(driver, percentage_agent, 30);
			WebElement element = ActionHelper.getDynamicElementByID(driver, percentage_agent, appendedElement);
			return ActionHelper.ClearAndType(driver, element, pStringToType);
		}
		
		public boolean saveEditedDistribution() {
			ActionHelper.waitForElementToBeVisible(driver, save_edited, 30);
			return ActionHelper.Click(driver, save_edited);
		}
		
		public String confirmationModalTitle() {
			ActionHelper.waitForElementToBeVisible(driver, confirmation_title, 30);
			return ActionHelper.getText(driver, confirmation_title).trim();
		}
		
		public boolean confrimUpdate() {
			ActionHelper.waitForElementToBeVisible(driver, confirm_update, 30);
			return ActionHelper.Click(driver, confirm_update);
		}
}

