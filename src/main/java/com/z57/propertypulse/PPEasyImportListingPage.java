/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.utility.ActionHelper;

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
	
	public boolean isEasyImportListingPage() {
		return ActionHelper.waitForElementToBeLocated(driver, easyImport_heading_xpath, 60);
	}
	
	public boolean selectIdxMLSBoard(String pOption) {
//		boolean isSelectSuccess = false;
//		if(ActionHelper.Click(driver, idxMlsBoardSelect)) {
//			List<WebElement> mls_board = idxMlsBoardSelect.findElements(By.tagName("option"));
//			for(WebElement element: mls_board) {
//				if(element.getText().trim().equalsIgnoreCase(pOption)) {
//					isSelectSuccess = true;
//					break;
//				}
//			}
//		}
		return ActionHelper.selectDropDownOption(driver, idxMlsBoardSelect, "", pOption);
	}
	
	public boolean clickOnMLSInputRadioButton() {
		return ActionHelper.Click(driver, enterMls_input);
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
		return ActionHelper.Click(driver, generateList_button);
	}

}
