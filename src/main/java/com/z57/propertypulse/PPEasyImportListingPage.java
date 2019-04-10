/**
 * 
 */
package com.z57.propertypulse;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class PPEasyImportListingPage extends Page{
	
	String easyImport_heading_xpath = "//h1[@class='z57-theme-page-topic' and text()='Easy Import']";
	
	@FindBy(id="idx_mls_board_select")
	WebElement idxMlsBoardSelect;
	
	@FindBy(id="by_mls_radio")
	WebElement enterMls_radiobox;
	
	@FindBy(id="mls_input")
	WebElement enterMls_input;
	
	@FindBy(id="generate_list_button")
	WebElement generateList_button;
	
	@FindBy(id="select_all_button")
	WebElement select_all_button;
	
	@FindBy(id="import_listings_button")
	WebElement import_listings_button;
	
	PPEasyImportListingPage(){
		
	}
	
	public PPEasyImportListingPage(WebDriver pWebDriver) {
			driver = pWebDriver;
			PageFactory.initElements(driver, this);
	}

	public boolean isEasyImportListingPage() {
		return ActionHelper.waitForElementToBeLocated(driver, easyImport_heading_xpath, 60);
	}
	
	public boolean selectIdxMLSBoard(String pOption) {

		boolean isSuccessful=false;
		List<WebElement> list_of_options = new ArrayList<WebElement>();
		AutomationLogger.info("Clicking on button "+idxMlsBoardSelect);
		if(ActionHelper.Click(driver, idxMlsBoardSelect)) {
			list_of_options = idxMlsBoardSelect.findElements(By.tagName("option"));
			AutomationLogger.info("Selecting a option from Dropdown "+idxMlsBoardSelect);
			for(WebElement element: list_of_options) {
				String lOptionText = element.getText().trim().replace("\n", "");
				System.out.println(lOptionText);

				if(element.getText().trim().contains(pOption)) {
					isSuccessful = ActionHelper.Click(driver, element);
					//					Click(pWebDriver,pElementToBeClicked);
					break;
				}
			}
		}

		return isSuccessful;

	}
	
	public boolean clickOnMLSInputRadioButton() {
		return ActionHelper.Click(driver, enterMls_radiobox);
	}
	
	public boolean typeMls(String pMLS) {
		return ActionHelper.ClearAndType(driver, enterMls_input, pMLS);
	}
	
	public boolean isListGeneratedSuccessfully() {
		int count=0;
		while(!generateList_button.getText().trim().equalsIgnoreCase("Generate List") || count<100) {
			count++;
		}
		return generateList_button.getText().trim().equalsIgnoreCase("Generate List");
	}
	public boolean clickOnGenerateListButton() {
		boolean isClicked= false;
		if(ActionHelper.waitForElementToBeClickAble(driver, generateList_button)) {
			isClicked = ActionHelper.Click(driver, generateList_button);;
		}
		return isClicked;
	}
	public boolean clicOnSelectAllButton() {
		boolean clickSuccessful= false;
		if(ActionHelper.waitForElementToBeVisible(driver, select_all_button, 30)) {
			clickSuccessful = ActionHelper.Click(driver, select_all_button);
		}
		return clickSuccessful;
	}
	public boolean clickOnImportListingButton() {
		return ActionHelper.Click(driver, import_listings_button);
	}

}
