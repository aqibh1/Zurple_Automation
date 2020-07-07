package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOEditDistributionPage extends Page{
		@FindBy(className="header")
		WebElement page_header;
		
		@FindBy(id="distribution_type-percentage")
		WebElement percentage_radio;
		
		@FindBy(id="percentage_12774")
		WebElement percentage_agent;
		
		@FindBy(id="saveDist")
		WebElement save_edited;
		
		@FindBy(className="ui-dialog-title")
		WebElement confirmation_title;
		
//		@FindBy(css="ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")
//		WebElement confirm_update;
		
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
			return ActionHelper.Click(driver, percentage_radio);
		}
		
		public boolean typeDistributionPercentage(String pStringToType) {
			ActionHelper.waitForElementToBeVisible(driver, percentage_agent, 30);
			return ActionHelper.ClearAndType(driver, percentage_agent, pStringToType);
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

