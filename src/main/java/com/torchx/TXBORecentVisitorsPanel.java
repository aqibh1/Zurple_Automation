package com.torchx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * 
 * @author ahabib
 *
 */
public class TXBORecentVisitorsPanel extends Page {

	@FindBy(className="customized")
	WebElement panel_header;
	
	@FindBy(xpath="//div[@class='col-md-6 ']/descendant::i[@class='info-icon-white']")
	WebElement tooltip_text;
	
	TXBORecentVisitorsPanel(){
		
	}
	
	public TXBORecentVisitorsPanel(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPanelHeaderText() {
		return ActionHelper.getText(driver, panel_header);
	}
	
	public String getToolTipText() {
		return ActionHelper.getAttribute(tooltip_text,"title");
	}

	
}
